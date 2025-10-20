package Dominio;

import java.time.Duration;
import java.time.LocalTime;

public class Turno {

    private int id;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Duration duracion;
    private String deporte;
    private boolean habilitado;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Duration getDuracion() {
        return duracion;
    }
    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public String getDeporte(){
        return deporte;
    }
    public void setDeporte (String deporte){
        this.deporte = deporte;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Turno(int id, String deporte, LocalTime horaInicio, LocalTime horaFin, Duration duracion, boolean habilitado){
        this.id = id;
        this.deporte = deporte;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.duracion = duracion;
        this.habilitado = habilitado;
    }

    public String duration(){
        int h = this.duracion.toString().indexOf("H");
        int m = this.duracion.toString().indexOf("M");
        String horas;
        String minutos;
        if(h >= 0){
            horas = this.duracion.toString().substring(h-1, h).concat(" horas ");
            if(m >= 0){
                minutos = this.duracion.toString().substring(h+1, m).concat(" minutos");
                return horas.concat(minutos);
            } else {
                return horas;
            }
        }
        if(m >= 0){
            minutos = this.duracion.toString().substring(m-1, m).concat(" minutos");
            return minutos;
        }
        return "";
    }

    @Override
    public String toString()
    {
        return this.id + ": " + this.deporte +
               ". De " + this.horaInicio +
            " hasta " + this.horaFin + ". Duración: " +
                duration();
    }
}
