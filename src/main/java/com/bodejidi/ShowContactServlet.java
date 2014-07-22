package com.bodejidi;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import java.util.Map;
import java.util.HashMap;

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

        Map map = new HashMap();

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
               
                map.put("id",resultSet.getInt("id"));
                map.put("name",resultSet.getString("name")); 
                map.put("moblie",resultSet.getString("mobile"));
                map.put("vpmn",resultSet.getString("vpmn"));
                map.put("email",resultSet.getString("email"));
                map.put("homeAddress",resultSet.getString("home_address"));
                map.put("officeAddress",resultSet.getString("office_address"));
                map.put("memo",resultSet.getString("memo"));
                map.put("job",resultSet.getString("job"));
                map.put("jobLevel",resultSet.getString("job_level"));

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
 
                resp.getWriter().println("contactId :" + map.get("id")); 
                resp.getWriter().println("name : " + map.get("name"));
                resp.getWriter().println("mobile :" + map.get("mobile"));
                resp.getWriter().println("vpmn :" + map.get("vpmn"));
                resp.getWriter().println("email : " + map.get("email"));    
            } 
        }
    } 
}
