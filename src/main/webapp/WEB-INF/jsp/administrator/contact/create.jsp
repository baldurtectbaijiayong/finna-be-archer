<% 
    String departmentParameter = (String)request.getAttribute("department");
%>
<html>
    <head>
        <title>Create Contact</title>
    </head>
    <body>
        <a href="list">Contact List</a>
        <h1>Create Contact</h1>
        <form action="" method="POST">
            <table border="1">
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="contactName"/></td>
                </tr>
                <tr>
                    <td>Mobile</td>
                    <td><input type="text" name="contactMobile"/></td>
                </tr>
                <tr>
                    <td>Vpmn</td>
                    <td><input type="text" name="contactVpmn"/></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="text" name="contactEmail"/></td>
                </tr>
                <tr>
                    <td>HomeAddress</td>
                    <td><input type="text" name="contactHomeAddress"/></td>
                </tr>
                <tr>
                    <td>OfficeAddress</td>
                    <td><input type="text" name="contactOfficeAddress"/></td>
                </tr>
                <tr>
                    <td>Job</td>
                    <td><input type="text" name="contactJob"/></td>
                </tr>
                <tr>
                    <td>JobLevel</td>
                    <td><input type="text" name="contactJobLevel"/></td>
                </tr>
                <tr>
                    <td>Department</td>
                    <td><input type="text" name="contactDepartment" value="<%=  departmentParameter %>"/></td>
                </tr>
            </table>
            <input type="submit" name="action" value="Create"/>
        </form>
    </body>
</html>