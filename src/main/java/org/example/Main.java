package org.example;

import org.example.Controller.Controller;
import org.example.Enums.Acciones;
import org.example.Enums.Dificultades;
import org.example.Enums.TipoDeNave;
import org.example.GameMaster.Juego;

public class Main {
    public static void main(String[] args) {
        Juego juego = Juego.getInstancia();
        Controller.gameBegin("Bruno Fernandes", 10000.0, TipoDeNave.NAVE_AEGIS, 4, Dificultades.FACIL);
        Controller.mostrarSistemas();

        juego.realizarAccionDeCompra(Acciones.COMPRAR_ARMA,"SIST-0",1);
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();


        juego.realizarAccionDeCompra(Acciones.COMPRAR_COMBUSTIBLE,"SIST-0",100);
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();


        juego.realizarAccionDeCompra(Acciones.COMPRAR_ESCUDO,"SIST-0",150);
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();


        juego.atacarPlanetaHostil("SIST-0");
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

    }

    public void comprarArma (Juego juego) {
        System.out.println("Bienvenido a la tienda de armas");
        System.out.println("1. CAÑON_DE_IONES");
        System.out.println("2. LASER_DE_FUSION");
        System.out.println("3. MISIL_DE_ANTIMATERIA");
        System.out.println("4. CAÑON_DE_PARTICULAS");
        System.out.println("5. CAÑON_GAUSS");
    }
}