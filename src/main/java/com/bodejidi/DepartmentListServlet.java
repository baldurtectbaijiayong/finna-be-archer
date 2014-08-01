package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class DepartmentListServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        
        String sql = "select * from department";
        DatabaseManager db = new DatabaseManager();
        
        db.connectAndCreateStatement();
        ResultSet resultSet = db.executeQuery(sql);
        request.setAttribute("departmentList", addDepartmentToList(resultSet));
        db.close();
        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/department/list.jsp")
            .forward(request, response);
    }
    
    public Department getDepartmentFromResultSet(ResultSet resultSet){
        Department department = new Department();
        try {
            
            department.setId(resultSet.getLong("id"));
            department.setName(resultSet.getString("name"));
            department.setMemo(resultSet.getString("memo"));
            department.setParent(resultSet.getString("parent"));
            department.setAddress(resultSet.getString("address"));   
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }        
        return department;
    }   
    
    public List addDepartmentToList(ResultSet resultSet){
        List<Department> departments = new ArrayList();
        try{
            while(resultSet.next()){
                Department department = getDepartmentFromResultSet(resultSet);
                departments.add(department);
            }
        } catch(SQLException sqle) {
                sqle.printStackTrace();
        }
        return departments;
    }
}
