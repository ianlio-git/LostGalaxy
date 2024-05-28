package org.example.mercado.accionesMercado;

import org.example.Jugador;

import javax.xml.transform.Source;
import java.sql.SQLOutput;

public class ComprarCombustibleAccion implements AccionMercado {

    @Override
    public void realizar(Jugador jugador) {
        System.out.println("¡Bienvenido a YPF!");

        if(jugador.getNave().getCombustible()== 100){
            System.out.println("Combustible lleno");
        }
        else {
            double combustibleFaltante = 100 - jugador.getNave().getCombustible();
            if (jugador.getUadeCoins() > jugador.getNave().getCombustible()) {
                jugador.getNave().llenarTanqueDeCombustible(combustibleFaltante);
                System.out.println("¡Has agregado " + (combustibleFaltante) + " de combustible a la nave!");
                jugador.quitarUadeCoins(combustibleFaltante);
            } else {
                System.out.println("No tenes suficientes uadeCoins.");
            }
        }
    }
}
