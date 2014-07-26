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

public class AdministratorDepartmentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{
        response.getWriter().println("Department");
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(Exception e) {
        
        }
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from department");
            while(resultSet.next()) {
                Department department = new Department();
                
                department.setId(resultSet.getLong("id"));
                department.setName(resultSet.getString("name"));
                department.setParent(resultSet.getString("parent"));
                department.setAddress(resultSet.getString("address"));
            
                response.getWriter().println(department.getName());
                response.getWriter().println(department.getParent());
                response.getWriter().println(department.getAddress());
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch(Exception e) {
            
            }
        }
        
        if(statement != null) {
            try {
                statement.close();
            } catch(Exception e) {
                
            }
        }
        
        if(connection != null) {
            try {
                connection.close();
            } catch(Exception e) {
            
            }
        }
    }
}