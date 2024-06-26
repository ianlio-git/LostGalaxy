package org.example._MVC.Model.Nave.TiposDeNaves;

import org.example._MVC.Model.Enums.TipoDeNave;
import org.example._MVC.Model.Nave.NaveAliada;
import org.example._MVC.Model.Nave.PartesDeLaNave.Arma;

public class NaveTitan extends NaveAliada {
    public NaveTitan() {
        super(100, 2500, 2000, 0);
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