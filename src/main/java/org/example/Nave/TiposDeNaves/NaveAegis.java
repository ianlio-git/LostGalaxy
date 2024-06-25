package org.example.Nave.TiposDeNaves;

import org.example.Enums.TipoDeNave;
import org.example.Nave.NaveAliada;
import org.example.Nave.PartesDeLaNave.Arma;

public class NaveAegis extends NaveAliada {

    public NaveAegis() {
        super(5, 100, 200,0);
    }

    @Override
    public double poderAtaque() {
        double danio = 0;
        if(this.armas.size() == 0){
            return danio;
        }
        else{
            for (Arma arma : this.armas){
                danio+=arma.getPoder();
            }
            return ((danio*this.getVida())/this.getVelocidad());
        }
    }

    @Override
    public TipoDeNave soyNaveTipo() {
        return TipoDeNave.NAVE_AEGIS;
    }
}
