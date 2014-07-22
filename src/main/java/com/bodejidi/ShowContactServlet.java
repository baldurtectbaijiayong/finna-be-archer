package com.bodejidi;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ShowContactServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException
    {
        String paraId = req.getParameter("ContactId");
        
        String SQLDriver = "com.mysql.jdbc.Driver";
        String SQLURL = "jdbc:mysql://localhost/test?" 
        + "user=root&password=";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        if(null == paraId)
        {
            resp.getWriter().println("Contact not find");
        }
        else
        {
            Long id = Long.valueOf(paraId);
            String sql = "SELECT * FROM contact WHERE id=" + id;

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
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                resultSet.next();
                resp.getWriter().println("contactId :" + resultSet.getInt("id"));
                resp.getWriter().println("name : " + resultSet.getString("name"));
                resp.getWriter().println("mobile :" + resultSet.getInt("mobile"));
                resp.getWriter().println("vpmn :" + resultSet.getInt("vpmn"));
                resp.getWriter().println("email : " + resultSet.getString("email"));    
                System.out.println(resultSet);
            }catch(SQLException ex)
            {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLStates: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
            }finally
            {
                if(resultSet != null)
                {
                    try
                    {
                       resultSet.close();
                    }catch(SQLException ex)
                    {
                        System.out.println(ex);
                    }
                }
                if(statement != null)
                {
                    try
                    {
                        statement.close();
                    }catch(SQLException ex)
                    {
                        System.out.println(ex);
                    }    
                }
                if(connection != null)
                {
                    try
                    {
                        connection.close();
                    }catch(SQLException ex)
                    {
                        System.out.println(ex);
                    }
                }
            
            } 

        }
    } 
}
