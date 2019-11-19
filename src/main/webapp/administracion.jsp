<%-- 
    Document   : administracion
    Created on : 6/11/2019, 10:28:47 AM
    Author     : jose_
--%>

<%@page import="Objetos.Contrato"%>
<%@page import="Registro.RegistroContratos"%>
<%@page import="Objetos.Usuario"%>
<%@page import="Registro.RegistroUsuarios"%>
<%@page import="Objetos.Area"%>
<%@page import="Objetos.Habitacion"%>
<%@page import="Objetos.Tarifa"%>
<%@page import="Registro.RegistroTarifas"%>
<%@page import="Registro.RegistroAreas"%>
<%@page import="Registro.RegistroHabitaciones"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


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
        
        <div class="accordion" id="accordionExample">
            <div class="card">
              <div class="card-header" id="headingOne">
                <h2 class="mb-0">
                  <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                     Areas
                  </button>
                </h2>
              </div>
              <div id="collapseOne" class="collapse show" aria-labelledby="headingOne" data-parent="#accordionExample">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <lu>
                            <li>
                                <a class="" href="#" data-toggle="modal" data-target="#crearArea">Crear Area</a>
                            </li>
                            <li>
                                <a class="" href="#" data-toggle="modal" data-target="#modificarArea">Modificar Area</a>
                            </li>
                            <li>
                                <a class="" href="#" data-toggle="modal" data-target="#eliminarArea">Eliminar Area</a>
                            </li>
                        </lu>
                    </div>
                </div>
              </div>
            </div>
            
            <div class="card">
              <div class="card-header" id="headingTwo">
                <h2 class="mb-0">
                  <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                     Usuarios
                  </button>
                </h2>
              </div>
              <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionExample">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <lu>
                            <li>
                                <a href="nuevoUsuario.jsp">Crear Usuario</a>
                            </li>
                            <li>
                                <a href="#" data-toggle="modal" data-target="#modificarUsuario">Modificar Usuario</a>
                            </li>
                            <li>
                                <a href="#" data-toggle="modal" data-target="#eliminarUsuario">Eliminar Usuario</a>
                            </li>
                        </lu>
                    </div>
                </div>
              </div>
            </div>
            
            <div class="card">
              <div class="card-header" id="headingThree">
                <h2 class="mb-0">
                  <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                    Tarifas
                  </button>
                </h2>
              </div>
              <div id="collapseThree" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <lu>
                            <li>
                                <a href="#" data-toggle="modal" data-target="#crearTarifa">Crear Tarifa</a>
                            </li>
                            <li>
                                <a href="#" data-toggle="modal" data-target="#modificarTarifa">Modificar Tarifa</a>
                            </li>
                            <li>
                                <a href="#" data-toggle="modal" data-target="#eliminarTarifa">Eliminar Tarifa</a>
                            </li>
                        </lu>
                    </div>
                </div>
              </div>
            </div>
            
            <div class="card">
              <div class="card-header" id="headingThree">
                <h2 class="mb-0">
                  <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseFour" aria-expanded="false" aria-controls="collapseThree">
                    Habitaciones
                  </button>
                </h2>
              </div>
              <div id="collapseFour" class="collapse" aria-labelledby="headingThree" data-parent="#accordionExample">
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-1"></div>
                        <lu>
                            <li>
                                <a href="#" data-toggle="modal" data-target="#crearHabitacion">Crear Habitacion</a>
                            </li>
                            <li>
                                <a href="#" data-toggle="modal" data-target="#modificarHabitacion">Modificar Habitacion</a>
                            </li>
                            <li>
                                <a href="#" data-toggle="modal" data-target="#eliminarHabitacion">Eliminar Habitacion</a>
                            </li>
                        </lu>
                    </div>
                </div>
              </div>
            </div>
        </div>
        
        <!--A partir de aqui van a encontrarse todos los modales--> 
        
        <!--Modal para crear una nueva area-->
        <div class="modal fade" id="crearArea" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Crear Area</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            <form action="crudAreas" method="post">
              <div class="modal-body">
                <div class="row container">
                    <label for="nombreArea" class="text-muted">Nombre</label>
                    <input type="text" class="form-control" id="nombreArea" name="nombreArea" value="" required>
                </div>
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary" name="crud" value="crear">Crear</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
              </div>
            </form>
            </div>
          </div>
        </div>
        
        <!--Modal para modificar un area ya creada-->
        <div class="modal fade" id="modificarArea" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modificar Area</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            <form action="crudAreas" method="post">
              <div class="modal-body">
                <div class="row container">
                    <label class="text-muted" for="area">Area</label>
                        <select id="area" name="area" class="form-control">
                            <%for (Area area : RegistroAreas.getListadoAreas()) {%>
                                <option value="<%=area.getId()%>"><%=area.getNombre()%></option>
                            <%}%>
                        </select>
                </div><br>
                <div class="row container">
                    <label for="nombreAreaNuevo" class="text-muted">Nombre Nuevo</label>
                    <input type="text" class="form-control" id="nombreAreaNuevo" name="nombreAreaNuevo" value="" required>
                </div>
              </div><br>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary" value="modificar" name="crud">Modificar</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
              </div>
            </form>
            </div>
          </div>
        </div>
        
        <!--Modal para eliminar un area ya creada-->
        <div class="modal fade" id="eliminarArea" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Elimnar Area</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <form action="crudAreas" method="post">
              <div class="modal-body">
                <div class="row container">
                    <label class="text-muted" for="area">Area</label>
                        <select id="area" name="area" class="form-control">
                            <%for (Area area : RegistroAreas.getListadoAreas()) {%>
                                <option value="<%=area.getId()%>"><%=area.getNombre()%></option>
                            <%}%>
                        </select>
                </div>
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary" value="eliminar" name="crud">Eliminar</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
              </div>
            </form>
            </div>
          </div>
        </div>
        
       <!--Modal para crear una nueva tarifa-->
       <div class="modal fade" id="crearTarifa" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Crear Tarifa</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            <form action="crudTarifas" method="post">
              <div class="modal-body">
                <div class="row container">
                    <label for="nombreTarifa" class="text-muted">Nombre</label>
                    <input type="text" class="form-control" id="nombreTarifa" name="nombreTarifa" value="" required>
                </div>
                <div class="row container">
                    <label for="precioTarifa" class="text-muted">Precio</label>
                    <input type="number" class="form-control" id="precioTarifa" name="precioTarifa" value="" step="any" required>
                </div>
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary" value="crear" name="crud">Crear</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
              </div>
            </form>
            </div>
          </div>
        </div>
        
        <!--Modal para modificar una tarifa--> 
        <div class="modal fade" id="modificarTarifa" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modificar Tarifa</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            <form action="crudTarifas" method="post">
              <div class="modal-body">
                <div class="row container">
                    <label class="text-muted" for="tarifa">Tarifa a modificar</label>
                        <select name="tarifa" class="form-control">
                            <%for (Tarifa tarifa : RegistroTarifas.getListadoTarifas()) {%>
                                <option value="<%=tarifa.getId()%>"><%=tarifa.getDescripcion()%></option>
                            <%}%>
                        </select>
                </div><br>
                <div class="row container">
                    <label for="nombreTarifa" class="text-muted">Nombre nuevo</label>
                    <input type="text" class="form-control" id="nombreTarifa" name="nombreTarifa" value="" required>
                </div><br>
                <div class="row container">
                    <label for="precioTarifa" class="text-muted">Precio nuevo</label>
                    <input type="number" class="form-control" id="precioTarifa" name="precioTarifa" value="" required>
                </div>
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary" value="modificar" name="crud">Modificar</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
              </div>
            </form>
            </div>
          </div>
        </div>
        
        <!--Modal para eliminar una tarifa-->
        <div class="modal fade" id="eliminarTarifa" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Eliminar Tarifa</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <form action="crudTarifas" method="post">
              <div class="modal-body">
                <div class="row container">
                    <label class="text-muted" for="tarifa">Tarifa a eliminar</label>
                        <select name="tarifa" class="form-control">
                            <%for (Tarifa tarifa : RegistroTarifas.getListadoTarifas()) {%>
                                <option value="<%=tarifa.getId()%>"><%=tarifa.getDescripcion()%></option>
                            <%}%>
                        </select>
                </div>
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary" value="eliminar" name="crud">Eliminar</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
              </div>
            </form>
            </div>
          </div>
        </div>
                        
        <!--Modal para crear un cuarto-->
        <div class="modal fade" id="crearHabitacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Crear Habitacion</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            <form action="crudHabitaciones" method="post">
              <div class="modal-body">
                <div class="row container">
                    <label for="precio" class="text-muted">Precio: </label>
                    <input type="number" class="form-control" id="precio" name="precio" value="<%=RegistroTarifas.getListadoTarifas().get(0).getPrecio()%>" step="any" required>
                </div><br>
                <div class="row container">
                    <label for="costo" class="text-muted">Costo: </label>
                    <input type="number" class="form-control" id="costo" name="costo" value="<%=RegistroTarifas.getListadoTarifas().get(1).getPrecio()%>" step="any" required>
                </div><br>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="disponible" id="checkboxSuscripcion" name="checkboxDisponible" checked="true">
                    <label class="form-check-label" for="checkboxDisponible">
                          Habitacion Disponible
                    </label>
                </div>
                <div class="modal-footer">
                    <button type="submit" name="crud" value="crear" class="btn btn-primary">Crear</button>
                    <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                </div>
              </div>
            </form>
          </div>
        </div>
        </div>
        
        <!--Modal para modificar un cuarto-->
        <div class="modal fade" id="modificarHabitacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modificar Habitacion</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            <form action="formularioHabitacion.jsp">
              <div class="modal-body">
                <div class="row container">
                    <label class="text-muted" for="habitacion">Seleccionar la habitacion a modificar</label>
                        <select id="habitacion" name="habitacion" class="form-control">
                            <%for (Habitacion habitacion : RegistroHabitaciones.getListadoHabitacionesModificables()){%>
                                <option><%=habitacion.getId()%></option>
                            <%}%>
                        </select>
                </div><br>
                <div class="modal-footer">
                    <button type="submit" name="crud" value="modificar" class="btn btn-primary">Seleccionar</button>
                    <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
                </div>
              </div>
            </form>
          </div>
        </div>
        </div>
                
        <!--Modal para eliminar la cantidad de cuartos-->
        <div class="modal fade" id="eliminarHabitacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modificar Cantidad De Habitaciones</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            <form action="crudHabitaciones" method="post">
              <div class="modal-body">
                <div class="row container">
                    <label for="cantidad" class="text-muted">Cantidad que desea eliminar: </label>
                    <input type="number" class="form-control" id="cantidad" name="cantidad" value="" required>
                </div>
              </div>
              <div class="modal-footer">
                <button type="submit" name="crud" value="eliminar" class="btn btn-primary">Eliminar</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
              </div>
            </form>
            </div>
          </div>
        </div>
        
        <!--Modal para modificar un usuario-->
        <div class="modal fade" id="modificarUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modificar Usuario</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            <form action="crudUsuarios" method="get">
              <div class="modal-body">
                <div class="row container">
                    <label class="text-muted" for="idContrato">Usuario a modificar</label>
                        <select id="idContrato" name="idContrato" class="form-control">
                            <option value="0">...</option>
                            <% for(Contrato contrato: RegistroContratos.getListadoContratosConUsuarioActivo()){%>
                                <option value="<%=contrato.getId()%>"><%=contrato.getNombreCompleto()%></option>
                            <%}%>
                        </select>
                </div>
              </div>
              <div class="modal-footer">
                <button type="submit" class="btn btn-primary">Ir al formulario</button>
                <button type="button" class="btn btn-dark" data-dismiss="modal">Close</button>
              </div>
            </form>
            </div>
          </div>
        </div>
        
        <!--Modal para eliminar un usuario-->
        <div class="modal fade" id="eliminarUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Eliminar Usuario</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
            <form action="crudUsuarios" method="post">
              <div class="modal-body">
                <div class="row container">
                    <label class="text-muted" for="idContrato">Usuario a modificar</label>
                        <select id="idContrato" name="idContrato" class="form-control">
                            <option value="0">...</option>
                            <% for(Contrato contrato: RegistroContratos.getListadoContratosConUsuarioActivo()){%>
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
        
        <%@include file="Bootstrap/scripts.html"%>
        
    </body>
</html>
