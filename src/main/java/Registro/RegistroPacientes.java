package Registro;

import Objetos.Paciente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */

public class RegistroPacientes {

    public static ArrayList<Paciente> getListadoPacientes() throws SQLException{
        ArrayList<Paciente> pacientes = new ArrayList<>();
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Paciente;");
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            Paciente paciente = new Paciente();
            paciente.setCui(resultado.getString("Cui"));
            paciente.setNombre(resultado.getString("Nombre"));
            paciente.setAltura(resultado.getFloat("Altura"));
            paciente.setContacto(resultado.getInt("Contacto"));
            paciente.setPeso(resultado.getFloat("Peso"));
            paciente.setTipoDeSangre(resultado.getString("TipoDeSangre"));
            pacientes.add(paciente);
        }
        return pacientes;
    }
    
    public static Paciente getPaciente(String cui) throws SQLException{
        Registro.iniciarSesion();
        Paciente paciente = null;
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Paciente where Cui=?;");
        declaracion.setString(1, cui);
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            paciente = new Paciente();
            paciente.setCui(resultado.getString("Cui"));
            paciente.setNombre(resultado.getString("Nombre"));
            paciente.setAltura(resultado.getFloat("Altura"));
            paciente.setContacto(resultado.getInt("Contacto"));
            paciente.setPeso(resultado.getFloat("Peso"));
            paciente.setTipoDeSangre(resultado.getString("TipoDeSangre"));
        }
        return paciente;
        
    }

    public static void crearPaciente(Paciente paciente) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Paciente (Cui,Nombre,Altura,Peso,Edad,Contacto,TipoDeSangre) values (?,?,?,?,?,?,?);");
        declaracion.setString(1, paciente.getCui());
        declaracion.setString(2, paciente.getNombre());
        declaracion.setFloat(3, paciente.getAltura());
        declaracion.setFloat(4, paciente.getPeso());
        declaracion.setInt(5, paciente.getEdad());
        declaracion.setInt(6, paciente.getContacto());
        declaracion.setString(7, paciente.getTipoDeSangre());
        declaracion.executeUpdate();
    }
}
