package org.example;

import org.example.Controller.Controller;
import org.example.Enums.Dificultades;
import org.example.Enums.TipoDeArma;
import org.example.Enums.TipoDeNave;
import org.example.GameMaster.Exception.*;
import org.example.GameMaster.Juego;
import org.example.MapaEstelar.MapaEstelar;

public class Main {
    public static void main(String[] args) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {

        Controller.gameBegin("Bruno Fernandes", 10000.0, TipoDeNave.NAVE_PHANTOM, 4, Dificultades.FACIL);
        Controller.mostrarSistemas();
        //hola
        Controller.mostrarTurno();
        Controller.repararNave();
        Controller.infoUpdate();
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.localizarTesoro();
        Controller.infoUpdate();
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.localizarTesoro();
        Controller.infoUpdate();
        Controller.mostrarDetalleDelJugador();

        Controller.mostrarTurno();
        Controller.localizarTesoro();
        Controller.infoUpdate();
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