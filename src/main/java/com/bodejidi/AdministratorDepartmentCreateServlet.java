package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdministratorDepartmentCreateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/administrator/department/create.jsp")
            .forward(request, response);
    }
    
     public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        response.getWriter().println("success");
    }
}