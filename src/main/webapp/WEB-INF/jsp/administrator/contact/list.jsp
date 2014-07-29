<%@ page import="java.util.List,com.bodejidi.Contact,com.bodejidi.Department"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<% 
    List contactList = (List) request.getAttribute("contactList");
%>

<html>
    <head>
        <title>
            All Contacts
        </title>
    </head>
    <body>
        <a href="../department/list">Department List</a>
        <h1>All Contacts</h1>
        <a href="create">Create Contact</a>
        <table border = "1">
            <tr>
                <td>name</td>
                <td>mobile</td>
                <td>department</td>
                <td>operation</td>          
            </tr>

            <c:forEach var="contact" items="${contactList}">
            <tr>
                <td><a href="list?contactId=${contact.id}">${contact.name}</td>
                <td>${contact.mobile}</td>
                <td><a href="../department/list?departmentId=${contact.departmentId}">${contact.department}</td>
                <td><button><a href="?contactId=${contact.id}">Alter</a></button>&nbsp;|&nbsp;<button><a href="?contactId=${contact.id}">Delete</a></button></td>
            </tr>
            </c:forEach>
        </table>
    </body>

</html>
