package org.example;

import org.example.enums.Acciones;
import org.example.enums.Dificultades;
import org.example.enums.TipoDeNave;
import org.example.enums.TipoDePlaneta;
import org.example.gameMaster.Juego;
import org.example.mapaEstelar.sistemas.SistemaEstelar;

public class Main {
    public static void main(String[] args) {
        Juego juego = Juego.getInstancia();
        juego.iniciarJuego("Bruno Fernandes", 1000.0, TipoDeNave.NAVE_SWIFT, 4, Dificultades.FACIL);
        juego.mostrarSistemas();
        System.out.println("Listado de planetas:");
        juego.mostrarPlanetasEnSistema("SIST-2");

        juego.siguienteTurno(Acciones.COMPRAR_ARMA, "SIST-2",2);
        juego.siguienteTurno(Acciones.COMPRAR_COMBUSTIBLE, "SIST-2",150);
        juego.siguienteTurno(Acciones.COMPRAR_ESCUDO, "SIST-2",150);
        juego.siguienteTurno(Acciones.COMPRAR_ARMA, "SIST-2",2);
       juego.siguienteTurno(Acciones.BUSCAR_TESORO, "SIST-2",0);
        juego.siguienteTurno(Acciones.BUSCAR_TESORO, "SIST-2",0);
        juego.siguienteTurno(Acciones.BUSCAR_TESORO, "SIST-2",0);
       juego.siguienteTurno(Acciones.BUSCAR_TESORO,"SIST-2",0);
        juego.siguienteTurno(Acciones.BUSCAR_TESORO,"SIST-2",0);
    }

}