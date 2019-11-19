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
        <div class="Sign-in-format">
            <form class="form-signin" action="login" method="post">
                <br><br><h2 class="h3 mb-3 font-weight-normal ">Iniciar Sesion</h2><br><br>
                <label for="username" class="sr-only">Username</label>
                <input type="text" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
                <label for="password" class="sr-only">Password</label>
                <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
                <p class="mt-5 mb-3 text-muted">&copy; 2019</p>
            </form>
        </div>
        <%@include file="Bootstrap/scripts.html" %>
    </body>
</html>
