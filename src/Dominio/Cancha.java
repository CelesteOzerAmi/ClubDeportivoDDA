package Dominio;

import java.util.ArrayList;

public class Cancha {

    private int idCancha;
    private String nombre;
    private String deporte;
    private boolean cubierta;
    private int capacidad;
    private Condicion condicion;
    private ArrayList<String> caracteristicas;

    public enum Condicion {
        DISPONIBLE, RESERVADA, OCUPADA, REPARACION
    }

    public Cancha(int idCancha, String nombre, String deporte, boolean cubierta, int capacidad, Condicion condicion, ArrayList<String> caracteristicas){
        this.idCancha = idCancha;
        this.nombre = nombre;
        this.deporte = deporte;
        this.cubierta = cubierta;
        this.capacidad = capacidad;
        this.condicion = condicion;
        this.caracteristicas = caracteristicas;
    }

    public int getIdCancha() {
        return idCancha;
    }
    public void setIdCancha(int idCancha) {
        this.idCancha = idCancha;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDeporte() {
        return deporte;
    }
    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public boolean isCubierta() {
        return cubierta;
    }
    public void setCubierta(boolean cubierta) {
        this.cubierta = cubierta;
    }

    public int getCapacidad() {
        return capacidad;
    }
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Condicion getCondicion() {
        return condicion;
    }
    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public ArrayList<String> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(ArrayList<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    @Override
    public String toString() {
        return
                idCancha + ", " + nombre +
                ". Deporte: " + deporte +
                ". Capacidad: " + capacidad +
                ". Cubierta? " + (cubierta ? "Sí. " : "No. ") +
                condicion.toString() + ". Características: " +
                (caracteristicas.toString().equals("[]") || caracteristicas.toString().isEmpty() ? "Sin características" : caracteristicas.toString());
    }

    public String resumen(){
        return "Id: " + idCancha + ". " + nombre
        + ". Deporte: " + deporte;
    }
}
