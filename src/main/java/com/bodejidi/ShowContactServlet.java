package com.bodejidi;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class ShowContactServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) 
        throws ServletException, IOException
    {
        resp.getWriter().println("Contact :");
    } 
}
