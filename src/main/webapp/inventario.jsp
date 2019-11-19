<%-- 
    Document   : inventario
    Created on : 7/11/2019, 11:11:36 AM
    Author     : jose_
--%>

<%@page import="Objetos.Medicamento"%>
<%@page import="Registro.RegistroMedicamentos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% if (session.getAttribute("cuenta") != null) {
        session.removeAttribute("cuenta");
    }%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Inventario</title>
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
                        <a class="nav-link" href="formularioMedicamento.jsp">Ingresar Nuevo Producto</a>
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

        <div class="row container-fluid">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <br>
                <center>
                    <legend>Inventario</legend>
                </center>
                <form action="crudMedicamentos" method="post">
                    <%for (Medicamento medicamento : RegistroMedicamentos.getListadoMedicamentos(4)) {%>
                    <div class="border-bottom">
                        <div class="row container">
                            <label class="text-muted">Nombre: <strong><%=medicamento.getNombre()%></strong></label>
                        </div>
                        <div class="row container">
                            <label class="text-muted">Cantidad que hay registrada en el sistema: <strong><%=medicamento.getCantidad()%></strong> </label>
                        </div>
                        <div class="row container">
                            <label class="text-muted" for="<%=medicamento.getId()%>">Cantidad en fisico que hay: </label><div class="col-md-1"></div>
                            <input type="number" class="form-control col-md-5" id="<%=medicamento.getId()%>" name="<%=medicamento.getId()%>" value="" required>
                        </div><br>
                    </div>
                    <%}%>
                    <br>
                    <div class="row">
                        <div class="col-md-1"></div>
                        <button class="col-md-4 btn btn-primary btn-lg" type="submit" name="crud" value="inventario">Registrar</button>
                        <div class="col-md-1"></div>
                        <button class="col-md-4 btn btn-secondary btn-lg" type="button" href="farmacia.jsp">Cancelar</button>
                    </div><br>
                </form>
            </div>
        </div>

        <%@include file="Bootstrap/scripts.html"%>
    </body>
</html>
