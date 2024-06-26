package org.example._MVC.Controller;

import org.example._MVC.Model.GameMaster.Juego;
import org.example._MVC.Model.MapaEstelar.Sistemas.SistemaEstelar;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaCambioDeSistema;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaDeCombate;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaMercado;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaPlanetaAliado;
import org.example._MVC.PantallasOK.PantallasInfomacion.*;
import org.example._MVC.PantallasOK.PantallasPrincipales.PantallaGameBegin;
import org.example._MVC.PantallasOK.PantallasPrincipales.PantallaMain;
import org.example._MVC.Views.GameBeginView;
import org.example._MVC.Views.JugadorView;
import org.example._MVC.Views.MessageView;
import org.example._MVC.Views.SistemasView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainController {
    public static void gameBegin() {
        try {
            PantallaGameBegin pantallaGameBegin = new PantallaGameBegin();
            pantallaGameBegin.setVisible(true);
            pantallaGameBegin.waitForGameStart();
            GameBeginView gameBeginView = pantallaGameBegin.getGameBeginView();
            Juego.getInstancia().iniciarJuego(gameBeginView.getNombreDelJugador(), gameBeginView.getNaveJugador(), gameBeginView.getCantidadSistemasEstelares(), gameBeginView.getDificultad());
        } catch (Exception e) {
            mostrarError(e);
        }
    }


    public static void iniciarPantallaMain() {
        try {
            PantallaMain.getInstance().setVisible(true);
            PantallaDeCostes.mostrarPantalla();
            PantallaTurno.getInstancia().mostrarTurno();
            JugadorView jugadorView = Juego.getInstancia().getJugador().toViewJugador();
            PantallaJugador pantallaJugador = PantallaJugador.obtenerInstancia(jugadorView);
            pantallaJugador.mostrarDatosDelJugador();
            List<SistemasView> sistemasViews = new ArrayList<>();
            for (SistemaEstelar sistemaEstelar : Juego.getInstancia().getMapaEstelar().getSistemasEstelares()) {
                SistemasView sistemasView = sistemaEstelar.toViewSistema();
                sistemasViews.add(sistemasView);
            }
            PantallaSistemasEstelares.getInstancia(sistemasViews).mostrarSistemas();
        } catch (Exception e) {
            mostrarError(e);
        }
    }


//error
    private static void mostrarError(Exception e) {
        MessageView messageView = new MessageView(e.getMessage());
        PantallaDeResultados pantallaDeResultados = new PantallaDeResultados(messageView);
        pantallaDeResultados.salirDelJuego(e);
    }

    public static void cerrarApp(int delayMillis) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Cerrando la aplicación...");
                System.exit(0); // Cierra la aplicación
            }
        }, delayMillis);
    }

    public static void gameReset(){
        PantallaCambioDeSistema.getInstance().dispose();
        PantallaDeCombate.getInstance().dispose();
        PantallaMercado.getInstance().dispose();
        PantallaPlanetaAliado.getInstance().dispose();
        JugadorView jugadorView = Juego.getInstancia().getJugador().toViewJugador();
        PantallaJugador pantallaJugador = PantallaJugador.obtenerInstancia(jugadorView);
        pantallaJugador.dispose();
        List<SistemasView> sistemasViews = new ArrayList<>();
        for (SistemaEstelar sistemaEstelar : Juego.getInstancia().getMapaEstelar().getSistemasEstelares()) {
            SistemasView sistemasView = sistemaEstelar.toViewSistema();
            sistemasViews.add(sistemasView);
        }
        PantallaSistemasEstelares.getInstancia(sistemasViews).dispose();
        PantallaTurno.getInstancia().dispose();
        Juego.getInstancia().reiniciarJuego();
    }

}
