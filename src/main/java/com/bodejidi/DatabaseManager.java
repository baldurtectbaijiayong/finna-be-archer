package com.bodejidi;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager{
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    
    public void connectAndCreateStatement() {
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception e){
            //ignore
        }
        
        try { 
            connection = DriverManager.getConnection("jdbc:mysql://localhost/test?user=root&password=");      
            statement = connection.createStatement(); 

        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    
    public ResultSet executeQuery(String sql){
        try { 
            resultSet = statement.executeQuery(sql);
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        return resultSet;
    }
    
    public void close(){
        if(resultSet!=null){
            try{
                resultSet.close();
            }catch(Exception e){
                //ignore; 
            }
        }
           
         if(statement!=null){
            try{
                statement.close();
            }catch(Exception e){
                //ignore;                       
            }
        }
        if(connection!=null){
            try{
                connection.close();
            }catch(Exception e){
                //ignore;  
            }
        }
    }
}
