/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import Objetos.Medicamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class RegistroMedicamentos {
    
    /**
     *
     * @param opcion
     * @return Agotandose con parametro 1, Sin agotarse con parametro 2, En existencia con parametro 3, Listado completo con parametro 4
     * @throws SQLException
     */
    public static ArrayList<Medicamento> getListadoMedicamentos(int opcion) throws SQLException{
        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        Registro.iniciarSesion();
        String consulta = null;
        switch(opcion){
            case 1:{
                consulta = "select * from Medicamento where (CantidadMinima>=Cantidad);";
                break;
            }
            case 2:{
                consulta = "select * from Medicamento where (CantidadMinima<Cantidad);";
                break;
            }
            case 3:{
                consulta = "select * from Medicamento where (Cantidad>0);";
                break;
            }
            case 4:{
                consulta = "select * from Medicamento;";
                break;
            }
        }
        Statement declaracion = Registro.conexion.createStatement();
        ResultSet resultado = declaracion.executeQuery(consulta);
        while(resultado.next()){
            Medicamento medicamento = new Medicamento();
            medicamento.setId(resultado.getInt("Id"));
            medicamento.setCantidad(resultado.getInt("Cantidad"));
            medicamento.setCantidadMinima(resultado.getInt("CantidadMinima"));
            medicamento.setPrecio(resultado.getFloat("Precio"));
            medicamento.setCosto(resultado.getFloat("Costo"));
            medicamento.setNombre(resultado.getString("Nombre"));
            medicamentos.add(medicamento);
        }
        return medicamentos;
    }

    public static Medicamento getMedicamento(int id) throws SQLException {
        Medicamento medicamento = new Medicamento();
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Medicamento where (Id=?);");
        declaracion.setInt(1, id);
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            medicamento.setId(resultado.getInt("Id"));
            medicamento.setCantidad(resultado.getInt("Cantidad"));
            medicamento.setCantidadMinima(resultado.getInt("CantidadMinima"));
            medicamento.setPrecio(resultado.getFloat("Precio"));
            medicamento.setCosto(resultado.getFloat("Costo"));
            medicamento.setNombre(resultado.getString("Nombre"));
        }
        return medicamento;
    }
    
    public static Medicamento getMedicamentoPorNombre(String nombre) throws SQLException{
        Medicamento medicamento = new Medicamento();
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Medicamento where (Nombre=?);");
        declaracion.setString(1, nombre);
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            medicamento.setId(resultado.getInt("Id"));
            medicamento.setCantidad(resultado.getInt("Cantidad"));
            medicamento.setCantidadMinima(resultado.getInt("CantidadMinima"));
            medicamento.setPrecio(resultado.getFloat("Precio"));
            medicamento.setCosto(resultado.getFloat("Costo"));
            medicamento.setNombre(resultado.getString("Nombre"));
        }
        return medicamento;
    }

    public static void modificarMedicamento(Medicamento medicamento) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("update Medicamento set Nombre=?, Cantidad=?, CantidadMinima=?, Precio=?, Costo=? where (Id=?);");
        declaracion.setString(1, medicamento.getNombre());
        declaracion.setInt(2, medicamento.getCantidad());
        declaracion.setInt(3, medicamento.getCantidadMinima());
        declaracion.setFloat(4, medicamento.getPrecio());
        declaracion.setFloat(5, medicamento.getCosto());
        declaracion.setInt(6, medicamento.getId());
        declaracion.executeUpdate();
    }
    
    public static void crearMedicamento(Medicamento medicamento) throws SQLException{
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Medicamento (Nombre, Cantidad, CantidadMinima, Precio, Costo) values (?,?,?,?,?);");
        declaracion.setString(1, medicamento.getNombre());
        declaracion.setInt(2, medicamento.getCantidad());
        declaracion.setInt(3, medicamento.getCantidadMinima());
        declaracion.setFloat(4, medicamento.getPrecio());
        declaracion.setFloat(5, medicamento.getCosto());
        declaracion.executeUpdate();
    }
    
}
