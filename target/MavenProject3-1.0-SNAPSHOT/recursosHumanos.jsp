<%-- 
    Document   : recursosHumanos
    Created on : 5/11/2019, 10:05:54 AM
    Author     : jose_
--%>

<%@page import="Registro.RegistroHabitaciones"%>
<%@page import="Objetos.Habitacion"%>
<%@page import="Registro.RegistroAreas"%>
<%@page import="Registro.RegistroContratos"%>
<%@page import="Objetos.Contrato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" type="text/css"/>
        <title>Recursos Humanos</title>
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
                            <a class="nav-link active" id="contratos" data-toggle="tab" href="#opcion2" role="tab" aria-controls="home" aria-selected="true">Contratos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="habitaciones" data-toggle="tab" href="#opcion1" role="tab" aria-controls="home" aria-selected="false">Habitaciones</a>
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
                                    <%for(Habitacion habitacion : RegistroHabitaciones.getListadoHabitacionesModificables()){%>
                                        <div class="row border-bottom">
                                            <div class="col-md-8">
                                                <div class="media text-muted pt-3">
                                                  <p class="media-body pb-3 mb-0 small lh-125 border-gray">
                                                    No: <%=habitacion.getId()%><br>
                                                    Estado: <%if(Utilidades.Utilidades.obtenerBooleano(habitacion.getDisponible())){%> Habilitada<%}else{%>Deshabilitada<%}%>
                                                  </p>
                                                </div>
                                            </div>
                                            <div class="col-md-4 d-flex justify-content-between align-items-center">
                                                <div class="btn-group">
                                                    <a method="post" href="crudHabitaciones?crud=disponibilidad&id=<%=habitacion.getId()%>" class="btn btn-sm btn-outline-secondary">Habilitar/Deshabilitar Disponibilidad</a>
                                                </div>       
                                            </div>
                                        </div>
                                    <%}%>
                                    
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade show active" id="opcion2" role="tabpanel" aria-labelledby="hombe-tab">
                            <div class="container-fluid">
                                <div class="row"><br></div>
                                <div class="row">
                                    <div class="col-md-1"></div>
                                    <form class="form-inline my-2 my-lg-0 col-md-4">
                                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                                    </form>
                                    <div class="col-md-3">
                                        <button class="btn btn-outline-success my-2 my-sm-0" data-toggle="modal" data-target="#terminarContrato">Terminar Contrato</button>
                                    </div>
                                    <form action="crudContratos" method="post"  class="col-md-3">
                                        <div>
                                            <button class="btn btn-outline-success my-2 my-sm-0" value="pagos" name="crud">Pagar Contratos</button>
                                        </div>
                                    </form>
                                </div>
                                <div class="my-3 p-3 bg-white rounded shadow-sm">
                                    <%for(Contrato contrato : RegistroContratos.getListadoContratos()){%>
                                        <div class="row border-bottom">
                                            <div class="col-md-8">
                                                <div class="media text-muted pt-3">
                                                  <p class="media-body pb-3 mb-0 small lh-125 border-gray">
                                                    Cui: <%=contrato.getCui()%><br>
                                                    Nombre: <%=contrato.getNombreCompleto()%><br>
                                                    Sueldo: <%=contrato.getSueldo()%><br>
                                                    Area: <%=RegistroAreas.getNombreArea(contrato.getArea())%>
                                                  </p>
                                                </div>
                                            </div>
                                            <div class="col-md-4 d-flex justify-content-between align-items-center">
                                                <div class="btn-group">
                                                    <a method="post" href="nuevoContrato.jsp?idContrato=<%=contrato.getId()%>" class="btn btn-sm btn-outline-secondary">Modificar Contrato</a>
                                                </div>       
                                            </div>
                                        </div>
                                    <%}%>
                                </div>
                            </div>
                        </div>
                    </div>   
                </div>
            </div>
        </div>
                                
        <!--Modal para terminar un contrato-->
        <div class="modal fade" id="terminarContrato" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Terminar Contrato</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            <form action="crudContratos" method="post">
              <div class="modal-body">
                <div class="row container">
                    <label class="text-muted" for="idContrato">Nombre Del empleado</label>
                        <select id="idContrato" name="idContrato" class="form-control">
                            <option value="0">...</option>
                            <% for(Contrato contrato: RegistroContratos.getListadoContratos()){%>
                                <option value="<%=contrato.getId()%>"><%=contrato.getNombreCompleto()%></option>
                            <%}%>
                        </select>
                </div>
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary" name="crud" value="eliminar">Eliminar</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
              </div>
            </form>
            </div>
          </div>
        </div>
        
        <%@include file="Bootstrap/scripts.html" %>
    </body>
</html>
