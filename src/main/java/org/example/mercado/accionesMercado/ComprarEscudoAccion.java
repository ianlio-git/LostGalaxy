package org.example.mercado.accionesMercado;

import org.example.gameMaster.Jugador;

import java.util.Scanner;

public class ComprarEscudoAccion implements AccionMercado {
    @Override
    public void realizar(Jugador jugador) {
        System.out.println("¡Bienvenido a la tienda de escudo!");
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de escudo a agregar:");

        double cantidadDeEscudo = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            if (sc.hasNextDouble()) {
                cantidadDeEscudo = sc.nextDouble();
                entradaValida = true;
            } else {
                System.out.println("Entrada no válida. Por favor, introduce un número válido.");
                sc.next();
            }
        }

        if (jugador.getUadeCoins() > cantidadDeEscudo) {
            jugador.getNave().agregarEscudo(cantidadDeEscudo);
            System.out.println("¡Has comprado " + cantidadDeEscudo + " de escudo para tu nave!");
            jugador.quitarUadeCoins(cantidadDeEscudo);
        } else {
            System.out.println("No tienes suficientes uadeCoins.");
        }
    }
}


