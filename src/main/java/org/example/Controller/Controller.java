package org.example.Controller;

import org.example.Enums.TipoDeArma;
import org.example.GameMaster.Exception.*;
import org.example.MapaEstelar.MapaEstelar;
import org.example.Views.GameBeginView;
import org.example.Views.JugadorView;
import org.example.Views.MessageView;
import org.example.Views.SistemasView;
import org.example.Enums.Dificultades;
import org.example.Enums.TipoDeNave;
import org.example.FuturoFront.*;
import org.example.GameMaster.Juego;
import org.example.GameMaster.Jugador;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;

public class Controller{

    public static void gameBegin(String nombreDelJugador, double uadeCoinsJugador, TipoDeNave naveJugador, int cantidadSistemasEstelares, Dificultades dificultad) {

        Juego.getInstancia().iniciarJuego(nombreDelJugador,uadeCoinsJugador,naveJugador,cantidadSistemasEstelares,dificultad);
        GameBeginView gameBeginView = Juego.getInstancia().toViewgameBegin();
        PantallaGameBegin pantallaGameBegin = new PantallaGameBegin(gameBeginView);
        PantallaGameBeginInfo pantallaGameBeginInfo = new PantallaGameBeginInfo(gameBeginView);
        pantallaGameBeginInfo.mostrarDatosDeInicio();
    }

    public static void mostrarDetalleDelJugador() {
        Jugador jugador;
            jugador = Juego.getInstancia().getJugador();
            JugadorView jugadorView = jugador.toViewJugador();
            PantallaJugador pantallaJugador = new PantallaJugador(jugadorView);
            pantallaJugador.mostrarDatosDelJugador();
    }

    public static void mostrarTurno(){
        GameBeginView gameBeginView = Juego.getInstancia().toViewgameBegin();
        PantallaTurno pantallaTurno = new PantallaTurno(gameBeginView);
        pantallaTurno.mostrarTurno();
    }

    public static void mostrarSistemas(){
        for(SistemaEstelar sistemaEstelar : Juego.getInstancia().getMapaEstelar().getSistemasEstelares()){
            SistemasView sistemasView = sistemaEstelar.toViewSistema();
            PantallaSistemasEstelares pantallaSistemasEstelares = new PantallaSistemasEstelares(sistemasView);
            pantallaSistemasEstelares.mostrarSistema();
        }
    }
    public static void cambiarDeSistema(String codigoDeSistema) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, SinCombustibleEnCinturonDeAsteroides, TesoroEncontradoException {
        Juego.getInstancia().indicarRumboANuevoSistema(codigoDeSistema);
    }
    public static void comprarArma(TipoDeArma tipoDeArma) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, SinCombustibleEnCinturonDeAsteroides, TesoroEncontradoException {
        Juego.getInstancia().comprarArma(tipoDeArma);
    }
    public static void venderArma(TipoDeArma tipoDeArma) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, SinCombustibleEnCinturonDeAsteroides, TesoroEncontradoException {
        Juego.getInstancia().venderArma(tipoDeArma);
    }

    public static void recargarEscudo(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, SinCombustibleEnCinturonDeAsteroides, TesoroEncontradoException {
        Juego.getInstancia().recargarEscudo(cantidad);
    }

    public static void comprarEscudoMaximo(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, SinCombustibleEnCinturonDeAsteroides, TesoroEncontradoException {
        Juego.getInstancia().comprarEscudoMaximo(cantidad);
    }

    public static void comprarCombustible(double cantidad) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, SinCombustibleEnCinturonDeAsteroides, TesoroEncontradoException {
        Juego.getInstancia().comprarCombustible(cantidad);
    }


    public static void repararNave() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, SinCombustibleEnCinturonDeAsteroides, TesoroEncontradoException {
        Jugador jugador;
        jugador = Juego.getInstancia().getJugador();
        Juego.getInstancia().realizarAccionDeReparacion();
        JugadorView jugadorView = jugador.toViewJugador();
        PantallaDeReparacionDeNave pantallaDeReparacionDeNave = new PantallaDeReparacionDeNave(jugadorView);
        if (jugadorView.getPlanetaActual().getCodigoDePlaneta().contains("ALI")) {
            pantallaDeReparacionDeNave.bienvenidaAlTaller(jugadorView);
        }
    }

    public static void localizarTesoro() throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, SinCombustibleEnCinturonDeAsteroides, TesoroEncontradoException {
        Juego.getInstancia().realizarAccionDeInformacion();
    }

    public static void atacarPlanetaHostil(String codigoDePlaneta) throws SinCombustibleException, NaveDestruidaException, CombustibleInsuficienteException, SinCombustibleEnCinturonDeAsteroides, TesoroEncontradoException {
        Juego.getInstancia().atacarPlanetaHostil(codigoDePlaneta);
    }

    public static void infoUpdate(){
        MessageView messageView = Juego.getInstancia().messageToView();
        PantallaDeResultados pantallaDeResultados = new PantallaDeResultados(messageView);
        pantallaDeResultados.mostrarMensaje();
    }


}
