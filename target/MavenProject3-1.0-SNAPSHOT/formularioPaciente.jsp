<%-- 
    Document   : formularioPaciente
    Created on : 17/11/2019, 12:19:45 AM
    Author     : jose_
--%>

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
        
        
        <div class="row container-fluid">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <br>
                <center>
                    <legend>Formulario Contrato</legend>  
                </center>
                <form action="crudPacientes" method="post">
                    <div class="row container">
                        <label for="cui">Cui</label>
                        <input type="number" class="form-control" id="cui" name="cui" required>
                    </div>
                    <br>
                    <div class="row container">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre" required>
                    </div>
                    <br>
                    <div class="row container">
                        <label for="altura">Altura</label>
                        <input type="number" step="any" class="form-control" id="altura" name="altura">
                    </div>
                    <br>
                    <div class="row container">
                        <label for="peso">Peso (en Lb)</label>
                        <input type="number" step="any" class="form-control" id="peso" name="peso">
                    </div>
                    <br>
                    <div class="row container">
                        <label for="edad">Edad</label>
                        <input type="number" class="form-control" id="edad" name="edad">
                    </div>
                    <br>
                    <div class="row container">
                        <label for="contacto">Contacto</label>
                        <input type="tel" class="form-control" id="contacto" name="contacto">
                    </div>
                    <br>
                    <div class="row container">
                        <label for="tipoDeSangre">Tipo de sangre</label>
                        <input type="text" class="form-control" id="tipoDeSangre" name="tipoDeSangre">
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-md-4"></div>
                        <button class="col-md-4 btn btn-primary btn-lg btn-block" type="submit" value="crear" name="crud">Crear Paciente</button>
                    </div>
                    <br>
                </form>
            </div>
        </div>
                    
        <%@include file="Bootstrap/scripts.html" %>
        
    </body>
</html>
