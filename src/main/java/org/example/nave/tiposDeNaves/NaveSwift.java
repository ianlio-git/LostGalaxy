package org.example.nave.tiposDeNaves;

import org.example.enums.TipoDeNave;
import org.example.nave.NaveAliada;
import org.example.partesDeLaNave.Arma;

public class NaveSwift extends NaveAliada {

    public NaveSwift() {
        super(20, 50, 100, 0);

    }

    @Override
    public double poderAtaque() {
        double danio = 0;
        if(this.armas.size() == 0){
            return danio;
        }
        else{
            for (Arma arma : this.armas){
                danio+= arma.getPoder();
            }
        }
        return (danio * this.getVelocidad()) + this.getVida() + this.getTanque().getCombustible();
    }

    @Override
    public TipoDeNave soyNaveTipo() {
        return TipoDeNave.NAVE_SWIFT;
    }
}