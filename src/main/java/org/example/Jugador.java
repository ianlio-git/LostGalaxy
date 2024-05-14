package org.example;

import org.example.nave.Nave;

public class Jugador {
    private Nave nave;
    private String nombre;
    private double uadeCoins;

    public Jugador(String nombre, double uadeCoins,Nave nave) {
        this.nombre = nombre;
        this.uadeCoins = uadeCoins;
    }

    public void visitarPlanetaNeutral(Planeta planeta) {
        // Implementación
    }

    public void visitarPlanetaHostil(Planeta planeta) {
        // Implementación
    }

    public double getUadeCoins() {
        return uadeCoins;
    }

    public void setUadeCoins(double uadeCoins) {
        this.uadeCoins = uadeCoins;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public Nave getNave() {
        return nave;
    }
}

