/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.Habitacion;
import Registro.RegistroHabitaciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jose_
 */
@WebServlet(name = "crudHabitaciones", urlPatterns = {"/crudHabitaciones"})
public class crudHabitaciones extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("crud") != null){
            String opcion = request.getParameter("crud");
            switch(opcion){
                case "crear":{
                    try {
                        Habitacion habitacion = new Habitacion();
                        if(request.getParameter("checkboxDisponible")!=null){
                            habitacion.setDisponible(1);
                        }else{
                            habitacion.setDisponible(0);
                        }
                        habitacion.setPrecio(Float.parseFloat(request.getParameter("precio")));
                        habitacion.setCosto(Float.parseFloat(request.getParameter("costo")));
                        habitacion.setOcupada(0);
                        RegistroHabitaciones.crearHabitacion(habitacion);
                        break;
                    } catch (SQLException ex) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Error en la base de datos (Servlet: crudHabitaciones)");
                        dispatcher.forward(request, response);
                        Logger.getLogger(crudAreas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case "modificar":{
                    try {
                        Habitacion habitacion = new Habitacion();
                        if(request.getParameter("checkboxDisponible")!=null){
                            habitacion.setDisponible(1);
                        }else{
                            habitacion.setDisponible(0);
                        }
                        habitacion.setId(Integer.parseInt(request.getParameter("id")));
                        habitacion.setPrecio(Float.parseFloat(request.getParameter("precio")));
                        habitacion.setCosto(Float.parseFloat(request.getParameter("costo")));
                        habitacion.setOcupada(0);
                        RegistroHabitaciones.modificarHabitacion(habitacion);
                        break;
                    } catch (SQLException ex) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Error en la base de datos (Servlet: crudHabitaciones)");
                        dispatcher.forward(request, response);
                        Logger.getLogger(crudAreas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case "disponibilidad":{
                    try{
                        Habitacion habitacion = RegistroHabitaciones.getHabitacion(request.getParameter("id"));
                        habitacion.setDisponibilidad();
                        RegistroHabitaciones.modificarHabitacion(habitacion);
                        break;
                    } catch (SQLException ex) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Error en la base de datos (Servlet: crudHabitaciones, disponibilidad)");
                        dispatcher.forward(request, response);
                        Logger.getLogger(crudHabitaciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case "eliminar":{
                    try {
                        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                        if(cantidad<10){
                            RegistroHabitaciones.eliminarHabitaciones(cantidad);
                        }else{
                            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                            request.setAttribute("error", "No se pueden eliminar más de 10 habitaciones simultaneamente (Servlet: crudHabitaciones)");
                            dispatcher.forward(request, response);
                        }
                        break;
                    } catch (SQLException ex) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Error en la base de datos (Servlet: crudTarifas)");
                        dispatcher.forward(request, response);
                        Logger.getLogger(crudAreas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        response.sendRedirect("recursosHumanos.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
