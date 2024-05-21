package org.example.mercado.accionesMercado;

import org.example.Jugador;
import org.example.nave.NaveAliada;
import org.example.nave.tiposDeNaves.NaveAegis;
import org.example.nave.tiposDeNaves.NaveSwift;

import java.util.Scanner;

public class ComprarEscudoAccion implements AccionMercado {
    @Override
    public void realizar(Jugador jugador) {
        System.out.println("¡Bienvenido a la tienda de escudo!");
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de escudo a agregar:");
        double cantidadDeEscudo = sc.nextDouble();
        if(jugador.getUadeCoins()>cantidadDeEscudo){
            jugador.getNave().agregarEscudo(cantidadDeEscudo);
            System.out.println("¡Has comprado " + cantidadDeEscudo + " de escudo para tu nave");
            jugador.quitarUadeCoins(cantidadDeEscudo);
        }
        else{
            System.out.println("No tenes suficientes uadeCoins.");
        }

    }
}

