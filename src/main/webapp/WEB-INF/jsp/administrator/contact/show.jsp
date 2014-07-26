<%@ page import = "com.bodejidi.Contact"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<% Contact contact = (Contact) request.getAttribute("contact"); %>
<html>
    <head>
        <title>Contact Show</title>
    </head>
    <body>
        <a href = "list">Back</a>
        <h1>Contact Show</h1>
        <form action = "" method = "POST" >
            <table border = "1">
                <tr>               
                    <td>name</td>
                    <td><input type = "text" name = "contactName" value = "${contact.name}"/></td>
                </tr>
                    <td>mobile</td>
                    <td><input type = "text" name = "contactMobile" value = "${contact.mobile}"/></td>
                </tr>    
                <tr>
                    <td>vpmn</td>
                    <td><input type = "text" name = "contactVpmn" value = "${contact.vpmn}"/></td>
                </tr>
                <tr>
                    <td>email</td>
                    <td><input type = "text" name = "contactEmail" value = "${contact.email}"/></td>
                </tr>
                <tr>
                    <td>homeAddress</td>
                    <td><input type = "text" name = "contactHomeAddress" value = "${contact.homeAddress}"/></td>
                </tr>
                <tr>
                    <td>officeAddress</td>
                    <td><input type = "text" name = "contactOfficeAddress" value = "${contact.officeAddress}"/></td>
                </tr>
                <tr>
                    <td>memo</td>
                    <td><input type = "text" name = "contactMemo" value = "${contact.memo}"/></td>
                </tr>
                <tr>
                    <td>job</td>
                    <td><input type = "text" name = "contactJob" value = "${contact.job}"/></td>
                </tr>
                <tr>
                    <td>jobLevel</td>
                    <td><input type = "text" name = "contactJobLevel" value = "${contact.jobLevel}"/></td>
                </tr>
                <tr>
                    <td>department</td>
                    <td><input type = "text" name = "contactDepartment" value = "${contact.department}"/></td>
                </tr>
            </table>
            <input type = "submit" name = "action" value = "Update"/>
            <input type = "submit" name = "action" value = "Delete"/>
        </form>
    </body>
</html>
