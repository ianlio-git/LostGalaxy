package org.example.nave.tiposDeNaves;

import org.example.nave.NaveAliada;

public class NaveSwift extends NaveAliada {

    public NaveSwift() {
        super(100, 100, 10, null, 0);
    }

    @Override
    public double poderAtaque() {
        if (this.getArma() == null) {
            return 0;
        } else {
            return ((this.getArma().getPoder() * this.getVelocidad()) + this.getVida() + this.getCombustible());

        }
    }
}