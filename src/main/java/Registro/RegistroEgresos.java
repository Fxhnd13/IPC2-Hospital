package Registro;

import Objetos.Egreso;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jose_
 */

public class RegistroEgresos {

    public static void crearEgreso(Egreso egreso) throws SQLException{
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Egreso (Tipo, Descripcion, Fecha, Monto) values (?,?,?,?);");
        declaracion.setString(1, egreso.getTipo());
        declaracion.setString(2, egreso.getDescripcion());
        declaracion.setDate(3, Utilidades.Utilidades.convertirLocalDateADate(egreso.getFecha()));
        declaracion.setFloat(4, egreso.getMonto());
        declaracion.executeUpdate();
    }
    
}
