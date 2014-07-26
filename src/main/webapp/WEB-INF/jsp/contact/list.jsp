<%@ page import="java.util.List,com.bodejidi.Contact,com.bodejidi.Department"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<% 
List contactList = (List) request.getAttribute("contactList");
List departmentList = (List) request.getAttribute("departmentList");
%>

<html>
    <head>
        <title>
            All Contacts
        </title>
    </head>
    <body>
        <table border = "1">
            <h1>All Contacts</h1>
            <tr>
                <td>name</td>
                <td>mobile</td>
                <td>department</td>
            
            </tr>

            <c:forEach var = "contact" items= "${contactList}">
            <tr>
                <td><a href = "show?contactId=${contact.id}">${contact.name}</a></td>
                <td>${contact.mobile}</td>
                <td><a href = "../department/show?departmentId=${contact.departmentId}">${contact.department}</td>
            </tr>
            </c:forEach>
        </table>
    </body>

</html>
