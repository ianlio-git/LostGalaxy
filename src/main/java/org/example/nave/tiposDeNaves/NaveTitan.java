package org.example.nave.tiposDeNaves;

import org.example.nave.NaveAliada;

public class NaveTitan extends NaveAliada {
    public NaveTitan() {
        super(100, 100, 10, null, 0);
    }

    @Override
    public double poderAtaque() {
        return 0;
    }
}