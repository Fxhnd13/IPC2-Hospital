/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Objetos.Cuenta;
import Objetos.Egreso;
import Objetos.Medicamento;
import Objetos.Pago;
import Registro.RegistroEgresos;
import Registro.RegistroMedicamentos;
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
@WebServlet(name = "crudMedicamentos", urlPatterns = {"/crudMedicamentos"})
public class crudMedicamentos extends HttpServlet {

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
            out.println("<title>Servlet crudMedicamentos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet crudMedicamentos at " + request.getContextPath() + "</h1>");
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
            String opcion = request.getParameter("crud");
            switch(opcion){
                case "modificarCantidad":{
                    Medicamento medicamento = RegistroMedicamentos.getMedicamento(Integer.parseInt(request.getParameter("idMedicamento")));
                    int cantidadASumar = Integer.parseInt(request.getParameter("cantidad"));
                    int cantidadNueva = medicamento.getCantidad() + cantidadASumar;
                    medicamento.setCantidad(cantidadNueva);
                    RegistroMedicamentos.modificarMedicamento(medicamento);
                    Egreso egreso = new Egreso("Medicamento", "Compra de: "+medicamento.getNombre(), LocalDate.now(), (cantidadASumar*medicamento.getCosto()));
                    RegistroEgresos.crearEgreso(egreso);
                    response.sendRedirect("farmacia.jsp");
                    break;
                }
                case "inventario":{
                    for (Medicamento medicamento : RegistroMedicamentos.getListadoMedicamentos(4)) {
                        int cantidadNueva = Integer.parseInt(request.getParameter(String.valueOf(medicamento.getId())));
                        medicamento.setCantidad(cantidadNueva);
                        RegistroMedicamentos.modificarMedicamento(medicamento);
                    }
                    response.sendRedirect("farmacia.jsp");
                    break;
                }
                case "modificar":{
                    Medicamento medicamento = RegistroMedicamentos.getMedicamento(Integer.parseInt(request.getParameter("idMedicamento")));
                    medicamento.setNombre(request.getParameter("nombre"));
                    medicamento.setCantidadMinima(Integer.parseInt(request.getParameter("cantidadMinima")));
                    medicamento.setPrecio(Float.parseFloat(request.getParameter("precio")));
                    medicamento.setCosto(Float.parseFloat(request.getParameter("costo")));
                    RegistroMedicamentos.modificarMedicamento(medicamento);
                    response.sendRedirect("farmacia.jsp");
                }
                case "crear":{
                    Medicamento medicamento = new Medicamento();
                    medicamento.setNombre(request.getParameter("nombre"));
                    medicamento.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
                    medicamento.setCantidadMinima(Integer.parseInt(request.getParameter("cantidadMinima")));
                    medicamento.setPrecio(Float.parseFloat(request.getParameter("precio")));
                    medicamento.setCosto(Float.parseFloat(request.getParameter("costo")));
                    RegistroMedicamentos.crearMedicamento(medicamento);
                    Egreso egreso = new Egreso("Medicamento", "Compra de: "+medicamento.getNombre(), LocalDate.now(), (medicamento.getCantidad()*medicamento.getCosto()));
                    RegistroEgresos.crearEgreso(egreso);
                    response.sendRedirect("farmacia.jsp");
                    break;
                }
                case "agregarVenta":{
                    Medicamento medicamento = RegistroMedicamentos.getMedicamento(Integer.parseInt(request.getParameter("idMedicamento")));
                    if((!request.getParameter("cantidad").isEmpty())&&(request.getParameter("cantidad")!=null)){
                        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                        if(request.getSession().getAttribute("cuenta")==null){
                            Cuenta cuenta = new Cuenta();
                            Pago pago = new Pago();
                            pago.setCantidad(cantidad);
                            pago.setDescripcion(medicamento.getNombre());
                            pago.setFecha(LocalDate.now());
                            pago.setTipo("Venta Medicamento");
                            pago.setPrecio(medicamento.getPrecio());
                            pago.setTotal(cantidad * medicamento.getPrecio());
                            cuenta.getPagos().add(pago);
                            request.getSession().setAttribute("cuenta", cuenta);
                        }else{
                            Cuenta cuenta = (Cuenta)request.getSession().getAttribute("cuenta");
                            Pago pago = new Pago();
                            pago.setCantidad(cantidad);
                            pago.setDescripcion(medicamento.getNombre());
                            pago.setFecha(LocalDate.now());
                            pago.setTipo("Venta Medicamento");
                            pago.setPrecio(medicamento.getPrecio());
                            pago.setTotal(cantidad * medicamento.getPrecio());
                            cuenta.getPagos().add(pago);
                            request.getSession().setAttribute("cuenta", cuenta);
                        }
                        response.sendRedirect("ventaFarmacia.jsp");
                    }else{
                        RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                        request.setAttribute("error", "Debe ingresar la cantidad del medicamento que desea.");
                        dispatcher.forward(request, response);
                    }
                    break;
                }
                case "hacerCuenta":{
                    Medicamento medicamento = RegistroMedicamentos.getMedicamento(Integer.parseInt(request.getParameter("idMedicamento")));
                    if((!request.getParameter("cantidad").isEmpty())&&(request.getParameter("cantidad")!=null)){
                        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                        if(request.getSession().getAttribute("cuenta")==null){
                            Cuenta cuenta = new Cuenta();
                            Pago pago = new Pago();
                            pago.setCantidad(cantidad);
                            pago.setDescripcion(medicamento.getNombre());
                            pago.setFecha(LocalDate.now());
                            pago.setTipo("Venta Medicamento");
                            pago.setPrecio(medicamento.getPrecio());
                            pago.setTotal(cantidad * medicamento.getPrecio());
                            cuenta.getPagos().add(pago);
                            request.getSession().setAttribute("cuenta", cuenta);
                        }else{
                            Cuenta cuenta = (Cuenta)request.getSession().getAttribute("cuenta");
                            Pago pago = new Pago();
                            pago.setCantidad(cantidad);
                            pago.setDescripcion(medicamento.getNombre());
                            pago.setFecha(LocalDate.now());
                            pago.setTipo("Venta Medicamento");
                            pago.setPrecio(medicamento.getPrecio());
                            pago.setTotal(cantidad * medicamento.getPrecio());
                            cuenta.getPagos().add(pago);
                            request.getSession().setAttribute("cuenta", cuenta);
                        }
                        response.sendRedirect("factura.jsp");
                    }else{
                        if(request.getSession().getAttribute("cuenta")!=null){
                            response.sendRedirect("factura.jsp");
                        }else{
                            RequestDispatcher dispatcher = request.getRequestDispatcher("errores.jsp");
                            request.setAttribute("error", "No se agrego ningún producto a la cuenta.");
                            dispatcher.forward(request, response);
                        }
                    }
                    break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(crudMedicamentos.class.getName()).log(Level.SEVERE, null, ex);
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
