package Objetos;

import java.time.LocalDate;

/**
 *
 * @author jose_
 */

public class Internado {

    private LocalDate fechaEntrada, fechaSalida;
    private String cuiPaciente;
    private int id, idHabitacion;
    private Paciente paciente; 
    
    public Internado(){
        
    }

    public Internado(LocalDate fechaEntrada, LocalDate fechaSalida, String cuiPaciente, int id, int idHabitacion) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.cuiPaciente = cuiPaciente;
        this.id = id;
        this.idHabitacion = idHabitacion;
    }

    public Internado(LocalDate fechaEntrada, String cuiPaciente, int idHabitacion) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.cuiPaciente = cuiPaciente;
        this.idHabitacion = idHabitacion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }
    
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getCuiPaciente() {
        return cuiPaciente;
    }

    public void setCuiPaciente(String cuiPaciente) {
        this.cuiPaciente = cuiPaciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
