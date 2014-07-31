package com.bodejidi;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactListService {
    public Map<String,Object> getAllContacts(){
        DatabaseManager db = new DatabaseManager();
        db.connectAndCreateStatement();
        ResultSet resultSet = db.executeQuery("select * from (contact left join contact_department on "
            + "contact.id=contact_department.id_contact)left join department on "
            + "contact_department.id_department=department.id");
            
        Map<String, Object> dataModel = new HashMap<String, Object>();
        dataModel.put("contactList",addContactToContactsList(resultSet));
        db.close(); 
        return dataModel;
    }
    
    public Contact getContactById(Long id) {
        Contact contact = new Contact();
        DatabaseManager db = new DatabaseManager();
        db.connectAndCreateStatement();
        String sql = "select * from (contact left join contact_department on "
            + "contact.id=contact_department.id_contact)left join department on "
            + "contact_department.id_department=department.id "
            + "where contact.id=" + id;
        ResultSet resultSet = db.executeQuery(sql);
        try{  
            if (resultSet.next())
            contact = createContactFromResultSet(resultSet);
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }     
        
        db.close(); 
        return contact;
    }
    
    public List addContactToContactsList(ResultSet resultSet){
        List contacts = new ArrayList();
        
        try {    
            while (resultSet.next()){
                contacts.add(createContactFromResultSet(resultSet));
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return contacts;
    }
   
    public Contact createContactFromResultSet(ResultSet resultSet) 
        throws SQLException {
        Contact contact = new Contact();
                    
        contact.setId(resultSet.getLong("contact.id"));
        contact.setName(resultSet.getString("contact.name"));
        contact.setMobile(resultSet.getString("contact.mobile"));
        contact.setDepartment(resultSet.getString("department.name"));
        contact.setOfficeAddress(resultSet.getString("office_address"));
        contact.setHomeAddress(resultSet.getString("home_address"));
        contact.setVpmn(resultSet.getString("vpmn"));
        contact.setMemo(resultSet.getString("contact.memo"));
        contact.setJob(resultSet.getString("job")); 
        contact.setJobLevel(resultSet.getLong("job_level"));
        contact.setDepartmentId(resultSet.getLong("department.id"));
        
        return contact;
    }
}