package Dominio;

import java.time.LocalDate;
import java.util.Optional;

public class Socio {

    private int idSocio;
    private String nombre;
    private String apaterno;
    private String amaterno;
    private String num_documento;
    private LocalDate fecha_nacimiento;
    private String telefono;
    private String pais;
    private static int contador = 1;

    public Socio(int idSocio, String nombre, String apaterno, String amaterno, String num_documento,
                 LocalDate fecha_nacimiento, String telefono, String pais){
        this.idSocio = contador++;
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this.num_documento = num_documento;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.pais = pais;
    }

    public int getIdSocio() {
        return idSocio;
    }
    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApaterno() {
        return apaterno;
    }
    public void setApaterno(String apaterno) {
        this.apaterno = apaterno;
    }

    public String getAmaterno() {
        return amaterno;
    }
    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNum_documento() {
        return num_documento;
    }
    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString(){
        return
                "Id " + idSocio + ": " + nombre + " " + apaterno
                + (!amaterno.isEmpty() ? " " + amaterno : "")
                + ". Fecha de nacimiento: " + fecha_nacimiento.toString() + ", "
                + pais + ". Documento: " + num_documento
                + ". Tel: " + telefono;
    }

    public String resumen(){
        return "Id: " + idSocio + ". " + nombre + " " + apaterno;
    }
}
