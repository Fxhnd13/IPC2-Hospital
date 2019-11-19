<%-- 
    Document   : errores
    Created on : 5/11/2019, 10:22:59 PM
    Author     : jose_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Error</title>
    </head>
    <body class="jumbotron">
        <br><br>
        <div class="row">
            <div>
                <img src="Images/Icono_Alerta.png" width="100" height="100">
            </div>
            <div class="col-md-4">
                <h1 class="display-3">Error</h1>
            </div>
        </div>
        <div class="row">
            <p class="lead">Se produjo un error al llevar a cabo la acción que desea realizar.</p>
        </div>
        <div class="row">
            <p class="lead">Motivo:</p>
        </div>
        <div class="row">
            <ul>
                <li>
                    <p><%=request.getAttribute("error")%></p>
                </li>
            </ul>
        </div>
        
        <%@include file="Bootstrap/scripts.html" %>
        
    </body>
</html>
