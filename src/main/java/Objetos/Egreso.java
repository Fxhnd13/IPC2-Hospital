package Objetos;

import java.time.LocalDate;

/**
 *
 * @author jose_
 */

public class Egreso {

    private int id;
    private String tipo, descripcion;
    private LocalDate fecha;
    private float monto;
    
    public Egreso(String tipo, String descripcion, LocalDate fecha, float monto){
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.monto = monto;
    }
    
    public Egreso(int id, String tipo, String descripcion, LocalDate fecha, float monto){
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.monto = monto;
    }
    
    public Egreso(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
}
