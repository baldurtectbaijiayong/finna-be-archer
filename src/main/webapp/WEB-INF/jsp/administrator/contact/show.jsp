<%@ page import = "com.bodejidi.Contact"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%
Contact contact = (Contact)request.getAttribute("contact");
%>
<html>
    <head>
        <title>ContactShow</title>
    </head>
    <body>
        <h4><a href = "list">Back</a></h4>
        <h1>ContactShow</h1>
        <table border = "1">
            <tr>               
                <td>name</td>
                <td>${contact.name}</td>
            </tr>
                <td>mobile</td>
                <td>${contact.mobile}</td>
            </tr>    
            <tr>
                <td>vpmn</td>
                <td>${contact.vpmn}</td>
            </tr>
            <tr>
                <td>email</td>
                <td>${contact.email}</td>
            </tr>
            <tr>
                <td>homeAddress</td>
                <td>${contact.homeAddress}</td>
            </tr>
            <tr>
                <td>officeAddress</td>
                <td>${contact.officeAddress}</td>
            </tr>
            <tr>
                <td>memo</td>
                <td>${contact.memo}</td>
            </tr>
            <tr>
                <td>job</td>
                <td>${contact.job}</td>
            </tr>
            <tr>
                <td>jobLevel</td>
                <td>${contact.jobLevel}</td>
            </tr>
            <tr>
                <td>department</td>
                <td>${contact.department}</td>
            </tr>
        </table>    
    </body>
</html>
