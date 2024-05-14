package org.example.nave;

public abstract class Nave {
    private double vida;
    private double velocidad;

    public Nave(double velocidad, double vida) {
        this.velocidad = velocidad;
        this.vida = vida;
    }

    public abstract double poderAtaque();

    public void setVida(double vida) {
        this.vida = vida;
    }

    public double getVida() {
        return vida;
    }


}