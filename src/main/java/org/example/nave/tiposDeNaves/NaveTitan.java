package org.example.nave.tiposDeNaves;

import org.example.enums.TipoDeNave;
import org.example.nave.NaveAliada;
import org.example.nave.partesDeLaNave.Arma;

public class NaveTitan extends NaveAliada {
    public NaveTitan() {
        super(100, 100, 400, 0);
    }

    @Override
    public double poderAtaque() {
        double danio = 0;
        if(this.armas.size() == 0){
            return danio;
        }
        else{
            for (Arma arma : this.armas){
                danio+= ((arma.getPoder() * this.getVelocidad()) + this.getVida() + this.getTanque().getCombustible());
            }
        }
        return danio;
    }

    @Override
    public TipoDeNave soyNaveTipo() {
        return TipoDeNave.NAVE_TITAN;
    }
}