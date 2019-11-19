package Registro;

import Objetos.Vacacion;
import java.time.LocalDate;
import Utilidades.Utilidades;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */

public class RegistroVacaciones {
    
    public static void crearVacacion(LocalDate vacacion, int id) throws SQLException{
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Vacacion (IdContrato, Fecha) values (?,?);");
        declaracion.setInt(1, id);
        declaracion.setDate(2, Utilidades.convertirLocalDateADate(vacacion));
        declaracion.executeUpdate();
    }
    
    public static void crearVacaciones(ArrayList<LocalDate> vacaciones) throws SQLException{
        int idContrato = RegistroContratos.getIdUltimoContrato();
        for (LocalDate vacacion: vacaciones) {
            crearVacacion(vacacion, idContrato);
        }
    }

    public static ArrayList<Vacacion> getListadoVacaciones(int id) throws SQLException {
        Registro.iniciarSesion();
        ArrayList<Vacacion> vacaciones = new ArrayList<Vacacion>();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Vacacion where IdContrato=?;");
        declaracion.setInt(1, id);
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            Vacacion vacacion = new Vacacion(resultado.getInt("Id"), resultado.getInt("IdContrato"), Utilidades.sumarDia(resultado.getDate("Fecha").toLocalDate() , 1));
            vacaciones.add(vacacion);
        }
        return vacaciones;
    }

    public static void modificarVacaciones(ArrayList<Vacacion> vacaciones, ArrayList<LocalDate> vacacionesNuevas) throws SQLException {
        for (int i = 0; i < vacaciones.size(); i++) {
            vacaciones.get(i).setFecha(vacacionesNuevas.get(i));
        }
        for(Vacacion vacacion: vacaciones){
            modificarVacacion(vacacion);
        }
    }

    private static void modificarVacacion(Vacacion vacacion) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("update Vacacion set Fecha=? where Id=?;");
        declaracion.setDate(1, Utilidades.convertirLocalDateADate(vacacion.getFecha()));
        declaracion.setInt(2, vacacion.getId());
        declaracion.executeUpdate();
    }
}
