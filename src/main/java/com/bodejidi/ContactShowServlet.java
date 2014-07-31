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
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException
    {
        DatabaseManager db = new DatabaseManager();
        String paraId = request.getParameter("contactId");
        
        String SQLDriver = "com.mysql.jdbc.Driver";
        String SQLURL = "jdbc:mysql://localhost/test?user=root&password=";
        
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        Contact contact = new Contact();
        
        if(null == paraId)
        {
            response.getWriter().println("Contact not find");
        }
        else
        {
            Long id = Long.valueOf(paraId);
            String sql = "select * from (contact left join contact_department on "
                + "contact.id=contact_department.id_contact)left join department on "
                + "contact_department.id_department=department.id "
                + "where contact.id=" + request.getParameter("contactId");
                
            response.getWriter().println(id);

            db.connectAndCreateStatement();
            resultSet = db.executeQuery(sql);
            try
            {
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
                
                request.setAttribute("contact",contact);
                
                getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/contact/show.jsp")
                    .forward(request, response);
                
            }catch(SQLException ex)
            {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLStates: " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
            }
        }
        db.close();
    } 
}
