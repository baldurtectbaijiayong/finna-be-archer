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
        ResultSet resultSet = null;
        DatabaseManager db = new DatabaseManager();
        String paraId = request.getParameter("contactId");
        ContactListService contactListService = new ContactListService();              
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
            try{  
                if (resultSet.next())
                contact = contactListService.createContactFromResultSet(resultSet);
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }
            request.setAttribute("contact",contact);
            getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/contact/show.jsp")
                .forward(request,response);
        } 
        db.close();
    } 
}
