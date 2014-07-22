package com.bodejidi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ContactListServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            response.getWriter().println("All Contacts");
            String sql = "select * from contact";
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            }catch(Exception e){
                //ignore
            }
            try{
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    response.getWriter().println("Name:" + resultSet.getString("name"));      
                }
                

            }catch(SQLException sqle){
                response.getWriter().println("Can not connect DB");
            }
    }

}
