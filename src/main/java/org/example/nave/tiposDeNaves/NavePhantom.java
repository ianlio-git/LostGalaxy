package org.example.nave.tiposDeNaves;

import org.example.nave.NaveAliada;
import org.example.partesDeLaNave.Arma;

public class NavePhantom extends NaveAliada {
    public NavePhantom() {
        super(100, 100, 10, null, 0);
    }

    @Override
    public double poderAtaque() {
        return 0;
    }
}
