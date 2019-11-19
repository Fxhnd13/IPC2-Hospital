package Objetos;

import Registro.RegistroCuentas;
import Registro.RegistroPagos;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */

public class Cuenta {

    private int id; 
    private LocalDate fecha;
    private String responsableDeLaVenta, responsableDeLaCompra;
    private float total;
    private ArrayList<Pago> pagos = new ArrayList<Pago>();
    
    public Cuenta(){
    }
    
    public Cuenta(int id, LocalDate fecha, String responsableDeLaVenta, String responsableDeLaCompra) throws SQLException{
        this.id = id;
        this.fecha = fecha;
        this.responsableDeLaCompra = responsableDeLaCompra;
        this.responsableDeLaVenta = responsableDeLaVenta;
        cargarPagos();
    }

    public void cargarPagos() throws SQLException{
        pagos = RegistroPagos.getPagos(this.id);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getResponsableDeLaVenta() {
        return responsableDeLaVenta;
    }

    public void setResponsableDeLaVenta(String responsableDeLaVenta) {
        this.responsableDeLaVenta = responsableDeLaVenta;
    }

    public String getResponsableDeLaCompra() {
        return responsableDeLaCompra;
    }

    public void setResponsableDeLaCompra(String responsableDeLaCompra) {
        this.responsableDeLaCompra = responsableDeLaCompra;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public ArrayList<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(ArrayList<Pago> pagos) {
        this.pagos = pagos;
    }

    public void calcularTotal() {
        total=0;
        for (Pago pago : pagos) {
            total += pago.getTotal();
        }
    }
    
}
