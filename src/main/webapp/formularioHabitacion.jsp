<%-- 
    Document   : administracion
    Created on : 6/11/2019, 10:28:47 AM
    Author     : jose_
--%>

<%@page import="Utilidades.Utilidades"%>
<%@page import="Registro.RegistroHabitaciones"%>
<%@page import="Objetos.Habitacion"%>
<%@page import="Objetos.Tarifa"%>
<%@page import="Registro.RegistroTarifas"%>
<%@page import="Objetos.Area"%>
<%@page import="Registro.RegistroAreas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    session = request.getSession();
    Habitacion habitacion = RegistroHabitaciones.getHabitacion(request.getParameter("habitacion"));
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Administracion</title>
    </head>
    <body>
        <h1 class="display-4">Administracion</h1>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                  <a class="nav-link" href="administracion.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#" data-toggle="modal" data-target="#modificarHabitaciones">Habitaciones</a>
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
                  <legend>Formulario Habitacion</legend>  
              </center>
              <form action="crudHabitaciones" method="post">
                <div class="row container">
                    <label for="id">Id</label>
                    <input type="number" class="form-control" id="id" name="id" value="<%=habitacion.getId()%>" readonly required>
                </div>
                  <br>
                <div class="row container">
                    <label for="precio">Precio por noche: </label>
                    <input type="number" class="form-control" id="precio" name="precio" value="<%=habitacion.getPrecio()%>" required step="any">
                </div>
                <br>
                <div class="row container">
                    <label for="costo">Costo por dia:</label>
                    <input type="number" class="form-control" id="costo" name="costo" value="<%=habitacion.getCosto()%>" required step="any">
                </div>
                <br>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="disponible" id="checkboxSuscripcion" name="checkboxDisponible" <% if(Utilidades.obtenerBooleano(habitacion.getDisponible())){%>checked<%}%>>
                    <label class="form-check-label" for="checkboxDisponible">
                          Habitacion Disponible
                    </label>
                </div>
                <div class="row">
                    <div class="col-md-4"></div>
                    <button class="col-md-4 btn btn-primary btn-lg btn-block" type="submit" name="crud" value="modificar">Modificar</button>
                </div>
                <br>
              </form>
            </div>
        </div>
                
                
        <%@include file="Bootstrap/scripts.html" %>
    </body>
</html>
