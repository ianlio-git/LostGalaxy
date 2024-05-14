package org.example.nave;

import org.example.partesDeLaNave.Arma;

public abstract class NaveAliada extends Nave {
    private double combustible;
    private Arma arma;
    private double escudo = 0;

    public NaveAliada(double velocidad, double vida, double combustible, Arma arma, double escudo) {
        super(velocidad, vida);
        this.combustible = combustible;
        this.arma = arma;
        this.escudo = escudo;
    }

    public void agregarEscudo(double cantDeEscudo){
        this.escudo+=cantDeEscudo;
    }

    public double getEscudo() {
        return escudo;
    }

    public Arma removeArma() {
        return arma;
    }

    public Arma ponerArma() {
        return arma;
    }

    public void llenarTanqueDeCombustible(double combustible) {
        this.combustible += combustible;
    }

    public double getCombustible() {
        return combustible;
    }

    public abstract double calcularDaño();
}