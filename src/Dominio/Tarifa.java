package Dominio;
import java.time.LocalDate;

public class Tarifa {
    private int id;
    private Turno turno;
    private Cancha cancha;
    private boolean tarifaEspecial;
    private double costo;
    private LocalDate vigencia;
    private boolean vigente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }

    public boolean isTarifaEspecial() {
        return tarifaEspecial;
    }

    public void setTarifaEspecial(boolean tarifaEspecial) {
        this.tarifaEspecial = tarifaEspecial;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public LocalDate getVigencia() {
        return vigencia;
    }
    public void setVigencia(LocalDate vigencia) {
        this.vigencia = vigencia;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }


    public Tarifa(int id, Turno turno, Cancha cancha, boolean tarifaEspecial, double costo, LocalDate vigencia, boolean vigente){
        this.id = id;
        this.turno = turno;
        this.cancha = cancha;
        this.tarifaEspecial = tarifaEspecial;
        this.costo = costo;
        this.vigencia = vigencia;
        this.vigente = vigente;
    }

    @Override
    public String toString()
    {
        return "Tarifa para " + this.turno.getDeporte() +
                " en " + this.cancha.getNombre() + " de "
                + this.turno.getHoraInicio() + " hasta "
                + this.turno.getHoraFin() +
                (tarifaEspecial ? ". Tarifa especial (feriados)" : ". Tarifa estandar")
                + ". Costo: $" + this.costo +
                (vigente ? ". Vigente desde: " + this.vigencia : ". No vigente")
                + ".";
    }
}
