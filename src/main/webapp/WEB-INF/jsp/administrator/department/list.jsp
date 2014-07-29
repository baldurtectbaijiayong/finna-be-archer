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
        <form action="" method="POST">
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
                <td><input type="hidden" name="hiddenDepartmentId" value="${department.id}"/><button><a href="list?departmentId=${department.id}">Alter</a></button>&nbsp;|&nbsp;<input type="submit" name="action" value="Delete"/></td>
            </tr>
            </c:forEach>
        </table>
        </form>
	</body>
</html>
