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

public class AdministratorContactServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{
        
        Contact contact = new Contact();
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception ex){
            //handl the error
        }
        
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from contact where id=" + request.getParameter("contactId"));      
            resultSet.next();
            contact.setId(resultSet.getLong("id"));
            contact.setName(resultSet.getString("name"));
            contact.setMobile(resultSet.getString("mobile"));
            contact.setVpmn(resultSet.getString("vpmn"));
            contact.setEmail(resultSet.getString("email"));
            contact.setHomeAddress(resultSet.getString("home_address"));
            contact.setOfficeAddress(resultSet.getString("office_address"));
            contact.setMemo(resultSet.getString("memo"));
            contact.setJob(resultSet.getString("job"));
            contact.setJobLevel(resultSet.getString("job_level"));
            
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
    }
}