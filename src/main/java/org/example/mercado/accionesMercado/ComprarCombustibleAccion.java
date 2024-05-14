package org.example.mercado.accionesMercado;

import org.example.Jugador;

import java.util.Scanner;

public class ComprarCombustibleAccion implements AccionMercado {

    @Override
    public void realizarAccion(Jugador jugador) {
        System.out.println("¡Comprar Combustible!");
        if(jugador.getUadeCoins()>jugador.getNave().getCombustible()){
            jugador.getNave().llenarTanqueDeCombustible(100-jugador.getNave().getCombustible());
            System.out.println("¡Has agregado " + cantidadDeEscudo + " al escudo de la nave!");
            jugador.quitarUadeCoins(cantidadDeEscudo);
        }
        else{
            System.out.println("No tenes suficientes uadeCoins.");
        }
    }
}
