package org.example.nave.partesDeLaNave;


import org.example.enums.TipoDePlaneta;
import org.example.mapaEstelar.sistemas.planetas.Planeta;

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
    public void cosumirCombustible(double consumir){
       this.combustible-=consumir;
    }
    public  boolean tanqueLleno(){
        return  this.combustible == this.capacidadMaxTanque;
    }

    public double getCombustible() {
        return combustible;
    }

    public double combustibleNecesario(TipoDePlaneta planeta){
        switch (planeta) {
            case NEUTRAL:
                return 10;
            case ALIADO:
                return 15;
            case HOSTIL:
                return 20;
            default:
                return 0;
        }
    }

}
