package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DepartmentShowServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		response.getWriter().println("Show");

		Connection connection = null;
		//Statement statement = null;
		//ResultSet resultSet = null;

		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) {
			// handle the error
		}

		try{
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
			//jdbc:mysql://localhost/test?user=root&password=");
			response.getWriter().println(connection);
			connection.close();
		} catch(SQLException sqle) {
			response.getWriter().println("cannot connect to db");
			sqle.printStackTrace();
		}
	}
}
