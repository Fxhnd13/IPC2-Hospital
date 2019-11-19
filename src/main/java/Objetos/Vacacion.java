package Objetos;

import java.time.LocalDate;

/**
 *
 * @author jose_
 */

public class Vacacion {

    private int id, idContrato;
    private LocalDate fecha;

    public Vacacion(int id, int idContrato, LocalDate fecha) {
        this.id = id;
        this.idContrato = idContrato;
        this.fecha = fecha;
    }
    
    public Vacacion(int idContrato, LocalDate fecha){
        this.idContrato = idContrato;
        this.fecha = fecha;
    }
    
    public Vacacion(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
}
