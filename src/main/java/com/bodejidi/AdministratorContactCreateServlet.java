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

public class AdministratorContactCreateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        
        String departmentParameter = request.getParameter("department");
        
        if(departmentParameter != null && departmentParameter != ""){
            request.setAttribute("department",departmentParameter);

            System.out.println(departmentParameter);
            getServletContext()
                .getRequestDispatcher("/WEB-INF/jsp/administrator/contact/create.jsp")
                .forward(request, response);
           
        } 
       
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        Contact contact = new Contact();
        contact.setName(request.getParameter("contactName"));
        contact.setMobile(request.getParameter("contactMobile"));
        contact.setVpmn(request.getParameter("contactVpmn"));
        contact.setEmail(request.getParameter("contactEmail"));
        contact.setHomeAddress(request.getParameter("contactHomeAddress"));
        contact.setOfficeAddress(request.getParameter("contactOfficeAddress"));
        contact.setJob(request.getParameter("contactJob"));
        contact.setJobLevel(Long.valueOf(request.getParameter("contactJobLevel")));
        contact.setDepartment(request.getParameter("contactDepartment"));
        
        String sql = "insert into contact (name, mobile, vpmn, email,"
            + " home_address, office_address, memo, job, job_level)"
            + " values('" + contact.getName() + "','"
            + contact.getMobile() + "','"
            + contact.getVpmn() + "','"
            + contact.getEmail() + "','"
            + contact.getHomeAddress() + "','"
            + contact.getOfficeAddress() + "','"
            + contact.getMemo() + "','"
            + contact.getJob() + "',"
            + contact.getJobLevel() + ")";
            
        String sql2 = "select * from department where name='" + contact.getDepartment() +"'";
            
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch(Exception e){
            response.getWriter().println(e);
        }
        
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
            statement = connection.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.getGeneratedKeys();
            
            if(resultSet != null){
                resultSet.next();
                contact.setId(resultSet.getLong(1));
            }
            
            resultSet = statement.executeQuery(sql2);
            
            if(resultSet != null){
                resultSet.next();
                contact.setDepartmentId(resultSet.getLong("id"));
            } 
            
            statement.executeUpdate("insert into contact_department values(" 
                + contact.getId() + "," + contact.getDepartmentId() + ")");
            
            response.getWriter().println("Create Contact Success");
        } catch(SQLException sqle){
            response.getWriter().println(sqle);
        }
        
        if(resultSet != null){
            try{
                resultSet.close();
            }catch(Exception e){
                response.getWriter().println(e);
            }
        }
        
        if(statement != null){
            try{
                statement.close();
            }catch(Exception e){
                response.getWriter().println(e);
            }
        }
        
        if(connection != null){
            try{
                connection.close();
            }catch(Exception e){
                response.getWriter().println(e);
            }
        }
        getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/administrator/contact/success.jsp")
            .forward(request, response);   
    }
}