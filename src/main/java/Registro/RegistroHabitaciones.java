package Registro;

import Objetos.Area;
import Objetos.Habitacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */

public class RegistroHabitaciones {

    public static ArrayList<Habitacion> getListadoHabitacionesModificables() throws SQLException{
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Habitacion where(Ocupada=0);");
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            Habitacion habitacion = new Habitacion(resultado.getInt("Id"), resultado.getInt("Disponible"), 0, resultado.getFloat("PrecioPorDia"), resultado.getFloat("CostoPorDia"));
            habitaciones.add(habitacion);
        }
        return habitaciones;
    }
    
    public static ArrayList<Habitacion> getListadoHabitacionesDisponibles() throws SQLException{
        ArrayList<Habitacion> habitaciones = new ArrayList<Habitacion>();
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Habitacion where (Ocupada=0) AND (Disponible=1);");
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            Habitacion habitacion = new Habitacion(resultado.getInt("Id"), resultado.getInt("Disponible"), 0, resultado.getFloat("PrecioPorDia"), resultado.getFloat("CostoPorDia"));
            habitaciones.add(habitacion);
        }
        return habitaciones;
    }
    
    public static Habitacion getHabitacion(String id) throws SQLException{
        int Id = Integer.parseInt(id);
        Habitacion habitacion = null;
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Habitacion where (Id=?);");
        declaracion.setInt(1, Id);
        ResultSet resultado = declaracion.executeQuery();
        if(resultado.next()){
            habitacion = new Habitacion(resultado.getInt("Id"), resultado.getInt("Disponible"), 0, resultado.getFloat("PrecioPorDia"), resultado.getFloat("CostoPorDia"));
        }
        return habitacion;
    }

    public static void crearHabitacion(Habitacion habitacion) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Habitacion (PrecioPorDia, CostoPorDia, Ocupada, Disponible) values (?,?,?,?);");
        declaracion.setFloat(1, habitacion.getPrecio());
        declaracion.setFloat(2, habitacion.getCosto());
        declaracion.setInt(3, habitacion.getOcupada());
        declaracion.setInt(4, habitacion.getDisponible());
        declaracion.executeUpdate();
    }
    
    public static void modificarHabitacion(Habitacion habitacion)throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("update Habitacion set PrecioPorDia=?, CostoPorDia=?, Ocupada=?, Disponible=? where Id=?;");
        declaracion.setFloat(1, habitacion.getPrecio());
        declaracion.setFloat(2, habitacion.getCosto());
        declaracion.setInt(3, habitacion.getOcupada());
        declaracion.setInt(4, habitacion.getDisponible());
        declaracion.setInt(5, habitacion.getId());
        declaracion.executeUpdate();
    }

    public static void eliminarHabitaciones(int cantidad) throws SQLException {
        for (int i = 0; i < cantidad; i++) {
            ArrayList<Habitacion> habitacionesEliminables = getListadoHabitacionesModificables();
            eliminarUltimaHabitacion(habitacionesEliminables);
        }
    }
    
    public static void eliminarUltimaHabitacion(ArrayList<Habitacion> habitaciones) throws SQLException{
        int indiceFinal = habitaciones.size()-1;
        eliminarHabitacion(habitaciones.get(indiceFinal));
    }
    
    public static void eliminarHabitacion(Habitacion habitacion) throws SQLException{
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("delete from Habitacion where Id=?;");
        declaracion.setInt(1, habitacion.getId());
        declaracion.executeUpdate();
    }
    
}
