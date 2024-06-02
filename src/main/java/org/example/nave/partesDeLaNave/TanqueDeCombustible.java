package org.example.nave.partesDeLaNave;


import org.example.enums.TipoDePlaneta;

public class TanqueDeCombustible {
    private double combustible;
    private double capacidadMaxTanque;

    public TanqueDeCombustible(double capacidadMaxTanque) {
        this.combustible = 15;
        this.capacidadMaxTanque = capacidadMaxTanque;
    }
    public double cargarCombustible(double cantidad){
        if(cantidad + this.combustible <= this.capacidadMaxTanque){
            this.combustible+=cantidad;
            return cantidad;
        }else{
            double faltante = this.capacidadMaxTanque -this.combustible;
            this.combustible+= faltante;
            return faltante;
        }
    }
    public void consumirCombustible(double consumir){
       if(consumir>combustible) {
           combustible=0;
       }
       else {
           this.combustible -= consumir;
       }

    }
    public  boolean tanqueLleno(){
        return  this.combustible == this.capacidadMaxTanque;
    }

    public double getCombustible() {
        return combustible;
    }

    public double combustibleNecesario(TipoDePlaneta planeta, double cantidadDeArmas){
        double tasaDeAumento = Math.pow(1.5, cantidadDeArmas);
        switch (planeta) {
            case NEUTRAL:
                return (10*tasaDeAumento);
            case ALIADO:
                return (15*tasaDeAumento);
            case HOSTIL:
                return (20*tasaDeAumento);
            case CINTURON_ASTEROIDE:
                return (30*tasaDeAumento);
            default:
                return 0;
        }
    }
    public boolean tengoCombustible(){
        return combustible!=0;
    }

}
