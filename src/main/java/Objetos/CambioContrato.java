package Objetos;

import java.time.LocalDate;

/**
 *
 * @author jose_
 */

public class CambioContrato {

    private int id, idContratacion;
    private LocalDate fecha;
    private String comentario;
    
    public CambioContrato(int id, int idContratacion, LocalDate fecha, String comentario){
        this.id= id;
        this.idContratacion = idContratacion;
        this.fecha = fecha;
        this.comentario = comentario;
    }    
    
    public CambioContrato(int idContratacion, LocalDate fecha, String comentario){
        this.idContratacion = idContratacion;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(int idContratacion) {
        this.idContratacion = idContratacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
