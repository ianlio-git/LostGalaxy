package org.example.gameMaster;

import org.example.enums.Acciones;
import org.example.enums.Dificultades;
import org.example.enums.TipoDePlaneta;
import org.example.mapaEstelar.MapaEstelar;
import org.example.nave.tiposDeNaves.NaveAegis;
import org.example.nave.tiposDeNaves.NavePhantom;
import org.example.nave.tiposDeNaves.NaveSwift;
import org.example.enums.TipoDeNave;
import org.example.mapaEstelar.sistemas.planetas.Hostil;
import org.example.mapaEstelar.sistemas.planetas.Neutral;
import org.example.mapaEstelar.sistemas.planetas.Planeta;
import org.example.nave.tiposDeNaves.NaveTitan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Juego {
    private static Juego instanciaJuego;
    private MapaEstelar mapaEstelar;
    private static int turno;
    private Jugador jugador;

    private Juego() {
        this.mapaEstelar = new MapaEstelar();
    }
    public static Juego getInstancia() {
        if (instanciaJuego == null) {
            instanciaJuego = new Juego();
        }
        return instanciaJuego;
    }
    public void iniciarJuego(String nombreDelJugador, double uadeCoinsJugador, TipoDeNave naveJugador, int cantidadSistemasEstelares, Dificultades dificultad){
        if (jugador == null) {
            switch (naveJugador) {
                case NAVE_AEGIS:
                    NaveAegis naveAegis = new NaveAegis();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveAegis);
                    break;
                case NAVE_SWIFT:
                    NaveSwift naveSwift = new NaveSwift();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveSwift);
                    break;
                case NAVE_PHANTOM:
                    NavePhantom navePhantom = new NavePhantom();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, navePhantom);
                    break;
                case NAVE_TITAN:
                    NaveTitan naveTitan = new NaveTitan();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveTitan);
                    break;
            }
            crearMapaEstelar(dificultad,cantidadSistemasEstelares);
        } else {
            throw new RuntimeException("El juego ya fue iniciado");
        }
    }
    private void crearMapaEstelar(Dificultades dificultad, int cantidadSistemasEstelares){
        Random random = new Random();
        for (int i = 0; i < cantidadSistemasEstelares; i++) {
            boolean tieneTesoro = (i == random.nextInt(cantidadSistemasEstelares));
            boolean tieneCinturon = random.nextBoolean();
            mapaEstelar.agregarSistemaEstelar(dificultad, tieneTesoro, tieneCinturon);
        }
    }
    public void siguienteTurno(Acciones accion, String codigoDeSistema,String codigoDePlaneta,double compra) {
        switch (accion) {
            case COMPRAR_COMBUSTIBLE:
            case COMPRAR_ESCUDO:
            case COMPRAR_ARMA:
                this.realizarAccionDeCompra(accion,codigoDeSistema, codigoDePlaneta,compra);
                break;
            case BUSCAR_TESORO:
                this.realizarAccionDeAtaque(codigoDeSistema, codigoDePlaneta);
                break;
            default:
        }
        this.turno++;
        mostrarDatosDelJugador();
    }
    private void realizarAccionDeCompra(Acciones accion,String codigoDeSistema,String codigoDePlaneta,double compra) {
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlaneta(codigoDePlaneta);
        if (planeta instanceof Neutral) {
            Neutral planetaNeutral = (Neutral) planeta;
            planetaNeutral.realizarAccionEnMercado(accion, jugador);
        } else {
            throw new IllegalArgumentException("La acción no es válida para este tipo de planeta.");
        }

    }
    private void realizarAccionDeAtaque(String codigoDeSistema,String codigoDePlaneta) {
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlaneta(codigoDePlaneta);
        if (planeta instanceof Hostil) {
            Hostil planetaHostil = (Hostil) planeta;
            finDelJuego(planetaHostil.combate(jugador.getNave()), planetaHostil.isTesoro());
            jugador.agregarUadeCoins(jugador.getNave().getRecompensa());
            jugador.getNave().setRecompensa(0);
            mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).quitarPlaneta(planetaHostil);
        } else {
            throw new IllegalArgumentException("La acción no es válida para este tipo de planeta.");
        }
        throw new IllegalArgumentException("Acción no válida.");
    }


    private void finDelJuego(boolean naveDestruida, boolean tesoroEncontrado) {
        if (naveDestruida) {
            System.out.println("La nave enemiga fue destruida. ¡Muy bien!");
        } else {
            System.out.println("Tu nave fue destruida. ¡Game Over!");
            mostrarDatosDelJugador();
            System.exit(1);
        }
        ;
        if (tesoroEncontrado && naveDestruida) {
            System.out.println("¡Felicidades! Has encontrado el tesoro y has ganado el juego.");
            mostrarDatosDelJugador();
            System.exit(0);
        } else {
            System.out.println("Por desgracia no haz encontrado el tesoro, sigue intentado!");
        }
    }

    private void mostrarDatosDelJugador() {
        System.out.println("==============================================");
        System.out.println("Cantidad de escudo:" + jugador.getNave().getEscudo());
        System.out.println("Nombre:" + jugador.getNombre());
        System.out.println("Cant de uade coins:" + jugador.getUadeCoins());
        System.out.println("Cantidad de combustible:" + jugador.getNave().getCombustible());
        System.out.println("Cantidad de vida:" + jugador.getNave().getVida());
        if (jugador.getNave().getArma() != null) {
            System.out.println("Mi arma es una:" + jugador.getNave().getArma().getTipoDeArma());
        } else {
            System.out.println("Aun no tengo un arma, pero ya tendre una!");
        }
        System.out.println("==============================================");
    }
    public void mostrarSistemas(){
        System.out.println("Sistemas:");
        mapaEstelar.mostrarSistemas();
    }
    public void mostrarPlanetasEnSistema(String codigoDeSistema){
        System.out.println("planetas en sistema "+ codigoDeSistema+":");
        mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).mostarListadoPlanetas();
    }
}