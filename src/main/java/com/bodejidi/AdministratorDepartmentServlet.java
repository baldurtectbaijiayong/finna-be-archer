package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.util.List;
import java.util.ArrayList;

public class AdministratorDepartmentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{
        String sql;
        
        if(request.getParameter("departmentId") == null){
            response.getWriter().println("Department List");
            
            sql = "select * from department";
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            List<Department> departments = new ArrayList();
            
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch(Exception e) {
            
            }
            
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while(resultSet.next()) {
                    Department department = new Department();
                    
                    department.setId(resultSet.getLong("id"));
                    department.setName(resultSet.getString("name"));
                    department.setParent(resultSet.getString("parent"));
                    department.setAddress(resultSet.getString("address"));
                
                    departments.add(department);
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(Exception e) {
                
                }
            }
            
            if(statement != null) {
                try {
                    statement.close();
                } catch(Exception e) {
                    
                }
            }
            
            if(connection != null) {
                try {
                    connection.close();
                } catch(Exception e) {
                
                }
            }
            
            request.setAttribute("departmentList", departments);
            getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/administrator/department/list.jsp")
                .forward(request, response);
        } else {
            response.getWriter().println("Department Show");
            
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            List<Contact> contacts = new ArrayList();
            Department department = new Department();
            
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch(Exception e) {
            
            }
            
            try {
                sql = "select * from  department,contact,contact_department where "
                + "department.id='" 
                + request.getParameter("departmentId") 
                + "'and contact_department.id_department=department.id and" 
                + " contact.id=contact_department.id_contact";

                connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
                while(resultSet.next()) {
                    Contact contact = new Contact();
                    
                    contact.setId(resultSet.getLong("contact.id"));
                    contact.setName(resultSet.getString("contact.name"));
                    contact.setMobile(resultSet.getString("contact.mobile"));
                    contact.setDepartment(resultSet.getString("department.name"));
                    department.setId(resultSet.getLong("department.id"));
                    department.setName(resultSet.getString("department.name"));
                    department.setMemo(resultSet.getString("department.memo"));
                    department.setParent(resultSet.getString("department.parent"));
                    department.setAddress(resultSet.getString("department.address"));
                    
                    contacts.add(contact);
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(Exception e) {
                
                }
            }
            
            if(statement != null) {
                try {
                    statement.close();
                } catch(Exception e) {
                    
                }
            }
            
            if(connection != null) {
                try {
                    connection.close();
                } catch(Exception e) {
                
                }
            }
            
            if(department.getName() != null){
                request.setAttribute("contactList", contacts);
                request.setAttribute("department", department);

                getServletContext()
                    .getRequestDispatcher("/WEB-INF/jsp/administrator/department/show.jsp")
                    .forward(request, response);
            } else {
                response.getWriter().println("cannot find this department");
            }
        }
    }
    
    public void doPost(HttpServletRequest request,HttpServletResponse response)
        throws IOException,ServletException{
        String action = request.getParameter("action");
        String sql = null;
        
        Department department = new Department();
       
        department.setName(request.getParameter("departmentName"));
        department.setMemo(request.getParameter("departmentMemo"));
        department.setParent(request.getParameter("departmentParent"));
        department.setAddress(request.getParameter("departmentAddress"));

        if(action.equals("Update")) {
            String id = request.getParameter("departmentId");
            
            Connection connection = null;
            Statement statement = null;
            
            response.getWriter().println("Update " + id + " department Success!");
     
            sql = "update department set name = '" 
                + department.getName() + "', memo = '" 
                + department.getMemo() + "', parent = '" 
                + department.getParent() + "', address = '" 
                + department.getAddress() + "' WHERE id = " + id;
            
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch(Exception e) {
            
            }
    
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException sqle) {
                response.getWriter().println(sqle);
                sqle.printStackTrace();
            }
            
            if(statement != null) {
                try {
                    statement.close();
                } catch(Exception e) {
                    
                }
            }
            
            if(connection != null) {
                try {
                    connection.close();
                } catch(Exception e) {
                
                }
            }
        }
        
        if(action.equals("Delete")) {
            response.getWriter().println("Department Show");
            
            Connection connection = null;
            Statement statement = null;
            response.getWriter().println(request.getParameter("departmentId"));
            
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch(Exception e) {
            
            }
            
            try {
                sql = "delete from department where id=" + request.getParameter("departmentId");

                connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
                statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (SQLException sqle) {
                response.getWriter().println(sqle);
                sqle.printStackTrace();
            }
            
            if(statement != null) {
                try {
                    statement.close();
                } catch(Exception e) {
                    
                }
            }
            
            if(connection != null) {
                try {
                    connection.close();
                } catch(Exception e) {
                
                }
            }
        }
    }
}