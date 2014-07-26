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

public class ContactShowServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException
    {
        String paraId = req.getParameter("contactId");
        
        String SQLDriver = "com.mysql.jdbc.Driver";
        String SQLURL = "jdbc:mysql://localhost/test?" 
        + "user=root&password=";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        Contact contact = new Contact();
        
        if(null == paraId)
        {
            resp.getWriter().println("Contact not find");
        }
        else
        {
            Long id = Long.valueOf(paraId);
            String sql = "select * from  department,contact,contact_department where "
                + "contact.id='" 
                + req.getParameter("contactId") 
                + "'and contact_department.id_department=department.id and" 
                + " contact.id=contact_department.id_contact";
                

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
               
                contact.setId(resultSet.getLong("contact.id"));
                contact.setName(resultSet.getString("contact.name")); 
                contact.setMobile(resultSet.getString("mobile"));
                contact.setVpmn(resultSet.getString("vpmn"));
                contact.setEmail(resultSet.getString("email"));
                contact.setHomeAddress(resultSet.getString("home_address"));
                contact.setOfficeAddress(resultSet.getString("office_address"));
                contact.setMemo(resultSet.getString("contact.memo"));
                contact.setJob(resultSet.getString("job"));
                contact.setJobLevel(resultSet.getLong("job_level"));
                
                contact.setDepartmentId(resultSet.getLong("department.id"));
                contact.setDepartment(resultSet.getString("department.name"));
                System.out.println(resultSet);
                
                req.setAttribute("contact",contact);
                
                getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/contact/show.jsp")
                    .forward(req, resp);
                
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
