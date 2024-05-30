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
            crearMapaEstelar(dificultad,cantidadSistemasEstelares);
            Planeta planetaInicial = mapaEstelar.obtenerSistemaEstelar("SIST-0").obtenerPlanetaAliado();
            switch (naveJugador) {
                case NAVE_AEGIS:
                    NaveAegis naveAegis = new NaveAegis();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveAegis,planetaInicial);
                    break;
                case NAVE_SWIFT:
                    NaveSwift naveSwift = new NaveSwift();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveSwift,planetaInicial);
                    break;
                case NAVE_PHANTOM:
                    NavePhantom navePhantom = new NavePhantom();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, navePhantom,planetaInicial);
                    break;
                case NAVE_TITAN:
                    NaveTitan naveTitan = new NaveTitan();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveTitan,planetaInicial);
                    break;
            }
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
                jugador.establecerPlanetaActual(planeta);
                System.out.println("No puedo visitar el planeta neutral, ya no me queda combustible.");
                finDelJuego(planeta);
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
                jugador.establecerPlanetaActual(planeta);
                System.out.println("no tengo combustible suficiente");
                finDelJuego(planeta);
            }
        } else {
            System.out.println("No hay planetas aliados en este sistema estelar.");
        }
        finDelJuego(planeta);
    }

    private void realizarAccionDeInformacion(String codigoDeSistema, SistemaEstelar sistemaEstelarConTesoro){
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaAliado();
        if (planeta != null) {
            if (jugador.puedoViajar(planeta)){
                jugador.viajeAPlaneta(planeta);
                planeta.obtenerInformacion(sistemaEstelarConTesoro, jugador);
            }else{
                jugador.establecerPlanetaActual(planeta);
                System.out.println("No puedo visitar el planeta aliado, ya no me queda combustible.");
                finDelJuego(planeta);
            }
        } else {
            System.out.println("No hay planetas aliados en este sistema estelar");
        }

    }


    private void realizarAccionDeAtaque(String codigoDeSistema) {
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaHostil();
        if (planeta != null) {
            if (jugador.puedoViajar(planeta)){
                jugador.viajeAPlaneta(planeta);
                planeta.combate(this.jugador);
                finDelJuego(planeta);
                mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).quitarPlaneta(planeta);
            }else{
                jugador.establecerPlanetaActual(planeta);
                System.out.println("No puedo realizar un ataque la nave se ha quedado sin combustible");
                finDelJuego(planeta);
            }
        } else {
            System.out.println("No hay planetas enemigos restantes en este sistema estelar prueba en otro");
        }
    }


    private void finDelJuego(Planeta planeta) {
        if(jugador.mostrarTesoro()) {
            System.out.println("******************************************");
            System.out.println("¡Game Over!");
            System.out.println("******************************************");
            System.exit(0);
        }
       if (jugador.naveEstaDestruida()) {
           System.out.println("****************************************************");
           System.out.println("Tu nave fue destruida en el planeta " + planeta.getCodigoDePlaneta() + " ¡Game Over!");
           System.out.println("****************************************************");
           System.exit(1);
       }
       if (!jugador.puedoViajar(planeta)&&(planeta!=jugador.MiPlanetaActual())&&planeta!=null) {
            System.out.println("******************************************");
            System.out.println("Sin combustible. ¡Game Over!");
           System.out.println("******************************************");
            System.exit(2);
       }
       if(jugador.getNave().getTanque().getCombustible()==0) {
           System.out.println("******************************************");
           System.out.println("Sin combustible. ¡Game Over!");
           System.out.println("******************************************");
           System.exit(2);
       }
            System.out.println("Turno: "+this.turno);
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