<%-- 
    Document   : nuevoUsuario
    Created on : 6/11/2019, 11:54:53 AM
    Author     : jose_
--%>

<%@page import="Objetos.Usuario"%>
<%@page import="Registro.RegistroUsuarios"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Registro.RegistroContratos"%>
<%@page import="Objetos.Contrato"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    ArrayList<Contrato> contratos = RegistroContratos.getListadoContratosSinUsuarioActivo();
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Formulario Usuario</title>
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
                    <a class="dropdown-item" href="#">Cerrar Sesion</a>
                    <a class="dropdown-item" href="#">Sesion Activa: *Nombre Usuario Activo*</a>
                  </div>
                </li>
              </ul>
            </div>
        </nav>
        
        <div class="row container-fluid">
            <div class="col-md-3"></div>
            <% if(request.getParameter("idContrato")==null){%>
                <div class="col-md-6">
                  <br>
                  <center>
                      <legend>Formulario Usuario</legend>
                  </center>
                  <form action="crudUsuarios" method="post">
                    <div class="row container">
                        <label for="idContrato">Nombre</label>
                        <select id="idContrato" name="idContrato" class="form-control">
                                <option value="0">...</option>
                                <% for(Contrato contrato : contratos){%>
                                <option value="<%=contrato.getId()%>"><%=contrato.getNombreCompleto()%></option>
                                <%}%>
                        </select>
                    </div>
                      <br>
                    <div class="row container">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username" value="" required>
                    </div>
                      <br>
                      <div class="row container">
                          <label for="password1">Contraseña</label>
                          <input type="password" class="form-control" id="password1" name="password1" value="" required>
                      </div>
                      <br>
                      <div class="row container">
                          <label for="password2">Confirmar Contraseña</label>
                          <input type="password" class="form-control" id="password2" name="password2" value="" required>
                      </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <button class="col-md-4 btn btn-primary btn-lg btn-block" type="submit" name="crud" value="crear">Crear</button>
                    </div>
                    <br>
                  </form>
                </div>
            <%}else{
                Usuario usuario = RegistroUsuarios.getUsuario(Integer.parseInt(request.getParameter("idContrato")));
                %>
                <div class="col-md-6">
                  <br>
                  <center>
                      <legend>Formulario Usuario</legend>
                  </center>
                  <form action="crudUsuarios" method="post">
                      <div class="row container">
                        <label for="idContrato">Contrato</label>
                        <input type="text" class="form-control" id="idContrato" name="idContrato" value="<%=usuario.getIdContrato()%>" readOnly required>
                    </div>
                      <br>
                    <div class="row container">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" name="username" value="<%=usuario.getUsername()%>" required>
                    </div>
                      <br>
                      <div class="row container">
                          <label for="password1">Contraseña</label>
                          <input type="password" class="form-control" id="password1" name="password1" value="" required>
                      </div>
                      <br>
                      <div class="row container">
                          <label for="password2">Confirmar Contraseña</label>
                          <input type="password" class="form-control" id="password2" name="password2" value="" required>
                      </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <button class="col-md-4 btn btn-primary btn-lg btn-block" type="submit" name="crud" value="modificar">Modificar</button>
                    </div>
                    <br>
                  </form>
                </div>
            <%}%>
        </div>
                
        <%@include file="Bootstrap/scripts.html"%>
                   
    </body>
</html>
