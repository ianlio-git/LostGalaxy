package org.example;

import org.example.nave.Nave;
import org.example.nave.TiposDeNaves;
import org.example.planeta.Planeta;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private static Juego instanciaJuego;
    private List<Planeta> planetas;
    private int turno;
    private Jugador jugador;

    private Juego() {
        this.planetas = new ArrayList<>();
    }
    public static Juego getInstancia() {
        if (instanciaJuego == null) {
            instanciaJuego = new Juego();
        }
        return instanciaJuego;
    }
    public void crearJugador(String ingresarNombre, double ingresarUadeCoins, TiposDeNaves elegirNave){
        if (jugador == null) {
            this.jugador = new Jugador(ingresarNombre, ingresarUadeCoins,elegirNave);
        } else {
            System.out.println("Ya hay un jugador creado en el juego.");
        }
    }


    public void siguienteTurno() {
        // Implementación
    }

    public void FinJuego(Planeta planeta, Jugador jugador) {
        // Implementación
    }

    public Planeta crearPlanetaNeutral() {
        // Implementación
        return null;
    }

    public Planeta crearPlanetaHostil() {
        // Implementación
        return null;
    }

    public Planeta getPlanetaNeutral() {
        // Implementación
        return null;
    }

    public Planeta getPlanetaHostil() {
        // Implementación
        return null;
    }
}