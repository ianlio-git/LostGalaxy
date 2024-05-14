package org.example.planeta;

import org.example.mercado.Mercado;

class Neutral extends Planeta {
    private static int count;
    private Mercado mercado;

    public Neutral() {
        super("NEU-"+ count++);
        this.mercado = new Mercado() ;
    }

    @Override
    public boolean soyPlanetaTipo() {
        return true;
    }

    public Mercado ingresarAlMercado() {
        return mercado;
    }
}