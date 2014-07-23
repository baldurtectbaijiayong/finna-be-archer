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
                    //Map contact = new HashMap();
					Contact contact = new Contact();
					
                    contact.setName(resultSet.getString("contact.name"));
                    contact.setMobile(resultSet.getString("contact.mobile"));
                    contact.setDepartment(resultSet.getString("department.name"));

                    contacts.add(contact);
                }
                

            }catch(SQLException sqle){
                response.getWriter().println("Can not connect DB");
            }

            if(resultSet!=null){
                try{
                    resultSet.close();
                }catch(Exception e){
                    
                }
            }
               
             if(statement!=null){
                try{
                    statement.close();
                }catch(Exception e){
                        
                }
            }
            if(connection!=null){
                try{
                    connection.close();
                }catch(Exception e){

                }
            }
            request.setAttribute("contactList",contacts);
            getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/ContactList.jsp")
                .forward(request,response);

    }

}
