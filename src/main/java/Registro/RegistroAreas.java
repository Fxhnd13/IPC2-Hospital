package Registro;

import Objetos.Area;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class RegistroAreas {

    public static ArrayList<Area> getListadoAreas() throws SQLException{
        ArrayList<Area> areas = new ArrayList<Area>();
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from area;");
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            Area area = new Area(resultado.getInt("Id"), resultado.getString("Nombre"));
            areas.add(area);
        }
        return areas;
    }    
    
    public static String getNombreArea(int id) throws SQLException{
        String area = null;
        Registro.iniciarSesion();
        Statement declaracion = Registro.conexion.createStatement();
        ResultSet resultado = declaracion.executeQuery("select * from area where Id="+id+";");
        if(resultado.next()){
            area = resultado.getString("Nombre");
        }
        return area;
    }

    public static void crearArea(Area area) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into area (Nombre) value (?);");
        declaracion.setString(1, area.getNombre());
        declaracion.executeUpdate();
    }
    
    public static void modificarArea(Area area) throws SQLException{
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("update area set Nombre=? where Id=?;");
        declaracion.setString(1, area.getNombre());
        declaracion.setInt(2, area.getId());
        declaracion.executeUpdate();
    }

    public static void eliminarArea(Area area) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("delete from area where Id=?;");
        declaracion.setInt(1, area.getId());
        declaracion.executeUpdate();
    }
    
}
