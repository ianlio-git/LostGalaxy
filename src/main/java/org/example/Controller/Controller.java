package org.example.Controller;

import org.example.Views.GameBeginView;
import org.example.Views.JugadorView;
import org.example.Views.SistemasView;
import org.example.Enums.Dificultades;
import org.example.Enums.TipoDeNave;
import org.example.FuturoFront.*;
import org.example.GameMaster.Juego;
import org.example.GameMaster.Jugador;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;

public class Controller {

    public static void gameBegin(String nombreDelJugador, double uadeCoinsJugador, TipoDeNave naveJugador, int cantidadSistemasEstelares, Dificultades dificultad) {

        Juego.getInstancia().iniciarJuego(nombreDelJugador,uadeCoinsJugador,naveJugador,cantidadSistemasEstelares,dificultad);
        GameBeginView gameBeginView = Juego.getInstancia().toViewgameBegin();
        PantallaGameBegin pantallaGameBegin = new PantallaGameBegin(gameBeginView);
        PantallaGameBeginInfo pantallaGameBeginInfo = new PantallaGameBeginInfo(gameBeginView);
       // pantallaGameBegin.ingresarDatosDeInicio();
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




}
