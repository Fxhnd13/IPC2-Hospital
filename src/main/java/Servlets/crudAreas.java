/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.Area;
import Registro.RegistroAreas;
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
@WebServlet(name = "crudAreas", urlPatterns = {"/crudAreas"})
public class crudAreas extends HttpServlet {

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
            out.println("<title>Servlet crudAreas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crudAreas at " + request.getContextPath() + "</h1>");
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
                        Area area = new Area(request.getParameter("nombreArea"));
                        RegistroAreas.crearArea(area);
                        break;
                    } catch (SQLException ex) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Error en la base de datos (Servlet: crudAreas)");
                        dispatcher.forward(request, response);
                        Logger.getLogger(crudAreas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                case "modificar":{
                    try {
                        Area area = new Area(Integer.parseInt(request.getParameter("area")), request.getParameter("nombreAreaNuevo"));
                        if(area.getId()>7){
                            RegistroAreas.modificarArea(area);
                        }else{
                            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                            request.setAttribute("error", "No se puede modificar el area seleccionada porque es un area basica. (Servlet: crudAreas)");
                            dispatcher.forward(request, response);
                        }
                    } catch (SQLException ex) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "");
                        dispatcher.forward(request, response);
                        Logger.getLogger(crudAreas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "eliminar":{
                    try {
                        Area area = new Area(Integer.parseInt(request.getParameter("area")));
                        if(area.getId()>7){
                            RegistroAreas.eliminarArea(area);
                        }else{
                            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                            request.setAttribute("error", "No se puede eliminar el area seleccionada porque es un area basica. (Servlet: crudAreas)");
                            dispatcher.forward(request, response);
                        }
                    } catch (SQLException ex) {
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Se produjo un error al guardar el archivo en la base de datos (Servlet: crudAreas)");
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
