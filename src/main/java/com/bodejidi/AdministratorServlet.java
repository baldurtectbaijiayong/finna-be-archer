package com.bodejidi;
 
import java.io.IOException; 
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

public class AdministratorServlet extends HttpServlet { 
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws IOException, ServletException {
        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/administrator/login.jsp")
            .forward(request, response);
    } 

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username.equals("admin") && password.equals("123")) {
            getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/administrator/success.jsp")
            .forward(request, response);
        } else {
            response.getWriter().println("fail");
        }

    } 
} 

