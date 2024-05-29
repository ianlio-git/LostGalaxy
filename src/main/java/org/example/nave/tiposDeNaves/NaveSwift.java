package org.example.nave.tiposDeNaves;

import org.example.nave.NaveAliada;
import org.example.partesDeLaNave.Arma;

public class NaveSwift extends NaveAliada {

    public NaveSwift() {
        super(100, 100, 10, 0);

    }

    @Override
    public double poderAtaque() {
        double danio = 0;
        if(this.armas.size() == 0){
            return danio;
        }
        else{
            for (Arma arma : this.armas){
                danio+= ((arma.getPoder() * this.getVelocidad()) + this.getVida() + this.getCombustible());
            }
        }
        return danio;
    }
}