package Registro;

import Objetos.Cuenta;
import Objetos.Pago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author jose_
 */

public class RegistroCuentas {

    public static int getUltimoId() throws SQLException {
        Registro.iniciarSesion();
        int valor = 0;
        Statement declaracion = Registro.conexion.createStatement();
        ResultSet resultado = declaracion.executeQuery("select MAX(Id) as Id from Cuenta;");
        if (resultado.next()) {
            valor = resultado.getInt("Id");
        }
        return valor;
    }
    
    public static Cuenta getCuenta(int id) throws SQLException{
        Cuenta cuenta = new Cuenta();
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Cuenta where Id=?;");
        declaracion.setInt(1, id);
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            cuenta.setFecha(resultado.getDate("Fecha").toLocalDate());
            cuenta.setId(id);
            cuenta.setResponsableDeLaCompra(resultado.getString("ResponsableDeLaCompra"));
            cuenta.setResponsableDeLaVenta(resultado.getString("ResponsableDeLaVenta"));
            cuenta.setTotal(resultado.getFloat("Total"));
            cuenta.setPagos(RegistroPagos.getPagos(id));
        }
        return cuenta;
    }

    public static void crearCuentaFarmacia(Cuenta cuenta) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Cuenta (Fecha, ResponsableDeLaVenta, ResponsableDeLaCompra, Total) values (?,?,?,?);");
        declaracion.setDate(1, Utilidades.Utilidades.convertirLocalDateADate(cuenta.getFecha()));
        declaracion.setString(2, cuenta.getResponsableDeLaVenta());
        declaracion.setString(3, cuenta.getResponsableDeLaCompra());
        declaracion.setFloat(4, cuenta.getTotal());
        declaracion.executeUpdate();
        for (Pago pago : cuenta.getPagos()) {
            pago.setIdCuenta(RegistroCuentas.getUltimoId());
            RegistroPagos.crearPago(pago);
        }
    }
    
    public static void crearCuentaNormal(Cuenta cuenta) throws SQLException{
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Cuenta (ResponsableDeLaCompra,Total) values (?,?);");
        declaracion.setString(1, cuenta.getResponsableDeLaCompra());
        declaracion.setFloat(2, cuenta.getTotal());
        declaracion.executeUpdate();
        for (Pago pago : cuenta.getPagos()) {
            pago.setIdCuenta(RegistroCuentas.getUltimoId());
            RegistroPagos.crearPago(pago);
        }
    }
    
    public static Cuenta getCuentaActivaDe(String nombreCliente) throws SQLException{
        Registro.iniciarSesion();
        Cuenta cuenta = null;
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from cuenta where (ResponsableDeLaCompra=?) AND (Fecha IS NULL);");
        declaracion.setString(1, nombreCliente);
        ResultSet resultado = declaracion.executeQuery();
        while(resultado.next()){
            cuenta = new Cuenta();
            cuenta.setId(resultado.getInt("Id"));
            cuenta.setFecha(null);
            cuenta.setResponsableDeLaCompra(nombreCliente);
            cuenta.setTotal(resultado.getFloat("Total"));
        }
        return cuenta;
    }
    
}
