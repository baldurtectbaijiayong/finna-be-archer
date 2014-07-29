package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.Statement;
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
        Statement statement = null;
        String sql;
        Department department = new Department();
        department.setName(request.getParameter("departmentName"));
        department.setMemo(request.getParameter("departmentMemo"));
        department.setParent(request.getParameter("departmentParent"));
        department.setAddress(request.getParameter("departmentAddress"));
        
        sql = "insert into department (name, memo, parent, address) values('"
            + department.getName() + "','"
            + department.getMemo() + "','"
            + department.getParent() + "','"
            + department.getAddress() + "')";
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(Exception e){
            response.getWriter().println(e);
        }
        
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            response.getWriter().println("success");
        } catch(SQLException sqle){
            response.getWriter().println(sqle);
        }
        
        if(statement != null){
            try{
                statement.close();
            } catch(Exception e){
                response.getWriter().println(e);
            }
        } 
        
        if(connection != null){
            try{
                connection.close();
            } catch(Exception e){
                response.getWriter().println(e);
            }
        } 
        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/administrator/contact/success.jsp")
            .forward(request, response);   
    }
}