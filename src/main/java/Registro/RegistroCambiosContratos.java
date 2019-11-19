package Registro;

import Objetos.CambioContrato;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */

public class RegistroCambiosContratos {

    public static void guardarCambios(ArrayList<CambioContrato> historial) throws SQLException {
        for (CambioContrato cambioContrato : historial) {
            guardarCambio(cambioContrato);
        }
    }

    public static void guardarCambio(CambioContrato cambio) throws SQLException{
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into HistorialContrato (IdContratacion, Fecha, Comentario) values (?,?,?);");
        declaracion.setInt(1, cambio.getIdContratacion());
        declaracion.setDate(2, Utilidades.Utilidades.convertirLocalDateADate(cambio.getFecha()));
        declaracion.setString(3, cambio.getComentario());
        declaracion.executeUpdate();
    }
}
