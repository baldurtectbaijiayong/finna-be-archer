<%@ page import = "java.util.List,com.bodejidi.Contact,com.bodejidi.Department"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%
List contactList = (List) request.getAttribute("contactList");
Department department = (Department) request.getAttribute("department");
%>
<html>
    <head>
        <title>
            department show
        </title>
    </head>
    <body>
        <h1 fontsize = "15" align = "center">Department Name: ${department.name}</h1>
        <table  align = "center">
            <tr>
                <td>parent</td>
                <td>${department.parent}</td>
            </tr>
            <tr>
                <td>address</td>
                <td>${department.address}</td>
            </tr>
            <tr>
                <td>memo</td>
                <td>${department.memo}</td>
            </tr>          
                       
        </table>
        </br>
        </br>
        </br>
        <table border = "1" align = "center">
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
