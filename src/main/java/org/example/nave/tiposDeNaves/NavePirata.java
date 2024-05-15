package org.example.nave.tiposDeNaves;

import org.example.nave.Nave;

import java.util.Random;

public class NavePirata extends Nave {

    private double poderDeAtaque = 0;
    public NavePirata() {
        super(100, 100);
    }

    @Override
    public double poderAtaque() {
        if (poderDeAtaque == 0) {
            Random rand = new Random();
            poderDeAtaque = rand.nextInt(101) + 50;
        }
        return poderDeAtaque;
    }
}