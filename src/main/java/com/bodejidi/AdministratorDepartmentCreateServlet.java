package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class AdministratorDepartmentCreateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/administrator/department/create.jsp")
            .forward(request, response);
    }
    
     public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        Connection connection = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(Exception e){
            response.getWriter().println(e);
        }
        
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
            response.getWriter().println(connection);
            connection.close();
        } catch(SQLException sqle){
            response.getWriter().println(sqle);
        }
    }
}