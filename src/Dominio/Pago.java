package Dominio;

import Controlador.Controlador;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pago {
    private int id;
    private Reserva reserva;
    private LocalDate fecha;
    private Tarifa tarifa;
    private ArrayList<Extras> extras;
    private double costoFinal;
    private boolean pagoRealizado;

    public double getCostoFinal() {
        return costoFinal;
    }

    public void setCostoFinal(double costoFinal) {
        this.costoFinal = costoFinal;
    }

    public ArrayList<Extras> getExtras() {
        return extras;
    }

    public void setExtras(ArrayList<Extras> extras) {
        this.extras = extras;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }

    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }


    public double getCostoExtras(){
        double costoExtras = 0;
        for(Extras e : extras){
            costoExtras += e.getCosto();
        }
        return costoExtras;
    }

    public Pago(int id, Reserva reserva, LocalDate fecha, Tarifa tarifa, ArrayList<Extras> extras, double costoFinal, boolean pagoRealizado){
        this.id = id;
        this.reserva = reserva;
        this.fecha = fecha;
        this.tarifa = tarifa;
        this.extras = extras;
        this.costoFinal = costoFinal;
        this.pagoRealizado = pagoRealizado;
    }

    @Override
    public String toString(){
        return this.id + ". Socio: " + this.reserva.getSocio().getNombre() +
                ". En " + this.reserva.getCancha().getNombre() +
                ". Fecha: " + this.reserva.getFecha() + " " + this.reserva.getTurno().getHoraInicio() +
                (this.reserva.isPrepago() ? ". Costo reserva prepaga: " + this.reserva.getTarifa().getCosto() : ". Costo tarifa: " + this.tarifa.getCosto() +
                        ", seña: " + this.reserva.getSenia() + ", total restante: $" + (this.tarifa.getCosto() - this.reserva.getSenia())) +
                (this.getCostoExtras() > 0 ? ". Extras: " + this.extras + " $"+ getCostoExtras() : ". Sin extras") + ". Total: $" + this.costoFinal;
    }



}
