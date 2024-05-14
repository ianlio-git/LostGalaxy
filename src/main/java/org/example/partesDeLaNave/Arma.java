package org.example.partesDeLaNave;

import org.example.enums.TipoDeArma;
import org.example.mercado.Item;

public class Arma extends Item {
    private TipoDeArma tipo;
    private double poder;

    public double getPoder() {
        return poder;
    }
}