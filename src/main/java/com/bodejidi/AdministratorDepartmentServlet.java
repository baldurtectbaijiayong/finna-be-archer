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
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(Exception e) {
        
        }
        
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from department");
            while(resultSet.next()) {
                response.getWriter().println(resultSet.getString("name"));
                response.getWriter().println(resultSet.getString("parent"));
                response.getWriter().println(resultSet.getString("address"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException sqle) {
            response.getWriter().println("cannot connect to db");
            sqle.printStackTrace();
        }
    }
}