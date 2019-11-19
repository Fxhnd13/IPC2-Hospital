package Registro;

import Objetos.Medicamento;
import Objetos.Pago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */

public class RegistroPagos {

    public static ArrayList<Pago> getPagos(int id) throws SQLException {
        ArrayList<Pago> pagos = new ArrayList<>();
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Pago where IdCuenta=?;");
        declaracion.setInt(1, id);
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            Pago pago = new Pago();
            pago.setIdCuenta(id);
            pago.setCantidad(resultado.getInt("Cantidad"));
            pago.setFecha(resultado.getDate("Fecha").toLocalDate());
            pago.setDescripcion(resultado.getString("Descripcion"));
            pago.setId(resultado.getInt("Id"));
            pago.setTipo(resultado.getString("Tipo"));
            pago.setPrecio(resultado.getFloat("Precio"));
            pago.setTotal(resultado.getFloat("Total"));
            pagos.add(pago);
        }
        return pagos;
    }

    static void crearPago(Pago pago) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Pago (IdCuenta,Tipo,Descripcion,Cantidad,Precio,Total,Fecha) values (?,?,?,?,?,?,?);");
        declaracion.setInt(1, pago.getIdCuenta());
        declaracion.setString(2, pago.getTipo());
        declaracion.setString(3, pago.getDescripcion());
        declaracion.setInt(4, pago.getCantidad());
        declaracion.setFloat(5, pago.getPrecio());
        declaracion.setFloat(6, pago.getTotal());
        declaracion.setDate(7, Utilidades.Utilidades.convertirLocalDateADate(pago.getFecha()));
        declaracion.executeUpdate();
        if(pago.getTipo().equals("Venta Medicamento")){
            Medicamento medicamento = RegistroMedicamentos.getMedicamentoPorNombre(pago.getDescripcion());
            medicamento.setCantidad(medicamento.getCantidad()-pago.getCantidad());
            RegistroMedicamentos.modificarMedicamento(medicamento);
        }
    }
}
