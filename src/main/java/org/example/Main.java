package org.example;

import org.example.Controller.Controller;
import org.example.Enums.Dificultades;
import org.example.Enums.TipoDeArma;
import org.example.Enums.TipoDeNave;
public class Main {
    public static void main(String[] args) {

        Controller.gameBegin("Bruno Fernandes", 10000.0, TipoDeNave.NAVE_TITAN, 4, Dificultades.FACIL);
        Controller.mostrarSistemas();

        Controller.mostrarTurno();
        Controller.comprarArma(TipoDeArma.MISIL_DE_ANTIMATERIA);
        Controller.mostrarDetalleDelJugador();
//
//        Controller.mostrarTurno();
//        Controller.comprarArma(TipoDeArma.MISIL_DE_ANTIMATERIA);
//        Controller.mostrarDetalleDelJugador();
//
        Controller.mostrarTurno();
        Controller.comprarEscudoMaximo(200);
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.recargarEscudo(200);
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.repararNave();
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.localizarTesoro();
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.localizarTesoro();
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.localizarTesoro();
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.atacarPlanetaHostil("ENE-1");
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.atacarPlanetaHostil("ENE-0");
        Controller.mostrarDetalleDelJugador();


        Controller.mostrarTurno();
        Controller.cambiarDeSistema("SIST-1");
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.atacarPlanetaHostil("ENE-2");
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.atacarPlanetaHostil("ENE-3");
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.cambiarDeSistema("SIST-2");
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.atacarPlanetaHostil("ENE-4");
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.atacarPlanetaHostil("ENE-5");
        Controller.mostrarDetalleDelJugador();

    }


//    public void comprarArma (Juego juego) {
//        System.out.println("Bienvenido a la tienda de armas");
//        System.out.println("1. CAÑON_DE_IONES");
//        System.out.println("2. LASER_DE_FUSION");
//        System.out.println("3. MISIL_DE_ANTIMATERIA");
//        System.out.println("4. CAÑON_DE_PARTICULAS");
//        System.out.println("5. CAÑON_GAUSS");
//    }
}