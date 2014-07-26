package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.util.List;
import java.util.ArrayList;

public class AdministratorContactServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{
        
        String sql;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception ex){
            //handl the error
        }
        if(request.getParameter("contactId") != null && request.getParameter("contactId") != ""){
            try{
                sql = "select * from  department,contact,contact_department where "
                    + "contact.id='" 
                    + request.getParameter("contactId") 
                    + "'and contact_department.id_department=department.id and" 
                    + " contact.id=contact_department.id_contact";
                    
                Contact contact = new Contact();
                
                connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);      
                resultSet.next();
                
                contact.setId(resultSet.getLong("contact.id"));
                contact.setName(resultSet.getString("contact.name"));
                contact.setMobile(resultSet.getString("mobile"));
                contact.setVpmn(resultSet.getString("vpmn"));
                contact.setEmail(resultSet.getString("email"));
                contact.setHomeAddress(resultSet.getString("home_address"));
                contact.setOfficeAddress(resultSet.getString("office_address"));
                contact.setMemo(resultSet.getString("contact.memo"));
                contact.setJob(resultSet.getString("job"));
                contact.setJobLevel(resultSet.getLong("job_level"));
                contact.setDepartment(resultSet.getString("department.name"));
                contact.setDepartmentId(resultSet.getLong("department.id"));
                
                System.out.println(resultSet);
                
                request.setAttribute("contact",contact);
                getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/administrator/contact/show.jsp")
                    .forward(request, response);
                    
            }catch(SQLException sqle){
                response.getWriter().println("can not connect to DB");
                response.getWriter().println(sqle.getMessage());
                sqle.printStackTrace();
            }
            
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch(Exception ex){
                
                }
            }
            
            if(statement != null){
                try{
                    statement.close();
                }catch(Exception ex){
                
                }
            }
            
            if(connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                
                }
            }
        }else{
            try{
                sql = "select * from  department,contact,contact_department"
                    + " where contact_department.id_department=department.id and" 
                    + " contact.id=contact_department.id_contact";
                    
                List<Contact> contacts = new ArrayList();
            
                connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);  
                
                while(resultSet.next()) {
                    Contact contact = new Contact();
                    
                    contact.setId(resultSet.getLong("contact.id"));
                    contact.setName(resultSet.getString("contact.name"));
                    contact.setMobile(resultSet.getString("mobile"));
                    contact.setVpmn(resultSet.getString("vpmn"));
                    contact.setEmail(resultSet.getString("email"));
                    contact.setHomeAddress(resultSet.getString("home_address"));
                    contact.setOfficeAddress(resultSet.getString("office_address"));
                    contact.setMemo(resultSet.getString("contact.memo"));
                    contact.setJob(resultSet.getString("job"));
                    contact.setJobLevel(resultSet.getLong("job_level"));
                    contact.setDepartment(resultSet.getString("department.name"));
                    contact.setDepartmentId(resultSet.getLong("department.id"));
                    
                    contacts.add(contact);
                }
                System.out.println("resultset: " + resultSet);
               
                request.setAttribute("contactList",contacts);
                getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/administrator/contact/list.jsp")
                    .forward(request, response);   
            }catch(SQLException sqle){
                response.getWriter().println("can not connect to DB");
                response.getWriter().println(sqle.getMessage());
                sqle.printStackTrace();
            }
            
            if(resultSet != null){
                try{
                    resultSet.close();
                }catch(Exception ex){
                
                }
            }
            
            if(statement != null){
                try{
                    statement.close();
                }catch(Exception ex){
                
                }
            }
            
            if(connection != null){
                try{
                    connection.close();
                }catch(Exception e){
                
                }
            }
        }
    }
}
