<%-- 
    Document   : farmacia
    Created on : 6/11/2019, 01:15:00 PM
    Author     : jose_
--%>

<%@page import="Objetos.Medicamento"%>
<%@page import="Registro.RegistroMedicamentos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% if(session.getAttribute("cuenta")!=null){session.removeAttribute("cuenta");}%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Farmacia</title>
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

        <div class="container-fluid">
            <fieldset>
                <%if (!RegistroMedicamentos.getListadoMedicamentos(3).isEmpty()) {%>
                <legend><br>Existencias Actuales</legend>
                <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-10">
                        <div class="container-fluid">
                            <div class="row"><br></div>
                            <div class="row">
                                <div class="col-md-4"></div>
                                <form class="form-inline my-2 my-lg-0 col-md-4">
                                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                                </form>
                                <div class="col-md-3"></div>
                            </div><br>
                            <table class="table">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">Codigo</th>
                                        <th scope="col">Producto</th>
                                        <th scope="col">Precio</th>
                                        <th scope="col">Cantidad en existencias</th>
                                        <th scope="col">Accion</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%for (Medicamento medicamento : RegistroMedicamentos.getListadoMedicamentos(1)) {%>
                                    <tr class="table-danger">
                                        <th scope="row"><%=medicamento.getId()%></th>
                                        <td><%=medicamento.getNombre()%></td>
                                        <td><%=medicamento.getPrecio()%></td>
                                        <td><%=medicamento.getCantidad()%></td>
                                        <td>
                                            <center>
                                                <form action="crudMedicamentos" method="post">
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-outline-secondary" type="submit" id="button-addon1" name="crud" value="modificarCantidad">Aumentar cantidad en</button>
                                                    </div>
                                                    <input type="number" hidden name="idMedicamento" value="<%=medicamento.getId()%>">
                                                    <input type="number" class="form-control" placeholder="" name="cantidad">
                                                </div>
                                            </form>
                                            </center>
                                        </td>
                                    </tr>
                                <%}%>

                                <%for (Medicamento medicamento : RegistroMedicamentos.getListadoMedicamentos(2)) {%>
                                <tr>
                                    <th scope="row"><%=medicamento.getId()%></th>
                                    <td><%=medicamento.getNombre()%></td>
                                    <td><%=medicamento.getPrecio()%></td>
                                    <td><%=medicamento.getCantidad()%></td>
                                    <td>
                                        <center>
                                            <form action="crudMedicamentos" method="post">
                                                <div class="input-group">
                                                    <div class="input-group-prepend">
                                                        <button class="btn btn-outline-secondary" type="submit" id="button-addon1" name="crud" value="modificarCantidad">Aumentar cantidad en</button>
                                                    </div>
                                                    <input type="number" hidden name="idMedicamento" value="<%=medicamento.getId()%>">
                                                    <input type="number" class="form-control" name="cantidad">
                                                </div>
                                            </form>
                                        </center>
                                    </td>
                                </tr>
                                <%}%>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <%}%>
            </fieldset>
        </div>

        <%@include file="Bootstrap/scripts.html"%>
    </body>
</html>
