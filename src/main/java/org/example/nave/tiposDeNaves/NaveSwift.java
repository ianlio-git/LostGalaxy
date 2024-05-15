package org.example.nave.tiposDeNaves;

import org.example.nave.NaveAliada;

public class NaveSwift extends NaveAliada {

    public NaveSwift() {
        super(100, 100, 10, null, 0);
    }

    @Override
    public double poderAtaque() {
        return ((this.getArma().getPoder()*this.getVelocidad())+this.getVida()+this.getCombustible());
    }

}