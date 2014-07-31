package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactListServlet extends AbstractFinnalServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        ContactListService contactListService = new ContactListService();
        render(request, response, "contact/list", contactListService.getAllContacts());
    }
    
   
}