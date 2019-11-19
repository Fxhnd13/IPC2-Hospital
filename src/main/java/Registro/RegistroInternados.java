package Registro;

import Objetos.Internado;
import Objetos.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */

public class RegistroInternados {

    public static void crearInternado(Internado internado) throws SQLException{
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Internado (FechaEntrada,IdHabitacion,CuiPaciente) values (?,?,?);");
        declaracion.setDate(1, Utilidades.Utilidades.convertirLocalDateADate(internado.getFechaEntrada()));
        declaracion.setInt(2, internado.getIdHabitacion());
        declaracion.setString(3, internado.getCuiPaciente());
        declaracion.executeUpdate();
    }
    
    public static ArrayList<Paciente> getInternadosAsignadosA(String username) throws SQLException{
        Registro.iniciarSesion();
        ArrayList<Paciente> pacientesAsignados = new ArrayList<Paciente>();
        int idContrato = RegistroContratos.getIdPorUsername(username);
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Internado i join AsignacionInternado a on(i.Id=a.IdInternado) where (a.IdContrato=?);");
        declaracion.setInt(1, idContrato);
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            
        }
        return pacientesAsignados;
    }
    
}
