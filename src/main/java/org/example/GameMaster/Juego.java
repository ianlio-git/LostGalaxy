package org.example.GameMaster;

import org.example.Enums.TipoDeArma;
import org.example.GameMaster.Exception.*;
import org.example.Views.GameBeginView;
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
    private boolean turnoExtra;
    private static Juego instanciaJuego;
    private MapaEstelar mapaEstelar;
    private Jugador jugador;

    private Juego() {
        this.mapaEstelar = MapaEstelar.getInstancia();
    }

    public static Juego getInstancia() {
        if (instanciaJuego == null) {
            instanciaJuego = new Juego();
        }
        return instanciaJuego;
    }
    public void iniciarJuego(String nombreDelJugador, double uadeCoinsJugador, TipoDeNave naveJugador, int cantidadSistemasEstelares, Dificultades dificultad){
        if (jugador == null) {
            mapaEstelar.crearMapaEstelar(dificultad,cantidadSistemasEstelares);
            SistemaEstelar sistemaInicial = mapaEstelar.obtenerSistemaEstelar("SIST-0");
            Planeta planetaInicial = sistemaInicial.obtenerPlanetaNeutral();
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


    public void indicarRumboANuevoSistema(String codigoDeSistema) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {
        pasarTurno();
        if(mapaEstelar.verificarExistenciaDeSistemaEstelar(codigoDeSistema)){
            if (!jugador.getSistemaActual().mostrarNombre().equals(codigoDeSistema)) {
                SistemaEstelar sistemaNuevo = mapaEstelar.obtenerSistemaEstelar(codigoDeSistema);
                jugador.comenzarViajeANuevoSistema(sistemaNuevo);
                if(sistemaNuevo.tieneCinturonAsteroides()) {
                    jugador.setPosicionEnElEspacio("En cinturon de asteroides");
                }else{
                    jugador.setPosicionEnElEspacio("En el espacio");
                }
                String mensaje = ("Cambiando de sistema....");
                finDelJuego(mensaje);
            }
            else{
                String mensaje = ("Continua tu aventura entre los planetas del mismo sistema");
                finDelJuego(mensaje);
            }
        }
        else {
            String mensaje = "Ingrese un sistema valido";
            finDelJuego(mensaje);
        }
    }



    public void comprarArma(TipoDeArma tipoDeArma) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {
        Planeta planeta = irAlShopping();
        if(planeta!=null){
            if(planeta.ingresarAlMercado().accionDeComprarArma(jugador, tipoDeArma)){
                finDelJuego("Compraste el "+tipoDeArma+" con exito!");
            }
            else {
                finDelJuego("No tienes suficientes uade coins!");
            }
        }
    }

    public void comprarCombustible(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {
        Planeta planeta = irAlShopping();
        if (!jugador.getNave().getTanque().tanqueLleno()) {
            if (planeta.ingresarAlMercado().accionDeComprarCombustible(jugador, cantidad)) {
                finDelJuego("Compraste combustible con exito!");
            } else {
                finDelJuego("No tienes suficientes uade coins!");
            }
        } else {
            finDelJuego("El tanque ya esta lleno");
        }
    }

    public void recargarEscudo(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {
        Planeta planeta = irAlShopping();
        if(!jugador.getNave().getEscudo().escudoLleno()){
            if(planeta!=null){
                if(planeta.ingresarAlMercado().accionDeRecargarEscudo(jugador,cantidad)){
                    finDelJuego("Recargaste escudo con exito!");
                }else{
                    finDelJuego("No tienes suficientes uade coins!");
                }
            }
        }else{
            finDelJuego("Escudos al maximo");
        }
    }

    public void comprarEscudoMaximo(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {
        Planeta planeta = irAlShopping();
            if(planeta!=null){
                if(planeta.ingresarAlMercado().accionDeComprarEscudoMaximo(jugador,cantidad)){
                    finDelJuego("Compraste escudo con exito");
                }else{
                    finDelJuego("No tienes suficientes uade coins!");
                }
            }
    }

    public void venderArma(TipoDeArma tipoDeArma) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {
        Planeta planeta = irAlShopping();
        if(planeta!=null){
            if(planeta.ingresarAlMercado().accionDeVenderArma(jugador,tipoDeArma)){
                finDelJuego("Vendiste el arma con exito!");
            }else{
                finDelJuego("No posees el arma que quieres vender.");
            }
        }
    }

    private Planeta irAlShopping() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {
        pasarTurno();
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaNeutral();
        jugador.setPlanetaActual(planeta);
        jugador.setPosicionEnElEspacio(planeta.getCodigoDePlaneta());
        if (jugador.puedoViajar(planeta)) {
            jugador.viajeAPlaneta(planeta);
            return planeta;
        }else {
            String mensaje = "No puedo visitar el planeta neutral, la nave no tiene combustible suficiente para esta accion.";
            finDelJuego(mensaje);
        }
        return null;
    }

    public void realizarAccionDeReparacion() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {
        pasarTurno();
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaAliado();
        if(planeta==null){
            String mensaje ="No hay planeta aliado en este sistema.";
            finDelJuego(mensaje);
        }else{

            if (jugador.puedoViajar(planeta)) {
                jugador.viajeAPlaneta(planeta);
                planeta.repararNaveAliada(jugador);
            } else {
                String mensaje ="No puedo visitar el planeta aliado, la nave no tiene combustible suficiente para esta accion.";
                finDelJuego(mensaje);
            }
        }

    }

    public void realizarAccionDeInformacion() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {
        pasarTurno();
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaAliado();
        if(planeta==null){
            String mensaje ="No hay planeta aliado en este sistema.";
            finDelJuego(mensaje);
        }else{
            if (jugador.puedoViajar(planeta)) {
                jugador.viajeAPlaneta(planeta);
                finDelJuego(planeta.obtenerInformacion(jugador));
            }
            else {
                String mensaje = "No puedo visitar el planeta aliado, la nave no tiene combustible suficiente para esta accion.";
                finDelJuego(mensaje);
            }
        }

    }

    public void atacarPlanetaHostil(String codigoDePlaneta) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, TesoroEncontradoException, SinCombustibleEnCinturonDeAsteroides {
        pasarTurno();
        Planeta planeta = jugador.getSistemaActual().obtenerPlanetaHostil(codigoDePlaneta);
        if (planeta == null) {
            String mensaje = "No se pudo encontrar el planeta hostil en el sistema.";
            finDelJuego(mensaje);
        }else {
            if (jugador.puedoViajar(planeta)) {
                jugador.viajeAPlaneta(planeta);
                planeta.combate(this.jugador);
                jugador.getSistemaActual().quitarPlaneta(planeta);
                finDelJuego("La nave enemiga fue destruida. ¡Muy bien!, Por desgracia no has encontrado el tesoro, sigue intentado!");
            } else {
                String mensaje = "No puedo atacar el planeta hostil, la nave no tiene combustible suficiente para esta accion.";
                finDelJuego(mensaje);
            }
        }
    }


    private void finDelJuego(String mensaje) throws SinCombustibleException, TesoroEncontradoException, NaveDestruidaException, CombustibleInsuficienteException, SinCombustibleEnCinturonDeAsteroides {
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
        if(jugador.getNave().getTanque().tanqueVacio()){
            throw new SinCombustibleEnCinturonDeAsteroides("Te perdiste entre los asteroides al quedarte sin combustible!");
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
        }else{
            ++this.turno;
        }

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