/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.Habitacion;
import Objetos.Internado;
import Objetos.Paciente;
import Registro.RegistroHabitaciones;
import Registro.RegistroInternados;
import Registro.RegistroPacientes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
@WebServlet(name = "crudPacientes", urlPatterns = {"/crudPacientes"})
public class crudPacientes extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet crudPacientes</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crudPacientes at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        try {
            String cui = request.getParameter("cui");
            if (cui.equals("0")) {
                switch (request.getParameter("crud")) {
                    case "consulta": {
                        request.getSession().setAttribute("operacion", "consulta");
                        response.sendRedirect("formularioPaciente.jsp");
                        break;
                    }
                    case "cirugia": {
                        request.getSession().setAttribute("operacion", "cirugia");
                        response.sendRedirect("formularioPaciente.jsp");
                        break;
                    }
                }
            } else {
                switch (request.getParameter("crud")) {
                    case "consulta": {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("consulta.jsp");
                        request.setAttribute("cui", cui);
                        dispatcher.forward(request, response);
                        break;
                    }
                    case "cirugia": {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("cirugia.jsp");
                        request.setAttribute("cui", cui);
                        dispatcher.forward(request, response);
                        break;
                    }
                    case "internado": {
                        LocalDate fechaEntrada = Utilidades.Utilidades.convertirFechaConGuion(request.getParameter("fecha"));
                        String cuiPaciente = request.getParameter("cuiPaciente");
                        ArrayList<Habitacion> habitacionesDisponibles = RegistroHabitaciones.getListadoHabitacionesDisponibles();
                        if (habitacionesDisponibles.isEmpty()) {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                            request.setAttribute("error", "No hay habitaciones disponibles.");
                            dispatcher.forward(request, response);
                        } else {
                            Habitacion habitacion = habitacionesDisponibles.get(0);
                            habitacion.setOcupada(1);
                            Internado internado = new Internado(fechaEntrada, cuiPaciente, habitacion.getId());
                            RegistroHabitaciones.modificarHabitacion(habitacion);
                            RegistroInternados.crearInternado(internado);
                        }
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(crudPacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            switch (request.getParameter("crud")) {
                case "crear": {
                    Paciente paciente = new Paciente();
                    paciente.setCui(request.getParameter("cui"));
                    paciente.setNombre(request.getParameter("nombre"));
                    if (request.getParameter("altura") != null) {
                        paciente.setAltura(Float.parseFloat(request.getParameter("altura")));
                    }
                    if (request.getParameter("peso") != null) {
                        paciente.setPeso(Float.parseFloat(request.getParameter("peso")));
                    }
                    if (request.getParameter("contacto") != null) {
                        paciente.setContacto(Integer.parseInt(request.getParameter("contacto")));
                    }
                    if (request.getParameter("edad") != null) {
                        paciente.setEdad(Integer.parseInt(request.getParameter("edad")));
                    }
                    if (request.getParameter("tipoDeSangre") != null) {
                        paciente.setTipoDeSangre(request.getParameter("tipoDeSangre"));
                    }
                    RegistroPacientes.crearPaciente(paciente);
                    String operacion = request.getSession().getAttribute("operacion").toString();
                    switch (operacion) {
                        case "consulta": {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("consulta.jsp");
                            request.setAttribute("cui", paciente.getCui());
                            dispatcher.forward(request, response);
                            break;
                        }
                        case "cirugia": {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("cirugia.jsp");
                            request.setAttribute("cui", paciente.getCui());
                            dispatcher.forward(request, response);
                            break;
                        }
                        case "internado": {
                            RequestDispatcher dispatcher = request.getRequestDispatcher("internado.jsp");
                            request.setAttribute("cui", paciente.getCui());
                            dispatcher.forward(request, response);
                            break;
                        }
                    }
                    break;
                }
            }
        } catch (SQLException ex) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
            request.setAttribute("error", "Error al acceder a la base de datos. (Servlet: crudPacientes)");
            dispatcher.forward(request, response);
            Logger.getLogger(crudPacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
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
