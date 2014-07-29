<%@ page import="java.util.List, com.bodejidi.Department, com.bodejidi.Contact"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
List<Contact> contactList=(List<Contact>) request.getAttribute("contactList");
Department department=(Department) request.getAttribute("department"); 
%>
<html>
    <head>
        <title>Department Show</title>
    </head>
    <body>
        <a href="list">Department List</a>
        <h1>Department Name: ${department.name}</h1>
        <form action="" method="POST" >
            <table border="1">
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="departmentName" value="${department.name}" /></td>
                </tr>
                <tr>
                    <td>Parent</td>
                    <td><input type="text" name="departmentParent" value="${department.parent}" /></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><input type="text" name="departmentAddress" value="${department.address}" /></td>
                </tr>
                <tr>
                    <td>Memo</td>
                    <td><input type="text" name="departmentMemo" value="${department.memo}" /></td>
                </tr>
            </table>
            <input type="hidden" name="hiddenDepartmentId" value="${department.id}"/>
            <input type="submit" name="action" value="Update"/>
            <input type="submit" name="action" value="Delete"/>
        </form>
        <br/>
        <br/>
        <h3>${department.name} Contacts List</h3>
       
        <h7><a href="../contact/create?department=${department.name}">Create</a></h7>
        <table border="1">
            <tr>
                <td>name</td>
                <td>mobile</td>
                <td>department</td>
                <td>operation</td>
            </tr>
            
            <c:forEach var="contact" items="${contactList}">
            <tr>
                <td><a href="../contact/list?contactId=${contact.id}">${contact.name}</td>
                <td>${contact.mobile}</td>
                <td>${contact.department}</td>
                <td><a href="">update</a>&nbsp;|&nbsp;<a href="">delete</a></td>
            <tr>
            </c:forEach>
            
        </table>
    </body>
</html>
