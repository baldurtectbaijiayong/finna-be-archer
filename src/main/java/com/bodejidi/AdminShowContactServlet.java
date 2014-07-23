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

public class AdminShowContactServlet extends HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{
        
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
            resultSet = statement.executeQuery("select * from contact where id=1");      
            resultSet.next();
            response.getWriter().println("name:" + resultSet.getString("Name"));  
            response.getWriter().println("mobile" + resultSet.getString("Mobile"));
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