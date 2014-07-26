<%@ page import="java.util.List, com.bodejidi.Department, com.bodejidi.Contact"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<Contact> contactList = (List<Contact>) request.getAttribute("contactList");
Department department = (Department) request.getAttribute("department"); 
%>
<html>
    <head>
        <title>Department Show</title>
    </head>
    <body>
        <a href="list">Department List</a>
        <h1>Department Name: ${department.name}</h1>
        <table border="1">
            <tr>
                <td>Name</td>
                <td>${department.name}</td>
            </tr>
            <tr>
                <td>Parent</td>
                <td>${department.parent}</td>
            </tr>
            <tr>
                <td>Address</td>
                <td>${department.address}</td>
            </tr>
            <tr>
                <td>Memo</td>
                <td>${department.memo}</td>
            </tr>
        </table>
        <br/>
        <br/>
        <table border="1">
            <tr>
                <td>name</td>
                <td>mobile</td>
                <td>department</td>
            </tr>
            
            <c:forEach var="contact" items="${contactList}">
            <tr>
                <td><a href="../contact/list?contactId=${contact.id}">${contact.name}</td>
                <td>${contact.mobile}</td>
                <td>${contact.department}</td>
            <tr>
            </c:forEach>
            
        </table>
    </body>
</html>