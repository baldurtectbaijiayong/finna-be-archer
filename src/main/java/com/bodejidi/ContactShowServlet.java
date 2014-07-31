package com.bodejidi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class ContactShowServlet extends AbstractFinnalServlet
{
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException
    {
        Long id = Long.valueOf(request.getParameter("contactId"));
        ContactListService contactListService = new ContactListService();
        String page = "contact/show";
        render(request, response, page, contactListService.getContactById(id));
        
    } 
    
   
}
