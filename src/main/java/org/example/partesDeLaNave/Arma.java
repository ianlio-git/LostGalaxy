package org.example.partesDeLaNave;

import org.example.enums.TipoDeArma;

public class Arma extends Item {

    private TipoDeArma tipo;
    private double poder;

    public Arma(TipoDeArma tipo, double poder) {
        this.tipo = tipo;
        this.poder = poder;
    }

    public double getPoder() {

        return poder;
    }
    double getPoderDeAtaque();
    double getPrecio();
}