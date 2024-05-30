package org.example.nave.partesDeLaNave;

import org.example.mapaEstelar.sistemas.planetas.Planeta;

public class TanqueDeCombustible {
    private double combustible;
    private double capacidadMaxTanque;

    public TanqueDeCombustible(double capacidadMaxTanque) {
        this.combustible = 10;
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
}
