package org.example.nave;

import org.example.partesDeLaNave.Arma;

public abstract class NaveAliada extends Nave {
    private double combustible;
    private Arma arma;
    private double escudo;

    public void setEscudo(double escudo) {
        this.escudo = escudo;
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

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }

    public double getCombustible() {
        return combustible;
    }

    public abstract double calcularDaño();
}