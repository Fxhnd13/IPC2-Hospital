package Objetos;

import java.time.LocalDate;

/**
 *
 * @author jose_
 */

public class Contrato {
    
    private int id, area, pagoIgss, pagoIrtra, estado; 
    private LocalDate fechaDeCreacion, fechaSolvente;
    private float sueldo;
    private String nombreCompleto, cui;
    
    public Contrato(int id, int area, int pagoIgss, int pagoIrtra, int estado, LocalDate fechaDeCreacion, float sueldo, String nombre, String cui, LocalDate fechaSolvente){
        this.id= id;
        this.area = area;
        this.pagoIgss = pagoIgss;
        this.pagoIrtra = pagoIrtra;
        this.estado = estado;
        this.fechaDeCreacion = fechaDeCreacion;
        this.sueldo = sueldo;
        this.nombreCompleto = nombre;
        this.cui = cui;
    }
    
    public Contrato(int area, int pagoIgss, int pagoIrtra, LocalDate fechaDeCreacion, float sueldo, String nombre, String cui){
        this.area = area;
        this.pagoIgss = pagoIgss;
        this.pagoIrtra = pagoIrtra;
        this.fechaDeCreacion = fechaDeCreacion;
        this.fechaSolvente = Utilidades.Utilidades.primeroDelSiguienteMes(fechaDeCreacion);
        this.sueldo = sueldo;
        this.nombreCompleto = nombre;
        this.cui = cui;
        this.estado=1;
    }
    
    public Contrato(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPagoIgss() {
        return pagoIgss;
    }

    public void setPagoIgss(int pagoIgss) {
        this.pagoIgss = pagoIgss;
    }

    public int getPagoIrtra() {
        return pagoIrtra;
    }

    public void setPagoIrtra(int pagoIrtra) {
        this.pagoIrtra = pagoIrtra;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDate fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public float getSueldo() {
        return sueldo;
    }

    public void setSueldo(float sueldo) {
        this.sueldo = sueldo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public LocalDate getFechaSolvente() {
        return fechaSolvente;
    }

    public void setFechaSolvente(LocalDate fechaSolvente) {
        this.fechaSolvente = fechaSolvente;
    }
    
}
