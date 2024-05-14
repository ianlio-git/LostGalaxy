package org.example;

import org.example.nave.Nave;
import org.example.planeta.Planeta;

public class Jugador {
    private Nave nave;
    private String nombre;
    private double uadeCoins;

    public Jugador(String nombre, double uadeCoins,Nave nave) {
        this.nombre = nombre;
        this.uadeCoins = uadeCoins;
    }

    public double getUadeCoins() {
        return uadeCoins;
    }

    public void setUadeCoins(double uadeCoins) {
        this.uadeCoins = uadeCoins;
    }


    public Nave getNave() {
        return nave;
    }
}

