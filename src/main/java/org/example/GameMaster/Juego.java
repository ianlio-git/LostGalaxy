package org.example.GameMaster;

import org.example.Views.GameBeginView;
import org.example.Enums.Acciones;
import org.example.Enums.Dificultades;
import org.example.MapaEstelar.MapaEstelar;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example.Nave.TiposDeNaves.NaveAegis;
import org.example.Nave.TiposDeNaves.NavePhantom;
import org.example.Nave.TiposDeNaves.NaveSwift;
import org.example.Enums.TipoDeNave;
import org.example.MapaEstelar.Sistemas.Planetas.Planeta;
import org.example.Nave.TiposDeNaves.NaveTitan;

import java.util.Random;

public class Juego {
    private static int turno;
    private static Juego instanciaJuego;
    private MapaEstelar mapaEstelar;
    private Jugador jugador;
    private SistemaEstelar sistemaEstelarConTesoro;
    private boolean turnoExtra;
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
            SistemaEstelar sistemaInicial = mapaEstelar.obtenerSistemaEstelar("SIST-0");
            Planeta planetaInicial = sistemaInicial.obtenerPlanetaNeutral();
            System.out.println("Juego Iniciado!");
            switch (naveJugador) {
                case NAVE_AEGIS:
                    NaveAegis naveAegis = new NaveAegis();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveAegis,planetaInicial,sistemaInicial);
                    break;
                case NAVE_SWIFT:
                    NaveSwift naveSwift = new NaveSwift();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveSwift,planetaInicial,sistemaInicial);
                    break;
                case NAVE_PHANTOM:
                    NavePhantom navePhantom = new NavePhantom();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, navePhantom,planetaInicial,sistemaInicial);
                    break;
                case NAVE_TITAN:
                    NaveTitan naveTitan = new NaveTitan();
                    this.jugador = new Jugador(nombreDelJugador, uadeCoinsJugador, naveTitan,planetaInicial,sistemaInicial);
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

    private void siguienteTurno(String codigoDeSistema) {
        pasarTurno();
        SistemaEstelar sistemaNuevo = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema);
        jugador.cambioDeSistema(sistemaNuevo);
        finDelJuego();
    }


    public void realizarAccionDeCompra(Acciones accion, String codigoDeSistema, double compra) {
        siguienteTurno(codigoDeSistema);
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaNeutral();
        if (jugador.puedoViajar(planeta)) {
            jugador.viajeAPlaneta(planeta);
            planeta.realizarAccionEnMercado(accion, jugador, compra);
        } else {
            System.out.println("No puedo visitar el planeta neutral, la nave no tiene combustible suficiente para esta accion.");
            finDelJuego();
        }
    }

    public void realizarAccionDeReparacion(String codigoDeSistema) {
        siguienteTurno(codigoDeSistema);
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaAliado();
        if (jugador.puedoViajar(planeta)) {
            jugador.viajeAPlaneta(planeta);
            planeta.repararNaveAliada(jugador);
        } else {
            System.out.println("No puedo visitar el planeta aliado, la nave no tiene combustible suficiente para esta accion.");
            finDelJuego();
        }
    }

    public void realizarAccionDeInformacion(String codigoDeSistema) {
        siguienteTurno(codigoDeSistema);
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaAliado();
        if (jugador.puedoViajar(planeta)) {
            jugador.viajeAPlaneta(planeta);
            planeta.obtenerInformacion(sistemaEstelarConTesoro, jugador);
        } else {
            if(planeta==null){
                System.out.println("No hay planeta aliado en este sistema.");
                finDelJuego();
            }else {
                System.out.println("No puedo visitar el planeta aliado, la nave no tiene combustible suficiente para esta accion.");
                finDelJuego();
            }
        }
    }

    public void atacarPlanetaHostil(String codigoDeSistema) {
        siguienteTurno(codigoDeSistema);
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaHostil();
        if (jugador.puedoViajar(planeta)) {
            jugador.viajeAPlaneta(planeta);
            planeta.combate(this.jugador);
            finDelJuego();
            mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).quitarPlaneta(planeta);
        } else {
            if(planeta==null){
                System.out.println("No hay planeta mas planetas hostiles en este sistema.");
                finDelJuego();
            }else {
                System.out.println("No puedo atacar el planeta hostil, la nave no tiene combustible suficiente para esta accion.");
                finDelJuego();
            }
        }
    }


    private void finDelJuego() {
        if(jugador.getPlanetaActual()==null){
            System.out.println("****************************************************");
            System.out.println("¡Game Over! Me quede sin combustible en el cinturon.");
            System.out.println("****************************************************");
            System.exit(3);
        }

        if (jugador.mostrarTesoro()) {
            System.out.println("****************************************************");
            System.out.println("¡Game Over! Encontraste el tesoro.");
            System.out.println("****************************************************");
            System.exit(0);
        }

        if (jugador.naveEstaDestruida()) {
            System.out.println("****************************************************");
            System.out.println("Tu nave fue destruida. ¡Game Over!");
            System.out.println("****************************************************");
            System.exit(1);
        }

        if (!jugador.puedoVolverPlanetaNeutral()) {
            switch (jugador.getPlanetaActual().soyPlanetaTipo()) {
                case PLANETA_ALIADO:
                    System.out.println("****************************************************");
                    System.out.println("Te quedaste sin combustible en un planeta aliado. ¡Game Over!");
                    System.out.println("****************************************************");
                    System.exit(2);
                    break;
                case PLANETA_HOSTIL:
                    System.out.println("****************************************************");
                    System.out.println("Te quedaste sin combustible en un planeta hostil. ¡Game Over!");
                    System.out.println("****************************************************");
                    System.exit(2);
                    break;
                case PLANETA_NEUTRAL:
                    if (!jugador.getNave().tengoArmas()&&jugador.tengoUadeCoinsParaCombustible()) {
                        System.out.println("****************************************************");
                        System.out.println("Te quedaste sin combustible en un planeta neutral y no tienes suficientes UadeCoins ni armas para vender. ¡Game Over!");
                        System.out.println("****************************************************");
                        System.exit(2);
                    }
                    break;
                default:
                    break;
            }
        }

    }

    private void pasarTurno(){
        if (jugador.getNave().soyNaveTipo() == TipoDeNave.NAVE_TITAN) {
            if (turnoExtra) {
                turnoExtra = false;
            } else {
                turnoExtra = true;
                ++this.turno;
            }
        }
        else{
            ++this.turno;
        }

    }

    public void mostrarPlanetasEnSistema(String codigoDeSistema){
        System.out.println("planetas en sistema "+ codigoDeSistema+":");
        mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).mostarListadoPlanetas();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public GameBeginView toViewgameBegin(){
        return new GameBeginView(jugador.getNombre(), jugador.getUadeCoins(),jugador.getNave().soyNaveTipo(),mapaEstelar.getSistemasEstelares().size(),mapaEstelar.getDificultad(),turno);
    }

    public MapaEstelar getMapaEstelar() {
        return mapaEstelar;
    }
}