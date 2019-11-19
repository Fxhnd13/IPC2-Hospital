<%-- 
    Document   : factura
    Created on : 7/11/2019, 08:58:56 AM
    Author     : jose_
--%>

<%@page import="Registro.RegistroCuentas"%>
<%@page import="Objetos.Cuenta"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Cuenta cuenta = new Cuenta();
    if ((session.getAttribute("cuenta") != null) || request.getParameter("idCuenta") != null) {
        if (session.getAttribute("cuenta") != null) {
            cuenta = (Cuenta) session.getAttribute("cuenta");
        }
        if (request.getParameter("idCuenta") != null) {
            cuenta = RegistroCuentas.getCuenta(Integer.parseInt(request.getParameter("idCuenta")));
            session.setAttribute("cuenta", cuenta);
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Factura</title>
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
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#">Ingresar Nuevo Producto</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#" data-toggle="modal" data-target="#">Inventario</a>
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

        <%if (session.getAttribute("cuenta") != null) {%>
        <div class="container-fluid">
            <center>
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <br>
                    <center>
                        <legend>Formulario Contrato</legend>  
                    </center>
                    <form action="crudCuentas" method="post">
                        <div class="row container-fluid">
                            <label for="fecha">Fecha</label>
                            <input type="date" class="form-control" id="fecha" name="fecha" value="<%=LocalDate.now()%>" required>
                        </div><br>
                        <div class="row container-fluid">
                            <label for="cliente">Nombre Cliente</label>
                            <input type="text" class="form-control" id="cliente" name="cliente">
                        </div><br>
                        <div class="row">
                            <div class="col-md-3"></div><br>
                            <button class="col-md-3 btn btn-primary btn-lg" type="submit" name="crud" value="cuentaFarmacia">Aceptar pago</button>
                            <div class="col-md-1"></div>
                            <button class="col-md-3 btn btn-secondary btn-lg" type="submit" name="crud" value="cancelarCuenta">Cancelar</button> 
                        </div>
                    </form>
                </div>
            </center>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <div class="container-fluid">
                        <div class="row"><br></div>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Codigo</th>
                                    <th scope="col">Tipo</th>
                                    <th scope="col">Descripcion</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">Precio</th>
                                    <th scope="col">Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%for (int i = 0; i < cuenta.getPagos().size(); i++) {%>
                                <tr>
                                    <th scope="row"><%=(i + 1)%></th>
                                    <td><%=cuenta.getPagos().get(i).getTipo()%></td>
                                    <td><%=cuenta.getPagos().get(i).getDescripcion()%></td>
                                    <td><%=cuenta.getPagos().get(i).getCantidad()%></td>
                                    <td><%=cuenta.getPagos().get(i).getPrecio()%></td>
                                    <td><%=cuenta.getPagos().get(i).getTotal()%></td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </fieldset>
    </div>
    <%}
            if (request.getParameter("idCuenta") != null) {%>

    <%}%>
    <%@include file="Bootstrap/scripts.html" %>
</body>
</html>
