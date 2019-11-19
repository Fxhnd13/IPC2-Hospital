<%-- 
    Document   : formularioMedicamento
    Created on : 16/11/2019, 08:53:37 AM
    Author     : jose_
--%>

<%@page import="Registro.RegistroMedicamentos"%>
<%@page import="Objetos.Medicamento"%>
<%@page import="Objetos.Medicamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Formulario Medicamento</title>
    </head>
    <body>
        <h1 class="display-4">Farmacia</h1>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                  <a class="nav-link" href="farmacia.jsp">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="inventario.jsp">Inventario</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="ventaFarmacia.jsp">Venta</a>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Sesion
                  </a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="logout.jsp">Cerrar Sesion</a>
                    <a class="dropdown-item" href="#">Sesion Activa: <%=session.getAttribute("username")%></a>
                  </div>
                </li>
              </ul>
            </div>
        </nav>
        
        <%if(request.getParameter("idMedicamento")==null){%>
            <div class="row container-fluid">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                  <br>
                  <center>
                      <legend>Formulario Medicamento</legend>  
                  </center>
                  <form action="crudMedicamentos" method="post">
                    <div class="row container">
                        <label for="Nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required>
                    </div>
                      <br>
                    <div class="row container">
                        <label for="cantidad">Cantidad</label>
                        <input type="number" class="form-control" name="cantidad" required>
                    </div>
                      <br>
                    <div class="row container">
                        <label for="cantidadMinima">Cantidad Minima</label>
                        <input type="number" class="form-control" name="cantidadMinima" required>
                    </div>
                      <br>
                    <div class="row container">
                        <label for="precio">Precio</label>
                        <input type="number" class="form-control" name="precio" required step="any">
                    </div>
                    <br>
                    <div class="row container">
                        <label for="costo">Costo</label>
                        <input type="number" class="form-control" name="costo" required step="any">
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <button class="col-md-4 btn btn-primary btn-lg btn-block" type="submit" name="crud" value="crear">Crear</button>
                    </div>
                    <br>
                  </form>
                </div>
            </div>
        <%}else{
        Medicamento medicamento = RegistroMedicamentos.getMedicamento(Integer.parseInt(request.getParameter("idMedicamento")));%>
            <div class="row container-fluid">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                  <br>
                  <center>
                      <legend>Formulario Medicamento</legend>  
                  </center>
                  <form action="crudMedicamentos" method="post">
                    <input type="number" class="form-control" name="idMedicamento" value="<%=medicamento.getId()%>" hidden>
                    <div class="row container">
                        <label for="Nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" value="<%=medicamento.getNombre()%>" required>
                    </div>
                      <br>
                    <div class="row container">
                        <label for="cantidadMinima">Cantidad Minima</label>
                        <input type="number" class="form-control" name="cantidadMinima" required value="<%=medicamento.getCantidadMinima()%>">
                    </div>
                      <br>
                    <div class="row container">
                        <label for="precio">Precio</label>
                        <input type="number" class="form-control" name="precio" required step="any" value="<%=medicamento.getPrecio()%>">
                    </div>
                    <br>
                    <div class="row container">
                        <label for="costo">Costo</label>
                        <input type="number" class="form-control" name="costo" required step="any" value="<%=medicamento.getCosto()%>">
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <button class="col-md-4 btn btn-primary btn-lg btn-block" type="submit" name="crud" value="modificar">Modificar</button>
                    </div>
                    <br>
                  </form>
                </div>
            </div>
        <%}%>
                  
        <%@include file="Bootstrap/scripts.html" %>
    </body>
</html>
