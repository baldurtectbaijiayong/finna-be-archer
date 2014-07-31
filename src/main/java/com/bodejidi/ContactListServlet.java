package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ContactListServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        ContactService contactService = new ContactService();
        render(request, response, "contact/list", contactService.getAllContacts());
    }
    
    public void render(HttpServletRequest request, HttpServletResponse response, String page, Map<String,Object> dataModel)
        throws IOException, ServletException {
        for(String key : dataModel.keySet()) {
            request.setAttribute(key,dataModel.get(key));
        }
        
        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/" + page + ".jsp")
            .forward(request,response);
    }
}