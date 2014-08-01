package com.bodejidi;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentService{
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