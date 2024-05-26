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
        this.escudo += cantDeEscudo;

    }
    public void quitarEscudo(double cantDeEscudo) {
        this.escudo -= cantDeEscudo;
        if (this.escudo < 0) {
            this.quitarVida(this.escudo);
            this.escudo = 0;
        }
    }

    public double getEscudo() {
        return escudo;
    }

    public boolean tengoEscudo() {
        return this.escudo>0;
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


}