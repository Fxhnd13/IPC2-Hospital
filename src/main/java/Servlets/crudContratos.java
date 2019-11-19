/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.CambioContrato;
import Objetos.Contrato;
import Objetos.Vacacion;
import Registro.RegistroCambiosContratos;
import Registro.RegistroContratos;
import Registro.RegistroVacaciones;
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
@WebServlet(name = "crudContratos", urlPatterns = {"/crudContratos"})
public class crudContratos extends HttpServlet {

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
            out.println("<title>Servlet crudContratos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crudContratos at " + request.getContextPath() + "</h1>");
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
        if((request.getParameter("crud").equals("crear"))||(request.getParameter("crud").equals("modificar"))){
            String cui = request.getParameter("cui");
            String nombreCompleto = request.getParameter("nombreCompleto");
            float sueldo = Float.parseFloat(request.getParameter("sueldo"));
            int area = Integer.parseInt(request.getParameter("area"));
            int pagoIgss=0;
            int pagoIrtra=0;
            if(request.getParameter("pagoIgss")!=null){
                pagoIgss=1;
            }
            if(request.getParameter("pagoIrtra")!=null){
                pagoIrtra=1;
            }
            LocalDate fechaDecreacion = Utilidades.Utilidades.convertirFechaConGuion(request.getParameter("fechaDeCreacion"));
            ArrayList<LocalDate> vacaciones = Utilidades.Utilidades.obtenerListadoDias(request, response);
            Contrato contrato = new Contrato(area, pagoIgss, pagoIrtra, fechaDecreacion, sueldo, nombreCompleto, cui);
            if(request.getParameter("crud").equals("crear")){
                try {
                    if(RegistroContratos.existeContratoActivo(contrato.getCui())){
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Ya existe un contrato activo con los datos ingresados.");
                        dispatcher.forward(request, response);
                    }else{
                        RegistroContratos.crearContrato(contrato);
                    }
                } catch (SQLException ex) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                    request.setAttribute("error", "Error al registrar el contrato en la base de datos. (Servlet: crudContrato, contrato)");
                    dispatcher.forward(request, response);
                    Logger.getLogger(crudContratos.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    RegistroVacaciones.crearVacaciones(vacaciones);
                } catch (SQLException ex) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                    request.setAttribute("error", "Error al registrar el contrato en la base de datos. (Servlet: crudContrato, vacaciones)");
                    dispatcher.forward(request, response);
                    Logger.getLogger(crudContratos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(request.getParameter("crud").equals("modificar")){
                ArrayList<CambioContrato> historial = new ArrayList<CambioContrato>();
                Contrato contratoOriginal;
                try {
                    contratoOriginal = RegistroContratos.getContratoPorId(request.getParameter("id"));
                    ArrayList<Vacacion> vacacionesOriginal = RegistroVacaciones.getListadoVacaciones(contratoOriginal.getId());
                    boolean valor = false;
                    boolean valor2 = false;
                    for(LocalDate date: vacaciones){
                        if(!Utilidades.Utilidades.revisarSiExisteElDia2(vacacionesOriginal, date)){
                            valor = true;
                        }
                        if(!Utilidades.Utilidades.verificarUnaSemanaDespues(date)){
                            valor2 = true;
                        }
                    }
                    if(valor){
                        if(valor2){
                            RegistroVacaciones.modificarVacaciones(RegistroVacaciones.getListadoVacaciones(contratoOriginal.getId()), vacaciones);
                            CambioContrato cambio = new CambioContrato(contratoOriginal.getId(), contrato.getFechaDeCreacion(), "Se Modifico una fecha de vacaciones de la contratacion.");
                            historial.add(cambio);
                        }else{
                            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                            request.setAttribute("error", "No se puede cambiar las vacaciones a una fecha dentro de una semana o menos.");
                            dispatcher.forward(request, response);
                        }
                    }
                    if(contratoOriginal.getArea()!=contrato.getArea()){
                        CambioContrato cambio = new CambioContrato(contratoOriginal.getId(), contrato.getFechaDeCreacion(), "Se modifico el Area de la contratacion.");
                        historial.add(cambio);
                    }
                    if(!contratoOriginal.getNombreCompleto().equals(contrato.getNombreCompleto())){
                        CambioContrato cambio = new CambioContrato(contratoOriginal.getId(), contrato.getFechaDeCreacion(), "Se modifico el Nombre de la contratacion.");
                        historial.add(cambio);
                    }
                    if(contratoOriginal.getPagoIgss()!=contrato.getPagoIgss()){
                        if(contrato.getPagoIgss()==1){
                            CambioContrato cambio = new CambioContrato(contratoOriginal.getId(), contrato.getFechaDeCreacion(), "Se modifico el Pago de Igss de la contratacion. (ahora paga Igss)");
                            historial.add(cambio);
                        }else{
                            CambioContrato cambio = new CambioContrato(contratoOriginal.getId(), contrato.getFechaDeCreacion(), "Se modifico el Pago de Igss de la contratacion. (ahora No paga Igss)");
                            historial.add(cambio);
                        }
                    }
                    if(contratoOriginal.getPagoIrtra()!=contrato.getPagoIrtra()){
                        if(contrato.getPagoIrtra()==1){
                            CambioContrato cambio = new CambioContrato(contratoOriginal.getId(), contrato.getFechaDeCreacion(), "Se modifico el Pago de Irtra de la contratacion. (ahora paga Irtra)");
                            historial.add(cambio);
                        }else{
                            CambioContrato cambio = new CambioContrato(contratoOriginal.getId(), contrato.getFechaDeCreacion(), "Se modifico el Pago de Irtra de la contratacion. (ahora No paga Irtra)");
                            historial.add(cambio);
                        }
                    }
                    if(contratoOriginal.getSueldo()!=contrato.getSueldo()){
                        CambioContrato cambio = new CambioContrato(contratoOriginal.getId(), contrato.getFechaDeCreacion(), "Se modifico el Sueldo de la contratacion.");
                        historial.add(cambio);
                    }
                    contrato.setId(contratoOriginal.getId());
                    RegistroContratos.modificarContrato(contrato);
                    RegistroCambiosContratos.guardarCambios(historial);
                } catch (SQLException ex) {
                    Logger.getLogger(crudContratos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(request.getParameter("crud").equals("eliminar")){
            Contrato contratoOriginal;
            try {
                contratoOriginal = RegistroContratos.getContratoPorId(request.getParameter("idContrato"));
                RegistroContratos.terminarContrato(contratoOriginal);
            } catch (SQLException ex) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                request.setAttribute("error", "Error en la base de datos al terminar el contrato seleccionado.");
                dispatcher.forward(request, response);
                Logger.getLogger(crudContratos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(request.getParameter("crud").equals("pagos")){
            try {
                RegistroContratos.pagarMes();
            } catch (SQLException ex) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                request.setAttribute("error", "Error en la base de datos al terminar el contrato seleccionado.");
                dispatcher.forward(request, response);
                Logger.getLogger(crudContratos.class.getName()).log(Level.SEVERE, null, ex);
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
