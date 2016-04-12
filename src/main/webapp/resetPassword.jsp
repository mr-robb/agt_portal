<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String errorMessage = (String)request.getAttribute("errorMessage");
    if( errorMessage == null ){
        errorMessage = "";
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Portal de Agentes :: recuperar contraseña</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="factura electronica,facturacion,sistema de facturacion,cfd,cfdi,comprobante fiscal digital, contabilidad electrónica, agentes" />
    <link rel="stylesheet" type="text/css" href="resources/css/login.css"/>
</head>
<body>
<div id="main">
    <div id="logo">Agentes</div>
    <div id="cuerpo">
        <form action="resetPassword" method="post">
            <fieldset id="basics">
                <legend>Recuperación de contraseña</legend>
                <%=errorMessage%>
                <label for="username">Nombre de Usuario</label>
                <input type="text" id="username" name="username" required="required" placeholder="usuario"/>
                <input type="submit" value="recuperar" />
            </fieldset>
            <a  class="liga" href="login.jsp">inicio</a>
        </form>
    </div>
    <div id="footer">© Copyright Grupo Financiero Interacciones 2015</div>
</div>
</body>
</html>