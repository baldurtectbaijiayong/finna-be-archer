package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DepartmentListServlet extends AbstractFinnalServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        DepartmentService departmentService = new DepartmentService();
        String page = "department/list";
        render(request,response,page,departmentService.getAllDepartment());
    }
}
