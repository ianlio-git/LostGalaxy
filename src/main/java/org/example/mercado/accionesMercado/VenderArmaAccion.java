package org.example.mercado.accionesMercado;

import org.example.Jugador;

public class VenderArmaAccion implements AccionMercado {

    @Override
    public void realizarAccion(Jugador jugador) {
        System.out.println("Vender Arma");
    }
}
