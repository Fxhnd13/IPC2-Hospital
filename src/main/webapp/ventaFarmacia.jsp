<%-- 
    Document   : ventaFarmacia
    Created on : 6/11/2019, 06:33:27 PM
    Author     : jose_
--%>

<%@page import="Objetos.Medicamento"%>
<%@page import="Registro.RegistroMedicamentos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Venta</title>
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
                  <legend>Facturar</legend>
              </center>
              <form action="crudMedicamentos" method="post">
                <div class="row container">
                    <label for="idMedicamento">Medicamentos</label>
                    <select id="idMedicamento" name="idMedicamento" class="form-control">
                        <option value="0">...</option>
                        <%for (Medicamento medicamento : RegistroMedicamentos.getListadoMedicamentos(3)) {%>
                            <option value="<%=medicamento.getId()%>"><%=medicamento.getNombre()%></option>
                        <%}%>
                    </select>
                </div>
                  <br>
                <div class="row container">
                    <label for="cantidad">Cantidad</label>
                    <input type="number" class="form-control" id="cantidad" name="cantidad" value="">
                </div><br>
                <div class="row">
                    <div class="col-md-1"></div>
                    <button class="col-md-4 btn btn-primary btn-lg" type="submit" name="crud" value="agregarVenta">Agregar a la cuenta</button>
                    <div class="col-md-1"></div>
                    <button class="col-md-4 btn btn-secondary btn-lg" type="submit" name="crud" value="hacerCuenta">Ver Factura</button> 
                </div>
                <br>
              </form>
            </div>
        </div>
        
        <%@include file="Bootstrap/scripts.html"%>
    </body>
</html>
