package org.example.nave.tiposDeNaves;

import org.example.nave.NaveAliada;
import org.example.partesDeLaNave.Arma;

public class NaveSwift extends NaveAliada {

    public NaveSwift() {
        super(100, 100, 10, null, 0);
    }

    @Override
    public double poderAtaque() {
        return 0;
    }

    public double calcularDaño() {
        // Implementación
        return 0;
    }
}