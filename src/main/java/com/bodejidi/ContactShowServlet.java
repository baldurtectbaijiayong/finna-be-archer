package com.bodejidi;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class ContactShowServlet extends HttpServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException
    {
        Long id = Long.valueOf(request.getParameter("contactId"));
        ContactListService contactListService = new ContactListService();
        request.setAttribute("contact",contactListService.getContactById(id));
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/contact/show.jsp")
                .forward(request,response);
    } 
}
