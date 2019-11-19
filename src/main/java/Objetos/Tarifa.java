package Objetos;

/**
 *
 * @author jose_
 */

public class Tarifa {

    private int id;
    private String descripcion;
    private float precio;
    
    public Tarifa(int id, String descripcion, float precio){
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    public Tarifa(String descripcion, float precio){
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    public Tarifa(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
}
