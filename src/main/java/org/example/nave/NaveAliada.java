package org.example.nave;

import org.example.partesDeLaNave.Arma;

public abstract class NaveAliada extends Nave {
    private double combustible;
    private Arma arma;
    private double escudo = 0;
    private double recompensa = 0;

    public NaveAliada(double velocidad, double vida, double combustible, Arma arma, double escudo) {
        super(velocidad, vida);
        this.combustible = combustible;
        this.arma = arma;
        this.escudo = escudo;
        this.recompensa=recompensa;
    }

    public void agregarEscudo(double cantDeEscudo){
        this.escudo+=cantDeEscudo;
    }

    public double getEscudo() {
        return escudo;
    }
    public void cambiarArma(Arma nuevaArma) {
        this.arma=nuevaArma;
    }

    public Arma getArma() {
        return arma;
    }

    public void llenarTanqueDeCombustible(double combustible) {
        this.combustible += combustible;
    }

    public double getCombustible() {
        return combustible;
    }

    public void setRecompensa(double cantDeRecompenza){
        this.recompensa = cantDeRecompenza;
    }

    public double getRecompensa(){
        return recompensa;
    }
}