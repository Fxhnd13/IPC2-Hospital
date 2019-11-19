/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.Consulta;
import Objetos.Cuenta;
import Objetos.Pago;
import Registro.RegistroConsultas;
import Registro.RegistroContratos;
import Registro.RegistroCuentas;
import Registro.RegistroPacientes;
import Registro.RegistroTarifas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
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
@WebServlet(name = "crudConsultas", urlPatterns = {"/crudConsultas"})
public class crudConsultas extends HttpServlet {

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
            out.println("<title>Servlet crudConsultas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crudConsultas at " + request.getContextPath() + "</h1>");
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
        try {
            String causa = request.getParameter("causa");
            String motivo = request.getParameter("motivo");
            String cuiPaciente = request.getParameter("cuiPaciente");
            LocalDate fecha = Utilidades.Utilidades.convertirFechaConGuion(request.getParameter("fecha"));
            String nombreMedico = RegistroContratos.getNombrePorUsername(request.getSession().getAttribute("username").toString());
            if (request.getSession().getAttribute("operacion") != null) {
                request.getSession().removeAttribute("operacion");
            }
            Consulta consulta = new Consulta(cuiPaciente, causa, motivo, fecha, nombreMedico);
            RegistroConsultas.crearConsulta(consulta);
            Cuenta cuenta = new Cuenta();
            cuenta.setResponsableDeLaCompra(RegistroPacientes.getPaciente(cuiPaciente).getNombre());
            Pago pago = new Pago();
            pago.setCantidad(1);
            pago.setDescripcion("Consulta");
            pago.setTipo("Consulta");
            pago.setFecha(LocalDate.now());
            pago.setIdCuenta(RegistroCuentas.getCuentaActivaDe(RegistroPacientes.getPaciente(cuiPaciente).getNombre()).getId());
            pago.setPrecio(RegistroTarifas.getListadoTarifas().get(4).getPrecio());
            pago.setTotal(pago.getPrecio() * pago.getCantidad());
            cuenta.getPagos().add(pago);
            RegistroCuentas.crearCuentaNormal(cuenta);
        } catch (SQLException ex) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
            request.setAttribute("error", "Error al acceder a la base de datos (servlet: crudConsultas).");
            dispatcher.forward(request, response);
            Logger.getLogger(crudConsultas.class.getName()).log(Level.SEVERE, null, ex);
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
