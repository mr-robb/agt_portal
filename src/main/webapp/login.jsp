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
    <title>Portal de Agentes :: Inicio de Sesión</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="factura electronica,facturacion,sistema de facturacion,cfd,cfdi,comprobante fiscal digital, contabilidad electrónica, agentes" />
    <link rel="stylesheet" type="text/css" href="resources/css/login.css"/>
</head>
<body>
<div id="main">
    <div id="logo">Agentes</div>
    <div id="cuerpo">
        <form action="login.action" method="post">
            <fieldset id="basics">
                <legend>Inicio de Sesión</legend>
                <%=errorMessage%>
                <label for="sec_user">Nombre de Usuario</label>
                <input type="text" id="sec_user" name="sec_user" required="required" placeholder="usuario"/>
                <label for="sec_pass">Contraseña</label>
                <input type="password" id="sec_pass" name="sec_pass" required="required" />
                <input type="submit" value="entrar" />
            </fieldset>
            <a class="liga" href="resetPassword">¿olvidó su contraseña?</a>
        </form>
    </div>
    <div id="footer">© Copyright Grupo Financiero Interacciones 2015</div>
</div>
</body>
</html>