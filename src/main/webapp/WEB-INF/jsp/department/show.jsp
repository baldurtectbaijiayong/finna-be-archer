<%@ page import = "java.util.List,com.bodejidi.Contact"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%
List contactList = (List) request.getAttribute("contactList");
%>
<html>
    <head>
        <title>
            department show
        </title>
    </head>
    <body>
        <table border = "1">
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
