package com.bodejidi;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class ShowContactServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException
    {

        String SQLDriver = "com.mysql.jdbc.Driver";
        String SQLURL = "jdbc:mysql://localhost/test?" 
        + "user=root&password=";

        Connection connection = null;
        Long id = Long.valueOf(req.getParameter("ContactId"));
        resp.getWriter().println(id);
        try
        {
            Class.forName(SQLDriver).newInstance();    
        }catch(Exception ex)
        {
            //ignore;
        }

        try
        {
            connection = DriverManager.getConnection(SQLURL);
        }catch(SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLStates: " + ex.getSQLState());
            System.out.println("ErrorCode: " + ex.getErrorCode());
        }

        resp.getWriter().println(connection);
    } 
}
