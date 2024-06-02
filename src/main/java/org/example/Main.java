package org.example;

import org.example.Controller.Controller;
import org.example.enums.Acciones;
import org.example.enums.Dificultades;
import org.example.enums.TipoDeNave;
import org.example.enums.TipoDePlaneta;
import org.example.gameMaster.Juego;
import org.example.mapaEstelar.sistemas.SistemaEstelar;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Juego juego = Juego.getInstancia();
        Controller.gameBegin("Bruno Fernandes", 10000.0, TipoDeNave.NAVE_TITAN, 4, Dificultades.FACIL);
        Controller.mostrarSistemas();


        Controller.mostrarDetalleDelJugador();
        juego.siguienteTurno(Acciones.COMPRAR_ARMA, "SIST-0",1);
        Controller.mostrarDetalleDelJugador();
        Controller.mostrarTurno();
        juego.siguienteTurno(Acciones.COMPRAR_COMBUSTIBLE, "SIST-0",100);

        Controller.mostrarDetalleDelJugador();
        Controller.mostrarTurno();
        juego.siguienteTurno(Acciones.COMPRAR_ESCUDO, "SIST-0",150);

        Controller.mostrarDetalleDelJugador();
        Controller.mostrarTurno();
        juego.siguienteTurno(Acciones.BUSCAR_TESORO, "SIST-0",0);
        Controller.mostrarDetalleDelJugador();
        Controller.mostrarTurno();
    }

    public void comprarArma (Juego juego) {
        System.out.println("Bienvenido a la tienda de armas");
        System.out.println("1. CAÑON_DE_IONES");
        System.out.println("2. LASER_DE_FUSION");
        System.out.println("3. MISIL_DE_ANTIMATERIA");
        System.out.println("4. CAÑON_DE_PARTICULAS");
        System.out.println("5. CAÑON_GAUSS");
        juego.siguienteTurno(Acciones.COMPRAR_ARMA, "SIST-2",1);
    }
}