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
        String paraId = request.getParameter("contactId");
        ContactListService contactListService = new ContactListService();
        request.setAttribute("contact",contactListService.getContactById(paraId));
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/contact/show.jsp")
                .forward(request,response);
    } 
}
