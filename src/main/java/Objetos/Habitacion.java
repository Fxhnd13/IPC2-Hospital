package Objetos;

/**
 *
 * @author jose_
 */

public class Habitacion {

    private int id, ocupada, disponible;
    private float precio, costo;
    
    public Habitacion(){
        
    }
    
    public Habitacion(int id, int disponible, int ocupada, float precio, float costo){
        this.id = id;
        this.disponible = disponible;
        this.ocupada = ocupada;
        this.precio = precio;
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOcupada() {
        return ocupada;
    }

    public void setOcupada(int ocupada) {
        this.ocupada = ocupada;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
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

    public void setDisponibilidad() {
        if(this.disponible==1){
            this.disponible=0;
        }else{
            this.disponible=1;
        }
    }
    
}
