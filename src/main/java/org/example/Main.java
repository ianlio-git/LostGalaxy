package org.example;

import org.example.Controller.Controller;
import org.example.Enums.Dificultades;
import org.example.Enums.TipoDeArma;
import org.example.Enums.TipoDeNave;
import org.example.GameMaster.Exception.*;
import org.example.GameMaster.Juego;

public class Main {
    public static void main(String[] args) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {

        Controller.gameBegin("Bruno Fernandes", 10000.0, TipoDeNave.NAVE_TITAN, 4, Dificultades.FACIL);
        Controller.mostrarSistemas();

        Controller.comprarArma(TipoDeArma.CAÑON_DE_IONES);
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();


        Controller.comprarCombustible(400);
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.cambiarDeSistema("SIST-1");
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.cambiarDeSistema("SIST-0");
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.cambiarDeSistema("SIST-1");
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.cambiarDeSistema("SIST-2");
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.cambiarDeSistema("SIST-3");
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.comprarArma(TipoDeArma.CAÑON_DE_PARTICULAS);
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.comprarEscudoMaximo(150);
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.venderArma(TipoDeArma.LASER_DE_FUSION);
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.recargarEscudo(150);
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.repararNave();
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.localizarTesoro();
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.atacarPlanetaHostil("ENE-0");
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.atacarPlanetaHostil("ENE-1");
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.atacarPlanetaHostil("ENE-2");
        Controller.mostrarTurno();
        Controller.mostrarDetalleDelJugador();

        Controller.atacarPlanetaHostil("ENE-3");
        Controller.mostrarTurno();
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