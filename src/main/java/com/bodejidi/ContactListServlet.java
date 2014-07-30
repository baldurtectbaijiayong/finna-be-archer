package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ContactListServlet extends HttpServlet{

    DatabaseManager db = new DatabaseManager();
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        db.connectAndCreateStatement();
        db.executeQuery("select * from (contact left join contact_department on "
            + "contact.id=contact_department.id_contact)left join department on "
            + "contact_department.id_department=department.id");
            
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("contactList",addContactToContactsList(db.resultSet));
        db.close();
        render(request, response, "contact/list",dataModel);
    }
    
    public List addContactToContactsList(ResultSet resultSet){
        List contacts = new ArrayList();
        
        try {    
            while (resultSet.next()){
                contacts.add(createContactFromResultSet(db.resultSet));
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return contacts;
    }
   
    public void render(HttpServletRequest request, HttpServletResponse response, String page, Map<String,Object> dataModel)
        throws IOException, ServletException {
        for(String key : dataModel.keySet()) {
            request.setAttribute(key,dataModel.get(key));
        }
        
        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/" + page + ".jsp")
            .forward(request,response);
    }
    
    public Contact createContactFromResultSet(ResultSet resultSet) 
        throws SQLException {
        Contact contact = new Contact();
                    
        contact.setId(resultSet.getLong("contact.id"));
        contact.setName(resultSet.getString("contact.name"));
        contact.setMobile(resultSet.getString("contact.mobile"));
        contact.setDepartment(resultSet.getString("department.name"));
        contact.setOfficeAddress(resultSet.getString("office_address"));
        contact.setHomeAddress(resultSet.getString("home_address"));
        contact.setVpmn(resultSet.getString("vpmn"));
        contact.setMemo(resultSet.getString("contact.memo"));
        contact.setJob(resultSet.getString("job")); 
        contact.setJobLevel(resultSet.getLong("job_level"));
        contact.setDepartmentId(resultSet.getLong("department.id"));
        
        return contact;
    }
}
class DatabaseManager{
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
    public void connectAndCreateStatement() {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception e){
            //ignore
        }
        
        try { 
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");      
            statement = connection.createStatement(); 

        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void executeQuery(String sql){
        try { 
            resultSet = statement.executeQuery(sql);
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public void close(){
        if(resultSet!=null){
            try{
                resultSet.close();
            }catch(Exception e){
                //ignore; 
            }
        }
           
         if(statement!=null){
            try{
                statement.close();
            }catch(Exception e){
                //ignore;                       
            }
        }
        if(connection!=null){
            try{
                connection.close();
            }catch(Exception e){
                //ignore;  
            }
        }
    }
   
}
