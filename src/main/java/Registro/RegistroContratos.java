package Registro;

import Objetos.Contrato;
import Objetos.Egreso;
import Objetos.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Utilidades.Utilidades;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class RegistroContratos {

    public static void crearContrato(Contrato contrato) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("insert into Contratacion (Fecha, Sueldo, Nombre, Cui, Area, PagoIGSS, PagoIRTRA, Estado, FechaSolvente)"
                + "values (?,?,?,?,?,?,?,?,?);");
        declaracion.setDate(1, Utilidades.convertirLocalDateADate(contrato.getFechaDeCreacion()));
        declaracion.setFloat(2, contrato.getSueldo());
        declaracion.setString(3, contrato.getNombreCompleto());
        declaracion.setString(4, contrato.getCui());
        declaracion.setInt(5, contrato.getArea());
        declaracion.setInt(6, contrato.getPagoIgss());
        declaracion.setInt(7, contrato.getPagoIrtra());
        declaracion.setInt(8, contrato.getEstado());
        declaracion.setDate(9, Utilidades.convertirLocalDateADate(contrato.getFechaSolvente()));
        declaracion.executeUpdate();
    }

    public static void terminarContrato(Contrato contrato) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("update Contratacion set Estado=0 where Id=?");
        declaracion.setInt(1, contrato.getId());
        declaracion.executeUpdate();
    }

    public static ArrayList<Contrato> getListadoContratos() throws SQLException {
        Registro.iniciarSesion();
        ArrayList<Contrato> contratos = new ArrayList<Contrato>();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Contratacion where Estado=1;");
        ResultSet resultado = declaracion.executeQuery();
        while (resultado.next()) {
            Contrato contrato = new Contrato();
            contrato.setId(resultado.getInt("Id"));
            contrato.setArea(resultado.getInt("Area"));
            contrato.setCui(resultado.getString("Cui"));
            contrato.setEstado(resultado.getInt("Estado"));
            contrato.setFechaDeCreacion(Utilidades.sumarDia(resultado.getDate("Fecha").toLocalDate(), 1));
            contrato.setFechaSolvente(Utilidades.sumarDia(resultado.getDate("FechaSolvente").toLocalDate(), 1));
            contrato.setNombreCompleto(resultado.getString("Nombre"));
            contrato.setPagoIgss(resultado.getInt("PagoIGSS"));
            contrato.setPagoIrtra(resultado.getInt("PagoIRTRA"));
            contrato.setSueldo(resultado.getFloat("Sueldo"));
            contratos.add(contrato);
        }
        return contratos;
    }

    public static ArrayList<Contrato> getListadoContratosConUsuarioActivo() throws SQLException {
        Registro.iniciarSesion();
        ArrayList<Contrato> contratos = new ArrayList<Contrato>();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Contratacion c join Usuario u on (u.IdContrato=c.Id) where c.Estado=1;");
        ResultSet resultado = declaracion.executeQuery();
        while (resultado.next()) {
            Contrato contrato = new Contrato();
            contrato.setId(resultado.getInt("Id"));
            contrato.setArea(resultado.getInt("Area"));
            contrato.setCui(resultado.getString("Cui"));
            contrato.setEstado(resultado.getInt("Estado"));
            contrato.setFechaDeCreacion(Utilidades.sumarDia(resultado.getDate("Fecha").toLocalDate(), 1));
            contrato.setFechaSolvente(Utilidades.sumarDia(resultado.getDate("FechaSolvente").toLocalDate(), 1));
            contrato.setNombreCompleto(resultado.getString("Nombre"));
            contrato.setPagoIgss(resultado.getInt("PagoIGSS"));
            contrato.setPagoIrtra(resultado.getInt("PagoIRTRA"));
            contrato.setSueldo(resultado.getFloat("Sueldo"));
            contratos.add(contrato);
        }
        return contratos;
    }
    
    public static String getNombrePorUsername(String username) throws SQLException{
        String nombre = null;
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Contratacion c join Usuario u on (u.IdContrato=c.Id) where u.Username=?;");
        declaracion.setString(1, username);
        ResultSet resultado = declaracion.executeQuery();
        while (resultado.next()) {
            nombre = resultado.getString("Nombre");
        }
        return nombre;
    }
    
    public static ArrayList<Contrato> getListadoContratosSinUsuarioActivo() throws SQLException {
        Registro.iniciarSesion();
        ArrayList<Contrato> contratos = new ArrayList<Contrato>();
        for (Contrato contrato : getListadoContratos()) {
            Usuario usuario = RegistroUsuarios.getUsuario(contrato.getId());
            if(usuario==null){
                contratos.add(contrato);
            }
        }
        return contratos;
    }

    public static Contrato getContratoPorId(String id) throws SQLException {
        Registro.iniciarSesion();
        Contrato contrato = null;
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Contratacion where Id=?");
        declaracion.setInt(1, Integer.parseInt(id));
        ResultSet resultado = declaracion.executeQuery();
        if (resultado.next()) {
            contrato = new Contrato();
            contrato.setId(resultado.getInt("Id"));
            contrato.setArea(resultado.getInt("Area"));
            contrato.setCui(resultado.getString("Cui"));
            contrato.setEstado(resultado.getInt("Estado"));
            contrato.setFechaDeCreacion(Utilidades.sumarDia(resultado.getDate("Fecha").toLocalDate(), 1));
            contrato.setFechaSolvente(Utilidades.sumarDia(resultado.getDate("FechaSolvente").toLocalDate(), 1));
            contrato.setNombreCompleto(resultado.getString("Nombre"));
            contrato.setPagoIgss(resultado.getInt("PagoIGSS"));
            contrato.setPagoIrtra(resultado.getInt("PagoIRTRA"));
            contrato.setSueldo(resultado.getFloat("Sueldo"));
        }
        return contrato;
    }

    public static int getIdUltimoContrato() throws SQLException {
        Registro.iniciarSesion();
        int valor = 0;
        Statement declaracion = Registro.conexion.createStatement();
        ResultSet resultado = declaracion.executeQuery("select MAX(Id) as Id from Contratacion;");
        if (resultado.next()) {
            valor = resultado.getInt("Id");
        }
        return valor;
    }

    public static void modificarContrato(Contrato contrato) throws SQLException {
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("update Contratacion set Sueldo=?, Nombre=?, Area=?, PagoIGSS=?, PagoIRTRA=?, Estado=?, FechaSolvente=?"
                + " where Id=?;");
        declaracion.setDate(1, Utilidades.convertirLocalDateADate(contrato.getFechaDeCreacion()));
        declaracion.setFloat(1, contrato.getSueldo());
        declaracion.setString(2, contrato.getNombreCompleto());
        declaracion.setInt(3, contrato.getArea());
        declaracion.setInt(4, contrato.getPagoIgss());
        declaracion.setInt(5, contrato.getPagoIrtra());
        declaracion.setInt(6, contrato.getEstado());
        declaracion.setDate(7, Utilidades.convertirLocalDateADate(contrato.getFechaSolvente()));
        declaracion.setInt(8, contrato.getId());
        declaracion.executeUpdate();
    }

    public static void pagarMes() throws SQLException {
        for (Contrato contrato : getListadoContratos()) {
            contrato.setFechaSolvente(Utilidades.primeroDelSiguienteMes(contrato.getFechaSolvente()));
            modificarContrato(contrato);
            float sueldoFinal = contrato.getSueldo();
            float pagoIgss = RegistroTarifas.getListadoTarifas().get(2).getPrecio() * contrato.getSueldo();
            float pagoIrtra = RegistroTarifas.getListadoTarifas().get(3).getPrecio() * contrato.getSueldo();
            if(contrato.getPagoIgss()==1){
                sueldoFinal = sueldoFinal - pagoIgss;
                Egreso egresoIgss = new Egreso("Pago Igss", "Pago Igss de: "+ contrato.getNombreCompleto(), LocalDate.now(), pagoIgss);
                RegistroEgresos.crearEgreso(egresoIgss);
            }
            if(contrato.getPagoIrtra()==1){
                sueldoFinal = sueldoFinal - pagoIrtra;
                Egreso egresoIrtra = new Egreso("Pago Irtra", "Pago Irtra de: "+ contrato.getNombreCompleto(), LocalDate.now(), pagoIrtra);
                RegistroEgresos.crearEgreso(egresoIrtra);
            }
            Egreso egreso = new Egreso("Sueldo", "Pago de: " + contrato.getNombreCompleto(), LocalDate.now(), sueldoFinal);
            RegistroEgresos.crearEgreso(egreso);
        }
    }

    public static boolean existeContratoActivo(String cui) throws SQLException {
        boolean valor = false;
        Registro.iniciarSesion();
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Contratacion where (Cui=?) AND (Estado=1);");
        declaracion.setString(1, cui);
        ResultSet resultado = declaracion.executeQuery();
        if(resultado.next())valor=true;
        return valor;
    }

    static int getIdPorUsername(String username) throws SQLException {
        int valor =0;
        PreparedStatement declaracion = Registro.conexion.prepareStatement("select * from Contratacion c join Usuario u on (u.IdContrato=c.Id) where u.Username=?;");
        declaracion.setString(1, username);
        ResultSet resultado = declaracion.executeQuery();
        while (resultado.next()) {
            valor = resultado.getInt("IdContrato");
        }
        return valor;
    }

}
