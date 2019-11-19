<%-- 
    Document   : Consulta
    Created on : 16/11/2019, 11:20:15 PM
    Author     : jose_
--%>

<%@page import="Registro.RegistroPacientes"%>
<%@page import="Objetos.Paciente"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Medico</title>
    </head>
    <body>
        <h1 class="display-4">Medico</h1>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="medicina.jsp">Inicio</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Sesion
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="logout.jsp?username=<%=session.getAttribute("username")%>">Cerrar Sesion</a>
                            <a class="dropdown-item" href="#">Sesion Activa: <%=session.getAttribute("username")%></a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="row container-fluid">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <br>
                <center>
                    <legend>Formulario Consulta</legend>  
                </center>
                <form action="crudConsultas" method="post">
                    <div class="row container">
                        <input type="number" class="form-control" id="cuiPaciente" name="cuiPaciente" value="<%=request.getParameter("cui")%>" hidden>
                    </div>
                    <div class="row container">
                        <label for="fecha">Fecha</label>
                        <input type="date" class="form-control" id="fecha" name="fecha" value="<%=LocalDate.now()%>" required>
                    </div>
                    <br>
                    <div class="row container form-group">
                        <label for="motivo">Motivo</label>
                        <textarea class="form-control" id="motivo" name="motivo" rows="5"></textarea>
                    </div>
                    <br>
                    <div class="row container form-group">
                        <label for="causa">Posibles Causas</label>
                        <textarea class="form-control" id="causa" name="causa" rows="5"></textarea>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <button class="col-md-4 btn btn-primary btn-lg btn-block" type="submit" value="crear" name="crud">Crear Consulta</button>
                    </div>
                    <br>
                </form>
            </div>
        </div>


    </body>
</html>
