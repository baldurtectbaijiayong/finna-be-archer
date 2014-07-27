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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            
            String sql = "select * from contact,contact_department,department where contact.id = contact_department.id_contact and contact_department.id_department = department.id";
            List contacts = new ArrayList();            
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;

            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            }catch(Exception e){
                //ignore
            }
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
                statement = connection.createStatement();
               
                resultSet = statement.executeQuery(sql);
                
                while (resultSet.next()){
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
                    contacts.add(contact);
                }
            }catch(SQLException sqle){
                response.getWriter().println("Can not connect DB");
            }

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
            request.setAttribute("contactList",contacts);
            getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/contact/list.jsp")
                .forward(request,response);

    }

}
