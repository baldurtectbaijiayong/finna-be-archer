package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ContactDeleteServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException,ServletException{
        response.getWriter().println("Delete Contact");
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        response.getWriter().println("OK");
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(Exception sqle) {
        
        }
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
            statement = connection.createStatement();
            //resultSet = statement.executeQuery("");
        } catch(SQLException sqle) {
        
        }
        
        if(resultSet != null){
            try {
                resultSet.close();
            } catch(Exception ex){
                
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch(Exception ex){
                
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch(Exception ex){
                
            }
        }
        
        
    }
}