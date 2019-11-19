/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import Objetos.Consulta;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jose_
 */
public class RegistroConsultas {

    public static void crearConsulta(Consulta consulta) throws SQLException{
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Consulta (Fecha,CuiPaciente,NombreMedico,Motivo,Causa) values (?,?,?,?,?);");
        declaracion.setDate(1, Utilidades.Utilidades.convertirLocalDateADate(consulta.getFecha()));
        declaracion.setString(2, consulta.getCuiPaciente());
        declaracion.setString(3, consulta.getNombreMedico());
        declaracion.setString(4, consulta.getMotivo());
        declaracion.setString(5, consulta.getCausa());
        declaracion.executeUpdate();
        
    }
}
