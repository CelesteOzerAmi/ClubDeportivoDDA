package Dominio;

public class Extras {

    private String concepto;
    private double costo;

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Extras(String concepto, double costo ){
        this.concepto = concepto;
        this.costo = costo;
    }

    @Override
    public String toString(){
        return this.concepto + ", $" + this.costo + ".";
    }


}
