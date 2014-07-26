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

public class AdministratorContactShowServlet extends HttpServlet{
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
            contact.setJobLevel(resultSet.getLong("job_level"));
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
        
        response.getWriter().println(contact.getId());
        response.getWriter().println(contact.getName());
        response.getWriter().println(contact.getMobile());
        response.getWriter().println(contact.getVpmn());
        response.getWriter().println(contact.getEmail());
        response.getWriter().println(contact.getHomeAddress());
        response.getWriter().println(contact.getOfficeAddress());
        response.getWriter().println(contact.getMemo());
        response.getWriter().println(contact.getJobLevel());
        response.getWriter().println(contact.getJob());
    }
}
