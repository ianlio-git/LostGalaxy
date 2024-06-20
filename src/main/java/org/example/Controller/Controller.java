package org.example.Controller;

import org.example.Enums.TipoDeArma;
import org.example.Views.GameBeginView;
import org.example.Views.JugadorView;
import org.example.Views.MessageView;
import org.example.Views.SistemasView;
import org.example.Enums.Dificultad;
import org.example.Enums.TipoDeNave;
import org.example.FuturoFront.*;
import org.example.GameMaster.Juego;
import org.example.GameMaster.Jugador;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Controller{

    public static void gameBegin() {
        try {
            PantallaGameBegin pantallaGameBegin = new PantallaGameBegin();
            pantallaGameBegin.setVisible(true);
            pantallaGameBegin.waitForGameStart();
            GameBeginView gameBeginView = pantallaGameBegin.getGameBeginView();
            Juego.getInstancia().iniciarJuego(gameBeginView.getNombreDelJugador(),gameBeginView.getUadeCoinsJugador(),gameBeginView.getNaveJugador(),gameBeginView.getCantidadSistemasEstelares(),gameBeginView.getDificultad());
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void mostrarDetalleDelJugador() {
        try {
            Jugador jugador = Juego.getInstancia().getJugador();
            JugadorView jugadorView = jugador.toViewJugador();
            PantallaJugador pantallaJugador = new PantallaJugador(jugadorView);
            pantallaJugador.mostrarDatosDelJugador();
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void mostrarTurno() {
        try {
            GameBeginView gameBeginView = Juego.getInstancia().toViewgameBegin();
            PantallaTurno pantallaTurno = new PantallaTurno(gameBeginView);
            pantallaTurno.mostrarTurno();
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void mostrarSistemas() {
        try {
            List<SistemasView> sistemasViews = new ArrayList<>();
            for (SistemaEstelar sistemaEstelar : Juego.getInstancia().getMapaEstelar().getSistemasEstelares()) {
                SistemasView sistemasView = sistemaEstelar.toViewSistema();
                sistemasViews.add(sistemasView);
            }
            PantallaSistemasEstelares.mostrarSistemas(sistemasViews);
        } catch (Exception e) {
            mostrarError(e);
        }
    }



    public static void cambiarDeSistema(String codigoDeSistema) {
        try {
            Juego.getInstancia().indicarRumboANuevoSistema(codigoDeSistema);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void comprarArma(TipoDeArma tipoDeArma) {
        try {
            Juego.getInstancia().comprarArma(tipoDeArma);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void venderArma(TipoDeArma tipoDeArma) {
        try {
            Juego.getInstancia().venderArma(tipoDeArma);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void recargarEscudo(double cantidad) {
        try {
            Juego.getInstancia().recargarEscudo(cantidad);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void comprarEscudoMaximo(double cantidad) {
        try {
            Juego.getInstancia().comprarEscudoMaximo(cantidad);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void comprarCombustible(double cantidad) {
        try {
            Juego.getInstancia().comprarCombustible(cantidad);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void repararNave() {
        try {
            Jugador jugador = Juego.getInstancia().realizarAccionDeReparacion();
            JugadorView jugadorView = jugador.toViewJugador();
            PantallaDeReparacionDeNave pantallaDeReparacionDeNave = new PantallaDeReparacionDeNave(jugadorView);
            if (jugadorView.getPlanetaActual().getCodigoDePlaneta().contains("ALI")) {
                pantallaDeReparacionDeNave.bienvenidaAlTaller(jugadorView);
            }
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void localizarTesoro() {
        try {
            Juego.getInstancia().realizarAccionDeInformacion();
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void atacarPlanetaHostil(String codigoDePlaneta) {
        try {
            Juego.getInstancia().atacarPlanetaHostil(codigoDePlaneta);
        } catch (Exception e) {
            mostrarError(e);
        }
    }


    private static void mostrarError(Exception e) {
        MessageView messageView = new MessageView(e.getMessage());
        PantallaDeResultados pantallaDeResultados = new PantallaDeResultados(messageView);
        pantallaDeResultados.salirDelJuego(e);
    }
}
