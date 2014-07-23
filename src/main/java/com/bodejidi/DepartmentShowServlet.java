package com.bodejidi;

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
		String sql = "select * from department";
		try	{
			Class.forName("com.mysql.jdbc.Driver");
			
		}catch(Exception e){

		}
		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
			response.getWriter().println(resultSet.getString("department.name"));
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

	}
}
