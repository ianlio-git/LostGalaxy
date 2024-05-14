package org.example.nave.tiposDeNaves;

import org.example.nave.Nave;

public class NavePirata extends Nave {

    public NavePirata() {
        super(100, 100);
    }

    public double calcularRecompensa() {
        // Implementación
        return 0;
    }

    @Override
    public double poderAtaque() {
        return 0;
    }
}