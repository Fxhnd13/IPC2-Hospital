/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.Usuario;
import Registro.RegistroUsuarios;
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
@WebServlet(name = "crudUsuarios", urlPatterns = {"/crudUsuarios"})
public class crudUsuarios extends HttpServlet {

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
        String idContrato = request.getParameter("idContrato");
        RequestDispatcher dispatcher = request.getRequestDispatcher("nuevoUsuario.jsp");
        dispatcher.forward(request, response);
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
        int idContrato = Integer.parseInt(request.getParameter("idContrato"));
        String username = request.getParameter("username");
        String password = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if(idContrato!=0){
            try{
                switch(request.getParameter("crud")){
                    case "crear":{
                        if(password.equals(password2)){
                            Usuario usuario = new Usuario(username, password, idContrato);
                            RegistroUsuarios.crearUsuario(usuario);
                        }else{
                            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                            request.setAttribute("error", "Las contraseñas ingresadas no coinciden.");
                            dispatcher.forward(request, response);
                        }
                        break;
                    }
                    case "modificar":{
                        Usuario usuario = new Usuario();
                        usuario.setIdContrato(idContrato);
                        usuario.setUsername(username);
                        if(password==null||password2==null){
                            RegistroUsuarios.modificarUsername(usuario);
                        }else{
                            if(password.equals(password2)){
                                usuario.setPassword(password);
                                RegistroUsuarios.modificarTodo(usuario);
                            }else{
                                RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                                request.setAttribute("error", "Las contraseñas ingresadas no coinciden.");
                                dispatcher.forward(request, response);
                            }
                        }
                        break;
                    }
                    case "eliminar":{
                        RegistroUsuarios.eliminarUsuario(idContrato);
                        break;
                    }
                }
            } catch (SQLException ex) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                request.setAttribute("error", "Problema al acceder a la base de datos. (Servlet: crudUsuarios)");
                dispatcher.forward(request, response);
                Logger.getLogger(crudUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("administracion.jsp");
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
            request.setAttribute("error", "No se selecciono un nombre");
            dispatcher.forward(request, response);
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
