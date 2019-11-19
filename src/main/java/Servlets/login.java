/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Registro.RegistroUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jose_
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

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
        try {
            String nombre = request.getParameter("username");
            String password = request.getParameter("password");
            RequestDispatcher dispatcher;
            int area = RegistroUsuarios.getArea(nombre, password);
            switch (area){
                case 0: response.sendRedirect("login.jsp"); break;
                case 1: {
                    request.getSession().setAttribute("username", request.getParameter("username"));
                    request.getSession().setAttribute("area", area);
                    dispatcher = request.getRequestDispatcher("administracion.jsp");
                    dispatcher.forward(request, response); break;
                }
                
                case 2: {
                    request.getSession().setAttribute("username", request.getParameter("username"));
                    request.getSession().setAttribute("area", area);
                    dispatcher = request.getRequestDispatcher("medicina.jsp");
                    dispatcher.forward(request, response); break;
                }
                case 3: {
                    request.getSession().setAttribute("username", request.getParameter("username"));
                    request.getSession().setAttribute("area", area);
                    dispatcher = request.getRequestDispatcher("enfermeria.jsp");
                    dispatcher.forward(request, response); break;
                }
                case 4: {
                    request.getSession().setAttribute("username", request.getParameter("username"));
                    request.getSession().setAttribute("area", area);
                    dispatcher = request.getRequestDispatcher("recursosHumanos.jsp");
                    dispatcher.forward(request, response); break;
                }
                case 5: {
                    request.getSession().setAttribute("username", request.getParameter("username"));
                    request.getSession().setAttribute("area", area);
                    dispatcher = request.getRequestDispatcher("recepcion.jsp");
                    dispatcher.forward(request, response); break;
                }
                case 6: {
                    request.getSession().setAttribute("username", request.getParameter("username"));
                    request.getSession().setAttribute("area", area);
                    dispatcher = request.getRequestDispatcher("delegacion.jsp");
                    dispatcher.forward(request, response); break;
                }
                case 7: {
                    request.getSession().setAttribute("username", request.getParameter("username"));
                    request.getSession().setAttribute("area", area);
                    dispatcher = request.getRequestDispatcher("farmacia.jsp");
                    dispatcher.forward(request, response); break;
                }
            }
        } catch (SQLException ex) {
            request.setAttribute("error", "Error al acceder a la base de datos (login)");
            RequestDispatcher dispatcher2 = request.getRequestDispatcher("errores.jsp");
            dispatcher2.forward(request, response);
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
