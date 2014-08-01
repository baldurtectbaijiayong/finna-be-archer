package com.bodejidi;

import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

public class DepartmentShowServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        
        DatabaseManager db = new DatabaseManager();
        DepartmentService departmentService = new DepartmentService();
        ContactService contactService = new ContactService();
        String sql = "select * from  department,contact,contact_department where "
            + "department.id='" 
            + request.getParameter("departmentId") 
            + "'and contact_department.id_department=department.id and" 
            + " contact.id=contact_department.id_contact";
            
        List contacts = new ArrayList();
        Department department = new Department();
        db.connectAndCreateStatement();
        ResultSet resultSet = db.executeQuery(sql);
        
        try{
            while(resultSet.next()){
                Contact contact = contactService.createContactFromResultSet(resultSet);
                department = departmentService.getDepartmentFromResultSet(resultSet);
                contacts.add(contact);            
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();	
        }
        
        request.setAttribute("contactList",contacts);
        request.setAttribute("department",department);
        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/department/show.jsp")
            .forward(request, response);
            
        db.close();
    }
}
