package org.example;

import org.example.enums.Acciones;
import org.example.enums.Dificultades;
import org.example.enums.TipoDeNave;
import org.example.enums.TipoDePlaneta;

public class Main {
    public static void main(String[] args) {
        Juego juego = Juego.getInstancia();
        juego.iniciarJuego("Bruno Fernandes", 1000.0, TipoDeNave.NAVE_SWIFT, 20, Dificultades.FACIL);

        System.out.println("Listado de planetas neutrales:");
        juego.mostarListadoPlanetas(TipoDePlaneta.NEUTRAL);

        System.out.println("Listado de planetas hostiles:");
        juego.mostarListadoPlanetas(TipoDePlaneta.HOSTIL);

        juego.siguienteTurno(Acciones.COMPRAR_ARMA, "NEU-1");
        juego.siguienteTurno(Acciones.COMPRAR_COMBUSTIBLE, "NEU-1");
        juego.siguienteTurno(Acciones.COMPRAR_ESCUDO, "NEU-1");
        //juego.siguienteTurno(Acciones.COMPRAR_ARMA, "NEU-1");
        juego.siguienteTurno(Acciones.BUSCAR_TESORO, "ENE-0");
        juego.siguienteTurno(Acciones.BUSCAR_TESORO, "ENE-1");
        juego.siguienteTurno(Acciones.BUSCAR_TESORO, "ENE-2");
        juego.siguienteTurno(Acciones.BUSCAR_TESORO,"ENE-3");
        juego.siguienteTurno(Acciones.BUSCAR_TESORO,"ENE-4");
    }

}