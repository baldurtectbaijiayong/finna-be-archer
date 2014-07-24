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
    <h1 align = "center"> <font size = "15">Department Name: ${department.name}</font></h1>
        <table  align = "center">
            <tr>
                <td><font size = "10">parent</font></td>
                <td><font size = "10">${department.parent}</font></td>
            </tr>
            <tr>
                <td><font size = "10">address</font></td>
                <td><font size = "10">${department.address}</font></td>
            </tr>
            <tr>
                <td><font size = "10">memo</font></td>
                <td><font size = "10">${department.memo}</font></td>
            </tr>          
                       
        </table>
        </br>
        </br>
        </br>
        <table border = "1" align = "center">
            <tr>
                <td><font size = "8">name</font></td>
                <td><font size = "8">mobile</font></td>
                <td><font size = "8">department</font></td>
            
            </tr>

            <c:forEach var = "contact" items = "${contactList}">
            <tr>
                <td><font size = "8">${contact.name}</font></td>
                <td><font size = "8">${contact.mobile}</font></td>
                <td><font size = "8">${contact.department}</font></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
