<%@page import="java.util.List,com.bodejidi.Department" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<Department> departmentList = (List<Department>) request.getAttribute("departmentList");
%>
<html>
    <head>
        <title>
            Department List
        </title>
    </head>
    <body>
        <a href="../contact/list">Back</a>
        <h1>Department List</h1>
        <table border="1">
            <tr>
                <td>name</td>
                <td>memo</td>
                <td>parent</td>
                <td>address</td>
            </tr>
            <c:forEach var="department" items="${departmentList}">
            <tr>
                <td><a href="show?departmentId=${department.id}">${department.name}</a></td>
                <td>${department.memo}</td>
                <td>${department.parent}</td>
                <td>${department.address}</td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
