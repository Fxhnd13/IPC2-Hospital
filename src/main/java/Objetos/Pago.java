package Objetos;

import java.time.LocalDate;

/**
 *
 * @author jose_
 */

public class Pago {

    private int id, idCuenta, cantidad;
    private String tipo, descripcion;
    private float total, precio;
    private LocalDate fecha;

    public Pago(int id, int idCuenta, int cantidad, String tipo, String descripcion, float total, float precio, LocalDate fecha) {
        this.id = id;
        this.idCuenta = idCuenta;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.total = total;
        this.fecha = fecha;
    }

    public Pago(int idCuenta, int cantidad, String tipo, String descripcion, float total, float precio, LocalDate fecha) {
        this.idCuenta = idCuenta;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.total = total;
        this.fecha = fecha;
    }

    public Pago() {
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
}
