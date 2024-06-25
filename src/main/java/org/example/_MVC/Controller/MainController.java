package org.example._MVC.Controller;

import org.example.GameMaster.Juego;
import org.example.GameMaster.Jugador;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaCambioDeSistema;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaDeCombate;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaMercado;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaPlanetaAliado;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeResultados;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaJugador;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaSistemasEstelares;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaTurno;
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
            PantallaMain pantallaMain = new PantallaMain();
            pantallaMain.setVisible(true);
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
        PantallaCambioDeSistema.getInstance().setVisible(false);
        PantallaDeCombate.getInstance().setVisible(false);
        PantallaMercado.getInstance().setVisible(false);
        PantallaPlanetaAliado.getInstance().setVisible(false);
        JugadorView jugadorView = Juego.getInstancia().getJugador().toViewJugador();
        PantallaJugador pantallaJugador = PantallaJugador.obtenerInstancia(jugadorView);
        pantallaJugador.setVisible(false);

        List<SistemasView> sistemasViews = new ArrayList<>();
        for (SistemaEstelar sistemaEstelar : Juego.getInstancia().getMapaEstelar().getSistemasEstelares()) {
            SistemasView sistemasView = sistemaEstelar.toViewSistema();
            sistemasViews.add(sistemasView);
        }
        PantallaSistemasEstelares.getInstancia(sistemasViews).setVisible(false);
        // PantallaSistemasEstelares.getInstancia(sistemasViews).destruirPantalla(); Esto se rompe todo porque no reinicia los planetas, es decir, si lo destruiste no lo vuelve a construir.
        PantallaTurno.getInstancia().setVisible(false);
        Juego.getInstancia().reiniciarJuego();
    }

}
