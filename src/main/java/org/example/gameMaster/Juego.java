package org.example.gameMaster;

import org.example.enums.Acciones;
import org.example.enums.Dificultades;
import org.example.enums.TipoDePlaneta;
import org.example.mapaEstelar.MapaEstelar;
import org.example.mapaEstelar.sistemas.SistemaEstelar;
import org.example.nave.tiposDeNaves.NaveAegis;
import org.example.nave.tiposDeNaves.NavePhantom;
import org.example.nave.tiposDeNaves.NaveSwift;
import org.example.enums.TipoDeNave;
import org.example.mapaEstelar.sistemas.planetas.Planeta;
import org.example.nave.tiposDeNaves.NaveTitan;

import java.util.Random;

public class Juego {
    private static int turno;
    private static Juego instanciaJuego;
    private MapaEstelar mapaEstelar;
    private Jugador jugador;
    private SistemaEstelar sistemaEstelarConTesoro;

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
            boolean tieneCinturon = random.nextBoolean();
            SistemaEstelar sistemaEstelar = mapaEstelar.agregarSistemaEstelar(dificultad, tieneTesoro, tieneCinturon);
            if(tieneTesoro){
                sistemaEstelarConTesoro = sistemaEstelar;
            }
        }

    }


    public void siguienteTurno(Acciones accion, String codigoDeSistema,double compra) {
        switch (accion) {
            case COMPRAR_COMBUSTIBLE:
            case COMPRAR_ESCUDO:
            case COMPRAR_ARMA:
            case RECARGAR_ESCUDO:
            case VENDER_ARMA:
                this.realizarAccionDeCompra(accion,codigoDeSistema,compra);
                break;
            case BUSCAR_TESORO:
                this.realizarAccionDeAtaque(codigoDeSistema);
                break;
            case REPARAR_NAVE:
                this.realizarAccionDeReparacion(codigoDeSistema);
                break;
            case OBTENER_INFORMACION:
                this.realizarAccionDeInformacion(codigoDeSistema, sistemaEstelarConTesoro);
                break;
            default:

                break;
        }
        this.turno++;
        mostrarDatosDelJugador();
    }

    private void realizarAccionDeCompra(Acciones accion,String codigoDeSistema,double compra) {
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaNeutral();
        if (planeta != null) {
            if (jugador.puedoViajar(planeta)){
                jugador.viajeAPlaneta(planeta);
                planeta.realizarAccionEnMercado(accion,jugador,compra);
            }else{
                System.out.println("no tengo combustible suficiente");
            }
        } else {
            System.out.println("No hay planetas que puedan cumplir con esta accion ");
        }
    }
    private void realizarAccionDeReparacion(String codigoDeSistema){
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaAliado();
        if (planeta != null) {
            if (jugador.puedoViajar(planeta)){
                jugador.viajeAPlaneta(planeta);
                planeta.repararNaveAliada(jugador);
            }else{
                System.out.println("no tengo combustible suficiente");
            }
        } else {
            System.out.println("No hay planetas que puedan cumplir con esta accion ");
        }
    }

    private void realizarAccionDeInformacion(String codigoDeSistema, SistemaEstelar sistemaEstelarConTesoro){
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaAliado();
        if (planeta != null) {
            if (jugador.puedoViajar(planeta)){
                jugador.viajeAPlaneta(planeta);
                planeta.obtenerInformacion(sistemaEstelarConTesoro, jugador);
            }else{
                System.out.println("no tengo combustible suficiente");
            }
        } else {
            System.out.println("No hay planetas que puedan cumplir con esta accion ");
        }
    }


    private void realizarAccionDeAtaque(String codigoDeSistema) {
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaHostil();
        if (planeta != null) {
            if (jugador.puedoViajar(planeta)){
                jugador.viajeAPlaneta(planeta);
                planeta.combate(this.jugador);
                finDelJuego(jugador.getNave().tengoVida(), jugador.mostrarTesoro(),planeta);
                mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).quitarPlaneta(planeta);
            }else{
                System.out.println("no tengo combustible suficiente");
            }
        } else {
            System.out.println("No hay planetas que puedan cumplir con esta accion ");
        }
    }


    private void finDelJuego(boolean naveDestruida, boolean tesoroEncontrado,Planeta planeta) {
        if (!jugador.puedoViajar(planeta) && planeta.soyPlanetaTipo() != TipoDePlaneta.NEUTRAL) {
            System.out.println("Te quedaste a vivir en el planeta " + planeta.getCodigoDePlaneta() + " por falta de combustible. ¡Game Over!");
            System.exit(1);
        }
        if (naveDestruida) {
            System.out.println("La nave enemiga fue destruida. ¡Muy bien!");
        } else {
            System.out.println("Tu nave fue destruida. ¡Game Over!");
            mostrarDatosDelJugador();
            System.exit(1);
        }

        if (tesoroEncontrado && naveDestruida) {
            System.out.println("¡Felicidades! Has encontrado el tesoro y has ganado el juego.");
            mostrarDatosDelJugador();
            System.exit(0);
        } else {
            System.out.println("Por desgracia no haz encontrado el tesoro, sigue intentado!");
        }


    }

    private void mostrarDatosDelJugador() {
       jugador.mostrarDatos();
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