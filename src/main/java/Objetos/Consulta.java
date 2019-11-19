package Objetos;

import java.time.LocalDate;

/**
 *
 * @author jose_
 */

public class Consulta {

    private int id;
    private String causa, motivo, cuiPaciente, nombreMedico;
    private LocalDate fecha;
    
    public Consulta(String causa, String motivo, String cuiPaciente, LocalDate fecha, String nombreMedico){
        this.causa = causa;
        this.motivo = motivo;
        this.cuiPaciente = cuiPaciente;
        this.fecha = fecha;
        this.nombreMedico = nombreMedico;
    }

    public Consulta(int id, String causa, String motivo, String cuiPaciente, LocalDate fecha, String nombreMedico) {
        this.id = id;
        this.causa = causa;
        this.motivo = motivo;
        this.cuiPaciente = cuiPaciente;
        this.fecha = fecha;
        this.nombreMedico = nombreMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getCuiPaciente() {
        return cuiPaciente;
    }

    public void setCuiPaciente(String cuiPaciente) {
        this.cuiPaciente = cuiPaciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
}
