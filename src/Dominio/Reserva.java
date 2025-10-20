package Dominio;
import java.time.LocalDate;


public class Reserva {
    private int idReserva;
    private Socio socio;
    private Cancha cancha;
    private LocalDate fechaReserva;
    private LocalDate fecha;
    private boolean fechaEspecial; // determina la tarifa que se aplica.
    private Turno turno;
    private Tarifa tarifa;
    private boolean prepago; // indica si se abonó el costo total. si es true, tarifa se congela; sino tarifa se actualiza.
    private double senia;
    private boolean enCurso; // indica si el socio ingresó a la cancha reservada.
    private String observacion;


    public Reserva(int id, Socio socio, Cancha cancha, LocalDate fechaReserva, LocalDate fecha, boolean fechaEspecial, Turno turno, Tarifa tarifa, boolean prepago, double senia, boolean enCurso, String observacion){
        this.idReserva = id;
        this.socio = socio;
        this.cancha = cancha;
        this.fechaReserva = fechaReserva;
        this.fecha = fecha;
        this.fechaEspecial = fechaEspecial;
        this.turno = turno;
        this.tarifa = tarifa;
        this.prepago = prepago;
        this.senia = (isPrepago() ? tarifa.getCosto() : senia); // si isPrepago, se guarda seña como costo total de tarifa, sino como valor de seña ingresada.
        this.enCurso = enCurso;
        this.observacion = observacion;
    }

    public int getIdReserva() {
        return idReserva;
    }
    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public Socio getSocio() {
        return socio;
    }
    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public Cancha getCancha() {
        return cancha;
    }
    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public double getSenia() {
        return senia;
    }
    public void setSenia(double senia) {
        this.senia = senia;
    }

    public boolean isPrepago() {
        return prepago;
    }
    public void setPrepago(boolean prepago) {
        this.prepago = prepago;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }
    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isFechaEspecial() {
        return fechaEspecial;
    }
    public void setFechaEspecial(boolean fechaEspecial) {
        this.fechaEspecial = fechaEspecial;
    }

    public Turno getTurno() {
        return turno;
    }
    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Tarifa getTarifa() {
        return tarifa;
    }
    public void setTarifa(Tarifa tarifa) {
        this.tarifa = tarifa;
    }

    public boolean isEnCurso() {
        return enCurso;
    }
    public void setEnCurso(boolean enCurso) {
        this.enCurso = enCurso;
    }

    public String getObservacion() {
        return observacion;
    }
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }



    @Override
    public String toString(){
        return
                "Id: " + idReserva + ". Socio: " + socio.getNombre() + " "
                + socio.getApaterno() + ". En " + cancha.getNombre()
                + ". Reserva realizada: " + fechaReserva + ". Para "
                + fecha + ", turno " + turno.getHoraInicio() +
                " hasta " + turno.getHoraFin() + ". " +
                (prepago ? "Pago anticipado: $" + senia : "Seña: $" + senia) +
                (enCurso ? ". Socio ingresó" : ". Socio no ingresó") +
                (observacion.isEmpty() ? ". Sin observaciones." : ". " + observacion);
    }

    public String resumen(){
        return  "Id: " + this.getIdReserva() + " En "
                + this.getCancha().getNombre() + " para "
                + this.getFecha() + " " + this.getTurno().getHoraInicio()
                + " hs, socio " + this.getSocio().getNombre() + " "
                + this.getSocio().getApaterno() + ". " +
                (this.isPrepago() ? "Prepago: $" : "Seña: $") + this.senia;
    }
}
