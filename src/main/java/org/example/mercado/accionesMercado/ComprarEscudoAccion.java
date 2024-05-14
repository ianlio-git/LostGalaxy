package org.example.mercado.accionesMercado;

import org.example.Jugador;

public class ComprarEscudoAccion implements AccionMercado {
    @Override
    public void realizarAccion(Jugador jugador) {
        System.out.println("Comprar Escudo");
    }
}
