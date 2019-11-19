package Registro;

import Objetos.Area;
import Objetos.Tarifa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class RegistroTarifas {
 
    public static ArrayList<Tarifa> getListadoTarifas() throws SQLException{
        ArrayList<Tarifa> tarifas = new ArrayList<Tarifa>();
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from tarifa;");
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            Tarifa tarifa = new Tarifa();
            tarifa.setId(resultado.getInt("Id"));
            tarifa.setDescripcion(resultado.getString("Descripcion"));
            tarifa.setPrecio(resultado.getFloat("Precio"));
            tarifas.add(tarifa);
        }
        return tarifas;
    }    

    public static void crearTarifa(Tarifa tarifa) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Tarifa (Descripcion, Precio) values (?,?);");
        declaracion.setString(1, tarifa.getDescripcion());
        declaracion.setFloat(2, tarifa.getPrecio());
        declaracion.executeUpdate();
    }
    
    public static void modificarTarifa(Tarifa tarifa) throws SQLException{
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("update Tarifa set Descripcion=?, Precio=? where Id=?;");
        declaracion.setString(1, tarifa.getDescripcion());
        declaracion.setFloat(2, tarifa.getPrecio());
        declaracion.setInt(3, tarifa.getId());
        declaracion.executeUpdate();
    }

    public static void eliminarTarifa(Tarifa tarifa) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("delete from Tarifa where Id=?;");
        declaracion.setInt(1, tarifa.getId());
        declaracion.executeUpdate();
    }
    
}
