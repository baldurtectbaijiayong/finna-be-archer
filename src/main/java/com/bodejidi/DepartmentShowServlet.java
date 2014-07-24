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

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sql = "select * from  department,contact,contact_department where department.name='" + request.getParameter("departmentName") + "'and contact_department.id_department=department.id and contact.id=contact_department.id_contact";
		List contacts = new ArrayList();

		try	{
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(Exception e){

		}
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
			Contact contact = new Contact();
			contact.setName(resultSet.getString("contact.name"));
			contact.setMobile(resultSet.getString("mobile"));
			contact.setDepartment(resultSet.getString("department.name"));
			contacts.add(contact);
			}
		}catch(SQLException sqle){
			response.getWriter().println("Cannot connection to DB");
			response.getWriter().println(sqle.getMessage());
			sqle.printStackTrace();	
		}
		
		if (resultSet != null){
			try{
				resultSet.close();		
			}catch(SQLException sqle){
				
			}
		}
		if (statement != null){
			try{
				statement.close();		
			}catch(SQLException sqle){
				
			}
		}
		if (connection != null){
			try{
				connection.close();		
			}catch(SQLException sqle){
				
			}
		}
		for (Object obj : contacts){
			Contact contact = (Contact) obj;
			response.getWriter().println("Name:" + contact.getName());
			response.getWriter().println("Mobile:" + contact.getMobile());
			response.getWriter().println("Department:" + contact.getDepartment());

		}
	}
}
