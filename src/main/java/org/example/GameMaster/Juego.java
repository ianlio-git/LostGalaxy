package org.example.GameMaster;

import org.example.GameMaster.Exception.CombustibleInsuficienteException;
import org.example.GameMaster.Exception.NaveDestruidaException;
import org.example.GameMaster.Exception.SinCombustibleException;
import org.example.GameMaster.Exception.TesoroEncontradoException;
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

    private void verificarSiguienteTurno(String codigoDeSistema) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException {
        pasarTurno();
        if (jugador.getPlanetaActual() != null) {
            if (!jugador.getSistemaActual().mostrarNombre().equals(codigoDeSistema)) {
                System.out.println("Cambiando de sistema....");
                SistemaEstelar sistemaNuevo = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema);
                jugador.cambioDeSistema(sistemaNuevo);
            }
        }else{
            String mensaje = ("el sistema donde queres ir no existe");
            finDelJuego(mensaje);
        }
    }


    public void realizarAccionDeCompra(Acciones accion, String codigoDeSistema, double compra) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException {
        verificarSiguienteTurno(codigoDeSistema);
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaNeutral();
        if (jugador.puedoViajar(planeta)) {
            jugador.viajeAPlaneta(planeta);
            planeta.realizarAccionEnMercado(accion, jugador, compra);
        } else {
            String mensaje = "No puedo visitar el planeta neutral, la nave no tiene combustible suficiente para esta accion.";
            finDelJuego(mensaje);
        }
    }

    public void realizarAccionDeReparacion(String codigoDeSistema) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException {
        verificarSiguienteTurno(codigoDeSistema);
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaAliado();
        if (jugador.puedoViajar(planeta)) {
            jugador.viajeAPlaneta(planeta);
            planeta.repararNaveAliada(jugador);
        } else {
            String mensaje ="No puedo visitar el planeta aliado, la nave no tiene combustible suficiente para esta accion.";
            finDelJuego(mensaje);
        }
    }

    public void realizarAccionDeInformacion(String codigoDeSistema) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException {
        verificarSiguienteTurno(codigoDeSistema);
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaAliado();
        if (jugador.puedoViajar(planeta)) {
            jugador.viajeAPlaneta(planeta);
            planeta.obtenerInformacion(sistemaEstelarConTesoro, jugador);
        } else {
            if(planeta==null){
                String mensaje ="No hay planeta aliado en este sistema.";
                finDelJuego(mensaje);
            }else {
                String mensaje ="No puedo visitar el planeta aliado, la nave no tiene combustible suficiente para esta accion.";
                finDelJuego(mensaje);
            }
        }
    }

    public void atacarPlanetaHostil(String codigoDeSistema) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException {
        verificarSiguienteTurno(codigoDeSistema);
        Planeta planeta = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).obtenerPlanetaHostil();
        if (jugador.puedoViajar(planeta)) {
            jugador.viajeAPlaneta(planeta);
            planeta.combate(this.jugador);
            String mensaje;
            if (this.jugador.mostrarTesoro()){
                mensaje = "La nave enemiga fue destruida. ¡Muy bien!, ¡Felicidades, has encontrado el tesoro y has ganado el juego!";
                finDelJuego(mensaje);
            }else{
                mensaje = "La nave enemiga fue destruida. ¡Muy bien!, Por desgracia no has encontrado el tesoro, sigue intentado!";
                finDelJuego(mensaje);
            }
            mapaEstelar.obtenerSistemaEstelar(codigoDeSistema).quitarPlaneta(planeta);
        } else {
            if(planeta==null){
                String mensaje ="No hay planeta mas planetas hostiles en este sistema.";
                finDelJuego(mensaje);
            }else {
                String mensaje ="No puedo atacar el planeta hostil, la nave no tiene combustible suficiente para esta accion.";
                finDelJuego(mensaje);
            }
        }
    }


    private void finDelJuego(String mensaje) throws SinCombustibleException, TesoroEncontradoException, NaveDestruidaException, CombustibleInsuficienteException {
        if (jugador.getPlanetaActual() == null) {
            throw new SinCombustibleException("¡Game Over! Me quedé sin combustible en el cinturón.");
        }

        if (jugador.mostrarTesoro()) {
            throw new TesoroEncontradoException("¡Game Over! Encontraste el tesoro.");
        }

        if (jugador.naveEstaDestruida()) {
            throw new NaveDestruidaException("Tu nave fue destruida. ¡Game Over!");
        }

        if (!jugador.puedoVolverPlanetaNeutral()) {
            switch (jugador.getPlanetaActual().soyPlanetaTipo()) {
                case PLANETA_ALIADO:
                    throw new CombustibleInsuficienteException("Te quedaste sin combustible en un planeta aliado. ¡Game Over!");
                case PLANETA_HOSTIL:
                    throw new CombustibleInsuficienteException("Te quedaste sin combustible en un planeta hostil. ¡Game Over!");
                case PLANETA_NEUTRAL:
                    if (!jugador.getNave().tengoArmas() && jugador.tengoUadeCoinsParaCombustible()) {
                        throw new CombustibleInsuficienteException("Te quedaste sin combustible en un planeta neutral y no tienes suficientes UadeCoins ni armas para vender. ¡Game Over!");
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println(mensaje);
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