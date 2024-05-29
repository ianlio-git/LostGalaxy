package org.example.gameMaster;

import org.example.enums.Acciones;
import org.example.enums.Dificultades;
import org.example.enums.TipoDeArma;
import org.example.mapaEstelar.MapaEstelar;
import org.example.nave.tiposDeNaves.NaveAegis;
import org.example.nave.tiposDeNaves.NavePhantom;
import org.example.nave.tiposDeNaves.NaveSwift;
import org.example.enums.TipoDeNave;
import org.example.mapaEstelar.sistemas.planetas.Planeta;
import org.example.nave.tiposDeNaves.NaveTitan;

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
        int var = random.nextInt(cantidadSistemasEstelares);
        for (int i = 0; i < cantidadSistemasEstelares; i++) {
            boolean tieneTesoro = (i == var);
            System.out.println(tieneTesoro);
            boolean tieneCinturon = random.nextBoolean();
            mapaEstelar.agregarSistemaEstelar(dificultad, tieneTesoro, tieneCinturon);
        }
    }
    public void siguienteTurno(Acciones accion, String codigoDeSistema,double compra) {
        switch (accion) {
            case COMPRAR_COMBUSTIBLE:
            case COMPRAR_ESCUDO:
            case COMPRAR_ARMA:
                this.realizarAccionDeCompra(accion,codigoDeSistema,compra);
                break;
            case BUSCAR_TESORO:
                this.realizarAccionDeAtaque(codigoDeSistema);
                break;
            default:
        }
        this.turno++;
        mostrarDatosDelJugador();
    }
    private void realizarAccionDeCompra(Acciones accion,String codigoDeSistema,double compra) {
        Planeta planetaNeutral = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaNeutral();
        if (planetaNeutral != null) {
            planetaNeutral.realizarAccionEnMercado(accion,jugador,compra);
        } else {
            System.out.println("No hay planetas que puedan cumplir con esta accion ");
        }
    }
    private void realizarAccionDeAtaque(String codigoDeSistema) {
        Planeta planetaHostil = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaHostil();
        if (planetaHostil != null) {
            planetaHostil.combate(this.jugador);
            finDelJuego(jugador.getNave().tengoVida(), jugador.mostrarTesoro());
            mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).quitarPlaneta(planetaHostil);
        } else {
            System.out.println("No quedan mas planetas enemigos en este sitema solar por favor viaja al siguiente");
        }
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
        if (jugador.getNave().tengoArmas()) {
            System.out.println("Mis armas son:" );
            jugador.getNave().getArmas();
            System.out.println("Poder:" + jugador.getNave().poderAtaque());
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