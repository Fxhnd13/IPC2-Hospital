<%-- 
    Document   : index
    Created on : 24/09/2019, 11:10:29 AM
    Author     : jose_
--%>


<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <link rel="stylesheet" href="Bootstrap/css/signin.css" type="text/css"/>
    </head>
    
    
    <body>
        <ul>
            <li>
                <a href="recursosHumanos.jsp">Recursos Humanos</a>
            </li>
            <li>
                <a href="administracion.jsp">Administracion</a>
            </li>
            <li>
                <a href="login.jsp">Login</a>
            </li>
            <li>
                <a href="nuevoContrato.jsp">Nuevo Contrato</a>
            </li>
            <li>
                <a href="nuevoUsuario.jsp">Nuevo Usuario</a>
            </li>
            <li>
                <a href="farmacia.jsp">Farmacia</a>
            </li>
            <li>
                <a href="ventaFarmacia.jsp">Venta Farmacia</a>
            </li>
            <li>
                <a href="factura.jsp">Factura</a>
            </li>
        </ul>
        
        <%@include file="Bootstrap/scripts.html" %>
    </body>
</html>
