package com.bodejidi;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class DepartmentShowServlet extends AbstractFinnalServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        DepartmentService departmentService = new DepartmentService();
        Long id = Long.valueOf(request.getParameter("departmentId"));  
        String page = "department/show";
        render(request, response, page, departmentService.getDepartmentAndContactsById(id));  
    }
}
