package com.bodejidi;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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
    
    public Map<String, Object> getAllDepartment(){
        String sql = "select * from department";
        DatabaseManager db = new DatabaseManager();
        DepartmentService departmentService = new DepartmentService();
        
        db.connectAndCreateStatement();
        ResultSet resultSet = db.executeQuery(sql);
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("departmentList",addDepartmentToList(resultSet));
        db.close();
        return dataModel;   
    }
    public Map getDepartmentAndContactsById(Long id){
        DatabaseManager db = new DatabaseManager();
        ContactService contactService = new ContactService();
        Department department = new Department();
        List contacts = new ArrayList();
        Map<String, Object> dataModel = new HashMap<String, Object>();
        String sql = "select * from  department,contact,contact_department where "
            + "department.id='" 
            + id
            + "'and contact_department.id_department=department.id and" 
            + " contact.id=contact_department.id_contact";
        db.connectAndCreateStatement();
        ResultSet resultSet = db.executeQuery(sql); 
        try{
            while(resultSet.next()){ 
                Contact contact = contactService.createContactFromResultSet(resultSet);
                department = getDepartmentFromResultSet(resultSet);
                contacts.add(contact);            
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();	
        }
        dataModel.put("contactList",contacts);
        dataModel.put("department",department);
        db.close();
        return dataModel;
    }
}