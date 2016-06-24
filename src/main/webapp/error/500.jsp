<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error interno</title>
</head>
<body>
<table border='0' cellpadding='5' cellspacing='0' width='800'>
    <tr bgcolor="#FF0000" align='center' valign='center' height='20'>
        <td>
            <h3><font color='white'>Error interno.</font></h3>
        </td>
    </tr>
    <tr align='right'>
        <td>

        </td>
    </tr>
</table>
<img src="<c:url value="/resources/img/error_interno.png"/>" width="579" height="309" alt="error_interno"/>
<p>Favor de contactar al administrador
</p>
<!--
 <div>
 <% exception.printStackTrace(response.getWriter()); %>
 </div>
-->
</body>
</html>