<%@ page import="java.util.List"%>
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
        <table border = "1">
            <h1>All Contacts</h1>
            <tr>
                <td>name</td>
                <td>mobile</td>
                <td>department</td>
            
            </tr>

            <c:forEach var = "contact" items = "${contactList}">
            <tr>
                <td>${contact.name}</td>
                <td>${contact.mobile}</td>
                <td>${contact.department}</td>
            </tr>
            </c:forEach>
        </table>
    </body>

</html>
