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
        String page = "contact/show";
        render(request, response, page, contactListService.getContactById(id));
        
    } 
    
    public void render(HttpServletRequest request, HttpServletResponse response, String page, Contact contact)
        throws ServletException, IOException{
        request.setAttribute("contact",contact);
        getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/" + page + ".jsp")
                .forward(request,response);
    }
}
