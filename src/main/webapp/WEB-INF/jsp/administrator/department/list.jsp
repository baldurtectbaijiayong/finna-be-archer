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
        <a href="../contact/list">Contact List</a>
        <h1>Department List</h1>
        <a href="create">Create Department</a>
        <table border="1">
            <tr>
                <td>name</td>
                <td>memo</td>
                <td>parent</td>
                <td>address</td>
                <td>operation</td>
            </tr>
            <c:forEach var="department" items="${departmentList}">
            <tr>
                <td><a href="list?departmentId=${department.id}">${department.name}</a></td>
                <td>${department.memo}</td>
                <td>${department.parent}</td>
                <td>${department.address}</td>
                <td><button><a href="list?departmentId=${department.id}">Alter</a></button>&nbsp;|&nbsp;<button><a href="list?departmentId=${department.id}">Delete</a></button></td>
            </tr>
            </c:forEach>
        </table>
	</body>
</html>
