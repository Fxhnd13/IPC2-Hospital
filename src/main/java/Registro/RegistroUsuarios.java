package Registro;

import Objetos.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose_
 */
public class RegistroUsuarios {

    public static int getArea(String username, String password) throws SQLException{
        int valor = 0;
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select c.Area from Usuario u join Contratacion c on (u.IdContrato=c.Id) AND (Username=?) AND (Password=?);");
        declaracion.setString(1, username);
        declaracion.setString(2, password);
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            valor = resultado.getInt("Area");
        }
        return valor;    
    }
    
    public static Usuario getUsuario(int idContrato) throws SQLException{
        Usuario usuario = null;
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Usuario where IdContrato=?");
        declaracion.setInt(1, idContrato);
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            usuario = new Usuario(resultado.getString("Username"), resultado.getString("Password"), resultado.getInt("IdContrato"));
        }
        return usuario;
    }

    public static void crearUsuario(Usuario usuario) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Usuario (Username, Password, IdContrato) values (?,?,?);");
        declaracion.setString(1, usuario.getUsername());
        declaracion.setString(2, usuario.getPassword());
        declaracion.setInt(3, usuario.getIdContrato());
        declaracion.executeUpdate();
    }

    public static void modificarUsername(Usuario usuario) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("update Usuario set Username=? where IdContrato=?;");
        declaracion.setString(1, usuario.getUsername());
        declaracion.setString(2, usuario.getPassword());
        declaracion.setInt(3, usuario.getIdContrato());
        declaracion.executeUpdate();
    }

    public static void modificarTodo(Usuario usuario) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("update Usuario set Username=?, Password=? where IdContrato=?;");
        declaracion.setString(1, usuario.getUsername());
        declaracion.setString(2, usuario.getPassword());
        declaracion.setInt(3, usuario.getIdContrato());
        declaracion.executeUpdate();
    }

    public static void eliminarUsuario(int idContrato) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("delete from Usuario where IdContrato=?;");
        declaracion.setInt(1, idContrato);
        declaracion.executeUpdate();
    }
    
}
