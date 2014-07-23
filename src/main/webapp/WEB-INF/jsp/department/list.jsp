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
		<table>
		<tr>
			<td>name</td>
			<td>memo</td>
			<td>parent</td>
			<td>address</td>
		</tr>
		<c:forEach var="department" items="${departmentList}">
		<tr>
			<a href="show">
			<td>${department.name}</td>
			</a>
			<td>${department.memo}</td>
			<td>${department.parent}</td>
			<td>${department.address}</td>
		</tr>
		</c:forEach>
		</table>
	</body>
</html>
