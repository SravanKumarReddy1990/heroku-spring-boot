<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="false"%>
 
<!DOCTYPE html>
 
<html>
<head>
 
<meta charset="UTF-8">
 
<title><spring:message code="label.title" /></title>
</head>
<body>
 
   
    <form method="post" action="">
        <table>
            <tr>
                <td>
                 <strong>
                <spring:message    code="label.userName" />
                </strong>
                </td>
                <td><input name="userName" /></td>
            </tr>
            <tr>
                <td>
                 <strong>
                <spring:message    code="label.password" />
                </strong>
                </td>
                <td><input name="password" /></td>
            </tr>
            <tr>
                <td colspan="2">
                <spring:message code="label.submit" var="labelSubmit"></spring:message>
                <input type="submit" value="${labelSubmit}" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
