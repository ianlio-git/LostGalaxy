package org.example.nave;

public abstract class Nave {
    protected double vida;
    protected double velocidad;

    public Nave(double velocidad, double vida) {
        this.velocidad = velocidad;
        this.vida = vida;
    }

    public abstract double poderAtaque();

    public void agregarVida(double vida) {
        this.vida += vida;
    }
    public void quitarVida(double vida) {
        this.vida -= vida;
    }

    public double getVida() {
        return vida;
    }

    public double getVelocidad() {
        return velocidad;
    }
    public boolean tengoVida(){
        return this.vida > 0 ;
    }
}