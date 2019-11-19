/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose_
 */
public class Registro {

public static Connection conexion=null;
    
    //CARGA EL ESQUEMA QUE SE VA A UTILIZAR PARA LA BASE DE DATOS.
    public static void cargarConexion() throws SQLException{
        Statement creaEsquema = conexion.createStatement();
        creaEsquema.executeUpdate("USE CodeNBugs");
    }
    
    //INICIA SESION EN LA BASE DE DATOS
    public static void iniciarSesion() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        String user = "root";
	String password = "JOSECARLOS13";
	String stringConnection = "jdbc:mysql://localhost/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        conexion = DriverManager.getConnection(stringConnection, user, password);
        cargarConexion();
    }
    
}
