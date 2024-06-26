package org.example._MVC.Model.Nave.TiposDeNaves;

import org.example._MVC.Model.Enums.TipoDeNave;
import org.example._MVC.Model.Nave.NaveAliada;
import org.example._MVC.Model.Nave.PartesDeLaNave.Arma;

public class NaveSwift extends NaveAliada {

    public NaveSwift() {
        super(20, 50, 500, 0);

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