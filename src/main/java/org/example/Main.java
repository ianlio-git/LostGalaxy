package org.example;

import org.example.enums.Acciones;
import org.example.enums.Dificultades;
import org.example.enums.TipoDeNave;
import org.example.enums.TipoDePlaneta;
import org.example.gameMaster.Juego;
import org.example.mapaEstelar.sistemas.SistemaEstelar;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Juego juego = Juego.getInstancia();
        juego.iniciarJuego("Bruno Fernandes", 10000.0, TipoDeNave.NAVE_AEGIS, 1, Dificultades.FACIL);
        mostrarSistemas(juego);

        juego.siguienteTurno(Acciones.COMPRAR_ARMA, "SIST-0",1);
        juego.siguienteTurno(Acciones.COMPRAR_COMBUSTIBLE, "SIST-0",150);
        juego.siguienteTurno(Acciones.COMPRAR_ESCUDO, "SIST-0",150);
        juego.siguienteTurno(Acciones.RECARGAR_ESCUDO, "SIST-0",150);
        juego.siguienteTurno(Acciones.COMPRAR_ARMA, "SIST-0",2);
        juego.siguienteTurno(Acciones.OBTENER_INFORMACION, "SIST-0", 0);
        juego.siguienteTurno(Acciones.BUSCAR_TESORO, "SIST-0",0);
        juego.siguienteTurno(Acciones.BUSCAR_TESORO, "SIST-0",0);
        juego.siguienteTurno(Acciones.REPARAR_NAVE, "SIST-0",0);
        juego.siguienteTurno(Acciones.BUSCAR_TESORO, "SIST-0",0);
        juego.siguienteTurno(Acciones.BUSCAR_TESORO,"SIST-0",0);
        juego.siguienteTurno(Acciones.BUSCAR_TESORO,"SIST-0",0);
    }
    public static  void mostrarSistemas(Juego juego){
        juego.mostrarSistemas();
        System.out.println("Listado de planetas:");
        juego.mostrarPlanetasEnSistema("SIST-0");
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