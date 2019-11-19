/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.Area;
import Objetos.Tarifa;
import Registro.RegistroAreas;
import Registro.RegistroTarifas;
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
@WebServlet(name = "crudTarifas", urlPatterns = {"/crudTarifas"})
public class crudTarifas extends HttpServlet {

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
            out.println("<title>Servlet crudTarifas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crudTarifas at " + request.getContextPath() + "</h1>");
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
        if(request.getParameter("crud") != null){
            String opcion = request.getParameter("crud");
            switch(opcion){
                case "crear":{
                    try {
                        Tarifa tarifa = new Tarifa(request.getParameter("nombreTarifa"), Float.parseFloat(request.getParameter("precioTarifa")));
                        RegistroTarifas.crearTarifa(tarifa);
                    } catch (SQLException ex) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Error en la base de datos (Servlet: crudTarifas)");
                        dispatcher.forward(request, response);
                        Logger.getLogger(crudAreas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "modificar":{
                    try {
                        Tarifa tarifa = new Tarifa();
                        tarifa.setDescripcion(request.getParameter("nombreTarifa"));
                        tarifa.setPrecio(Float.parseFloat(request.getParameter("precioTarifa")));
                        tarifa.setId(Integer.parseInt(request.getParameter("tarifa")));
                        RegistroTarifas.modificarTarifa(tarifa);
                    } catch (SQLException ex) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Error en la base de datos (Servlet: crudTarifas)");
                        dispatcher.forward(request, response);
                        Logger.getLogger(crudAreas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "eliminar":{
                    try {
                        Tarifa tarifa = new Tarifa();
                        tarifa.setId(Integer.parseInt(request.getParameter("tarifa")));
                        if(tarifa.getId()>2){
                            RegistroTarifas.eliminarTarifa(tarifa);
                        }else{
                            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                            request.setAttribute("error", "No se puede modificar la tarifa seleccionada porque es un tarifa basica. (Servlet: crudAreas)");
                            dispatcher.forward(request, response);
                        }
                    } catch (SQLException ex) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Error en la base de datos (Servlet: crudTarifas)");
                        dispatcher.forward(request, response);
                        Logger.getLogger(crudAreas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
            }
        }
        response.sendRedirect("administracion.jsp");
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
