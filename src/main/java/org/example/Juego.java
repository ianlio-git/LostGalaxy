package org.example;


import org.example.enums.Acciones;
import org.example.enums.Dificultades;
import org.example.enums.TipoDePlaneta;
import org.example.nave.tiposDeNaves.NaveAegis;
import org.example.nave.tiposDeNaves.NavePirata;
import org.example.nave.tiposDeNaves.NaveSwift;
import org.example.enums.TipoDeNave;
import org.example.planeta.Hostil;
import org.example.planeta.Neutral;
import org.example.planeta.Planeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Juego {
    private static Juego instanciaJuego;
    private List<Planeta> planetas;
    private static int turno;
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
    public void iniciarJuego(String nombreDelJugador, double uadeCoinsJugador, TipoDeNave naveJugador, int tamanioDeLaGalaxia, Dificultades dificultad){
        if (jugador == null) {
            if(naveJugador == TipoDeNave.NAVE_AEGIS){
                NaveAegis naveAegis = new NaveAegis();
                this.jugador = new Jugador(nombreDelJugador,uadeCoinsJugador,naveAegis);
            }else{
                NaveSwift naveAegis = new NaveSwift();
                this.jugador = new Jugador(nombreDelJugador,uadeCoinsJugador,naveAegis);
            }
            crearPlanetas(tamanioDeLaGalaxia,dificultad);
        } else {
            throw new RuntimeException("El juego ya fue iniciado");
        }
    }

    public void FinJuego(Planeta planeta, Jugador jugador) {
        // Implementación
    }

    private void crearPlanetas(int tamanioDeLaGalaxia,Dificultades dificultad) {
        Random rand = new Random();
        int cantPlanetasNeutrales = 0;
        int cantPlanetasHostiles = 0;
        switch (dificultad) {
            case FACIL:
                cantPlanetasNeutrales = (int) (tamanioDeLaGalaxia * 0.7);
                cantPlanetasHostiles = (int) (tamanioDeLaGalaxia * 0.3);
                break;
            case MEDIO:
                cantPlanetasNeutrales = (int) (tamanioDeLaGalaxia * 0.5);
                cantPlanetasHostiles = (int) (tamanioDeLaGalaxia * 0.5);
                break;
            case DIFICIL:
                cantPlanetasNeutrales = (int) (tamanioDeLaGalaxia * 0.3);
                cantPlanetasHostiles = (int) (tamanioDeLaGalaxia * 0.7);
                break;
        }
        int indicePlanetaConTesoro = rand.nextInt(cantPlanetasHostiles);
        for (int i = 0; i < cantPlanetasNeutrales; i++) {
            Neutral planetaNeutral = new Neutral();
            planetas.add(planetaNeutral);
        }
        for (int i = 0; i < cantPlanetasHostiles; i++) {
            boolean tieneTesoro = (i == indicePlanetaConTesoro);
            NavePirata navePirata = new NavePirata();
            Hostil planetaHostil = new Hostil(tieneTesoro, navePirata);
            planetas.add(planetaHostil);
        }
    }
    public void siguienteTurno(Acciones accion, String codigoPlaneta ) {
        Planeta planeta = visitarPlaneta(codigoPlaneta);
        switch (accion) {
            case COMPRAR_COMBUSTIBLE:
            case COMPRAR_ESCUDO:
            case COMPRAR_ARMA:
            case VENDER_ARMA:
                if (planeta instanceof Neutral) {
                    Neutral planetaNeutral = (Neutral) planeta;
                    planetaNeutral.realizarAccionEnMercado(accion, jugador);
                } else {
                    throw new IllegalArgumentException("La acción no es válida para este tipo de planeta.");
                }
                break;
            case BUSCAR_TESORO:
                if (planeta instanceof Hostil) {
                    Hostil planetaHostil = (Hostil) planeta;
                    finDelJuego(planetaHostil.combate(jugador.getNave()), planetaHostil.isTesoro());
                } else {
                    throw new IllegalArgumentException("La acción no es válida para este tipo de planeta.");
                }
                break;
            default:
                throw new IllegalArgumentException("Acción no válida.");
        }
        this.turno++;

    }

    public void mostarListadoPlanetas(TipoDePlaneta tipoPlaneta) {
        for (Planeta planeta : planetas) {
            if (planeta.soyPlanetaTipo() == tipoPlaneta) {
                System.out.println(planeta.getCodigoDePlaneta());
            }
        }
    }
    private void finDelJuego(boolean naveDestruida, boolean tesoroEncontrado) {
        if (naveDestruida) {
            System.out.println("Tu nave ha sido destruida. ¡Game Over!");
        } else if (tesoroEncontrado) {
            System.out.println("¡Felicidades! Has encontrado el tesoro y has ganado el juego.");
        }
    }
    private Planeta visitarPlaneta(String codigoPlaneta) {
        for (Planeta planeta : planetas) {
            if (planeta.getCodigoDePlaneta().equals(codigoPlaneta)) {
                return planeta;
            }
        }
        throw new RuntimeException("No se encontró el planeta solicitado: " + codigoPlaneta);
    }
}