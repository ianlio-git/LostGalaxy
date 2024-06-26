package org.example._MVC.Model.Nave;

import org.example._MVC.Model.GameMaster.Exception.NaveDestruidaException;

public abstract class Nave {
    protected double vida;
    protected double velocidad;
    private double vidaMaxima;

    public Nave(double velocidad, double vida) {
        this.velocidad = velocidad;
        this.vida = vida;
        this.vidaMaxima = vida;
    }

    public abstract double poderAtaque();

    public void quitarVida(double vida) {
        this.vida -= vida;
    }

    public double getVida() {
        return vida;
    }

    public double getVelocidad() {
        return velocidad;
    }
    public abstract boolean tengoVida() throws NaveDestruidaException;
    public void reestablecerVida(){
        this.vida=this.vidaMaxima;
    }
}