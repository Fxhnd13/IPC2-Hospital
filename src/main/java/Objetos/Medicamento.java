package Objetos;

/**
 *
 * @author jose_
 */

public class Medicamento {

    private int id, cantidad, cantidadMinima;
    private String nombre;
    float precio, costo; 
    
    public Medicamento(int id, int cantidad, int cantidadMinima, String nombre, float precio, float costo){
        this.id= id;
        this.cantidad = cantidad;
        this.cantidadMinima  = cantidadMinima;
        this.nombre = nombre;
        this.precio= precio;
        this.costo = costo;
    }
    
    public Medicamento(int cantidad, int cantidadMinima, String nombre, float precio, float costo){
        this.cantidad = cantidad;
        this.cantidadMinima = cantidadMinima;
        this.nombre = nombre;
        this.precio = precio;
        this.costo = costo;
    }
    
    public Medicamento(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
    
}
