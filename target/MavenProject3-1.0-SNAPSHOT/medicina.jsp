<%-- 
    Document   : medicina
    Created on : 16/11/2019, 06:05:53 PM
    Author     : jose_
--%>

<%@page import="Registro.RegistroInternados"%>
<%@page import="Registro.RegistroPacientes"%>
<%@page import="Objetos.Paciente"%>
<%@page import="Objetos.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    if(session.getAttribute("operacion")!=null)session.removeAttribute("operacion");
%>
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
                            Pacientes
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#MostrarPacientes">Ver Pacientes</a>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#ModificarPacientes">Modificar Pacientes</a>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#eliminarPacientes">Eliminar Pacientes</a>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#internarPacientes">Internar Paciente</a>
                        </div>
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

        <div class="container-fluid">
            <br><br>
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="contratos" data-toggle="tab" href="#opcion2" role="tab" aria-controls="home" aria-selected="true">Cirugias Programadas</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="habitaciones" data-toggle="tab" href="#opcion1" role="tab" aria-controls="home" aria-selected="false">Pacientes Internados</a>
                        </li>
                    </ul>
                    <div class="tab-content profile-tab" id="myTabContent">
                        <div class="tab-pane fade show" id="opcion1" role="tabpanel" aria-labelledby="home-tab">
                            <div class="container-fluid">
                                <div class="row"><br></div>
                                <div class="row">
                                    <div class="col-md-4"></div>
                                    <form class="form-inline my-2 my-lg-0 col-md-4">
                                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                                    </form>
                                    <div class="col-md-3"></div>
                                </div>
                                <div class="my-3 p-3 bg-white rounded shadow-sm">

                                    <% for (Paciente paciente : RegistroInternados.getInternadosAsignadosA(session.getAttribute("username").toString())) {%>
                                    <div class="row border-bottom">
                                        <div class="col-md-8">
                                            <div class="media text-muted pt-3">
                                                <p class="media-body pb-3 mb-0 small lh-125 border-gray">
                                                    Nombre Paciente: *nombre paciente<br>
                                                    Fecha Entrada: *Fecha en la que se ingreso*
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-md-4 d-flex justify-content-between align-items-center">
                                            <div class="btn-group">
                                                <a method="get" href="#" class="btn btn-sm btn-outline-secondary">Ver Paciente</a>
                                            </div>       
                                        </div>
                                    </div>

                                    <div class="row border-bottom">
                                        <div class="col-md-8">
                                            <div class="media text-muted pt-3">
                                                <p class="media-body pb-3 mb-0 small lh-125 border-gray">
                                                    Nombre Paciente: *nombre paciente<br>
                                                    Fecha Entrada: *Fecha en la que se ingreso*
                                                </p>
                                            </div>
                                        </div>
                                        <div class="col-md-4 d-flex justify-content-between align-items-center">
                                            <div class="btn-group">
                                                <a method="get" href="#" class="btn btn-sm btn-outline-secondary">Ver Paciente</a>
                                            </div>       
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade show active" id="opcion2" role="tabpanel" aria-labelledby="hombe-tab">
                            <div class="container-fluid">
                                <div class="row"><br></div>
                                <div class="row">
                                    <div class="col-md-4"></div>
                                    <form class="form-inline my-2 my-lg-0 col-md-4">
                                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                                    </form>
                                </div>
                                <div class="my-3 p-3 bg-white rounded shadow-sm">

                                    <div class="row border-bottom">
                                        <div class="col-md-8">
                                            <div class="media text-muted pt-3">
                                                <p class="media-body pb-3 mb-0 small lh-125 border-gray">
                                                    Paciente: *nombre paciente* <br>
                                                    Fecha: *fecha de la cirugia* <br>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>   
                </div>
            </div>
        </div>

                <!--Modal para ver la lista de pacientes-->
        <div class="modal fade" id="MostrarPacientes" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Pacientes</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="crudPacientes" method="get">
                        <div class="modal-body">
                            <div class="row container">
                                <label for="cui">Nombre Paciente</label>
                                <select id="cui" name="cui" class="form-control">
                                    <option value="0">Nuevo Paciente</option>
                                    <% for (Paciente paciente : RegistroPacientes.getListadoPacientes()){%>
                                        <option value="<%=paciente.getCui()%>"><%=paciente.getNombre()%></option>
                                    <% }%>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary" name="crud" value="consulta">Hacer Consulta</button>
                            <button type="submit" class="btn btn-primary" name="crud" value="cirugia">Hacer Cirugia</button>
                            <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <%@include file="Bootstrap/scripts.html" %>

    </body>
</html>