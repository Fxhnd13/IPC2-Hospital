<%-- 
    Document   : nuevoContrato
    Created on : 5/11/2019, 10:54:52 PM
    Author     : jose_
--%>

<%@page import="Registro.RegistroVacaciones"%>
<%@page import="Objetos.Vacacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Objetos.Contrato"%>
<%@page import="Registro.RegistroContratos"%>
<%@page import="Objetos.Area"%>
<%@page import="Registro.RegistroAreas"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Formulario Contrato</title>
    </head>
    <body>
        <h1 class="display-4">Recursos Humanos</h1>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="recursosHumanos.jsp">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="nuevoContrato.jsp">Nuevo Contrato</a>
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

        <%if (request.getParameter("idContrato") != null) {
                Contrato contrato = RegistroContratos.getContratoPorId(request.getParameter("idContrato"));
                ArrayList<Vacacion> vacaciones = RegistroVacaciones.getListadoVacaciones(contrato.getId());%>
        <div class="row container-fluid">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <br>
                <center>
                    <legend>Formulario Contrato</legend>  
                </center>
                <form action="crudContratos" method="post">
                    <div class="row container">
                        <label for="id">Id Contrato</label>
                        <input type="number" class="form-control" id="id" name="id" value="<%=contrato.getId()%>" readOnly>
                    </div>
                    <br>
                    <div class="row container">
                        <label for="cui">Cui</label>
                        <input type="number" class="form-control" id="cui" name="cui" value="<%=contrato.getCui()%>" readOnly required>
                    </div>
                    <br>
                    <div class="row container">
                        <label for="nombreCompleto">Nombre Completo</label>
                        <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" value="<%=contrato.getNombreCompleto()%>" required>
                    </div>
                    <br>
                    <div class="row container">
                        <label for="sueldo">Sueldo</label>
                        <input type="number" class="form-control" id="sueldo" name="sueldo" value="<%=contrato.getSueldo()%>" required>
                    </div>
                    <br>
                    <div class="row container">
                        <label for="area">Area</label>
                        <select id="area" name="area" class="form-control">
                            <% for (Area area : RegistroAreas.getListadoAreas()) {%>
                            <option value="<%=area.getId()%>" <%if (area.getId() == contrato.getArea()) {%>selected<%}%>><%=area.getNombre()%></option>
                            <% } %>
                        </select>
                    </div>
                    <br>
                    <h5 class="mb-3">Pago IRTRA e IGSS</h5>
                    <div class="d-block my-3">
                        <div class="custom-control custom-radio">
                            <input id="pagoIgss" name="pagoIgss" type="checkbox" class="custom-control-input" <%if (contrato.getPagoIgss() == 1) {%>checked<%}%>>
                            <label class="custom-control-label" for="pagoIgss">IGSS</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="pagoIrtra" name="pagoIrtra" type="checkbox" class="custom-control-input" <%if (contrato.getPagoIrtra() == 1) {%>checked<%}%>>
                            <label class="custom-control-label" for="pagoIrtra">IRTRA</label>
                        </div>
                    </div>
                    <div class="row container">
                        <label for="fechaDeCreacion">Fecha De Modificacion</label>
                        <input class="form-control" type="date" id="fechaDeCreacion" name="fechaDeCreacion" value="<%=LocalDate.now()%>" required>
                    </div>
                    <br>
                    <%if (!vacaciones.isEmpty()) {%>
                    <h5 class="mb-3">Vacaciones</h5>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion0" name="fechaDeVacacion0" value="<%=vacaciones.get(0).getFecha()%>">
                            </div>
                            <div class="col-md-4">    
                                <input class="form-control" type="date" id="fechaDeVacacion1" name="fechaDeVacacion1" value="<%=vacaciones.get(1).getFecha()%>">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion2" name="fechaDeVacacion2" value="<%=vacaciones.get(2).getFecha()%>">
                            </div>
                            <br>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion3" name="fechaDeVacacion3" value="<%=vacaciones.get(3).getFecha()%>">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion4" name="fechaDeVacacion4" value="<%=vacaciones.get(4).getFecha()%>">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion5" name="fechaDeVacacion5" value="<%=vacaciones.get(5).getFecha()%>">
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion6" name="fechaDeVacacion6" value="<%=vacaciones.get(6).getFecha()%>">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion7" name="fechaDeVacacion7" value="<%=vacaciones.get(7).getFecha()%>">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion8" name="fechaDeVacacion8" value="<%=vacaciones.get(8).getFecha()%>">
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion9" name="fechaDeVacacion9" value="<%=vacaciones.get(9).getFecha()%>">
                            </div>
                        </div>
                    </div>
                    <%}%>
                    <br>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <button class="col-md-4 btn btn-primary btn-lg btn-block" type="submit" value="modificar" name="crud">Modificar Contrato</button>
                    </div>
                    <br>
                </form>
            </div>
        </div>
        <%} else {%>
        <div class="row container-fluid">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <br>
                <center>
                    <legend>Formulario Contrato</legend>  
                </center>
                <form action="crudContratos" method="post">
                    <div class="row container">
                        <label for="cui">Cui</label>
                        <input type="number" class="form-control" id="cui" name="cui" required>
                    </div>
                    <br>
                    <div class="row container">
                        <label for="nombreCompleto">Nombre Completo</label>
                        <input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" required>
                    </div>
                    <br>
                    <div class="row container">
                        <label for="sueldo">Sueldo</label>
                        <input type="number" class="form-control" id="sueldo" name="sueldo" required>
                    </div>
                    <br>
                    <div class="row container">
                        <label for="area">Area</label>
                        <select id="area" name="area" class="form-control">
                            <% for (Area area : RegistroAreas.getListadoAreas()) {%>
                            <option value="<%=area.getId()%>"><%=area.getNombre()%></option>
                            <% }%>
                        </select>
                    </div>
                    <br>
                    <h5 class="mb-3">Pago IRTRA e IGSS</h5>
                    <div class="d-block my-3">
                        <div class="custom-control custom-radio">
                            <input id="pagoIgss" name="pagoIgss" type="checkbox" class="custom-control-input" checked="true">
                            <label class="custom-control-label" for="pagoIgss">IGSS</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="pagoIrtra" name="pagoIrtra" type="checkbox" class="custom-control-input" checked="true">
                            <label class="custom-control-label" for="pagoIrtra">IRTRA</label>
                        </div>
                    </div>
                    <div class="row container">
                        <label for="fechaDeCreacion">Fecha De Creacion</label>
                        <input class="form-control" type="date" id="fechaDeCreacion" name="fechaDeCreacion" value="<%=LocalDate.now()%>" required>
                    </div>
                    <br>
                    <h5 class="mb-3">Vacaciones</h5>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion0" name="fechaDeVacacion0">
                            </div>
                            <div class="col-md-4">    
                                <input class="form-control" type="date" id="fechaDeVacacion1" name="fechaDeVacacion1">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion2" name="fechaDeVacacion2">
                            </div>
                            <br>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion3" name="fechaDeVacacion3">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion4" name="fechaDeVacacion4">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion5" name="fechaDeVacacion5">
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion6" name="fechaDeVacacion6">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion7" name="fechaDeVacacion7">
                            </div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion8" name="fechaDeVacacion8">
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <div class="col-md-4"></div>
                            <div class="col-md-4">
                                <input class="form-control" type="date" id="fechaDeVacacion9" name="fechaDeVacacion9">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <button class="col-md-4 btn btn-primary btn-lg btn-block" type="submit" value="crear" name="crud">Crear Contrato</button>
                    </div>
                    <br>
                </form>
            </div>
        </div>
        <%}%>
        <%@include file="Bootstrap/scripts.html" %>

    </body>
</html>
