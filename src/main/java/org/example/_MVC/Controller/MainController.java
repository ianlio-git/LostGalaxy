package org.example._MVC.Controller;

import org.example.GameMaster.Juego;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeResultados;
import org.example._MVC.PantallasOK.PantallasPrincipales.PantallaGameBegin;
import org.example._MVC.PantallasOK.PantallasPrincipales.PantallaMain;
import org.example._MVC.Views.GameBeginView;
import org.example._MVC.Views.MessageView;

public class MainController {
    public static void gameBegin() {
        try {
            PantallaGameBegin pantallaGameBegin = new PantallaGameBegin();
            pantallaGameBegin.setVisible(true);
            pantallaGameBegin.waitForGameStart();
            GameBeginView gameBeginView = pantallaGameBegin.getGameBeginView();
            Juego.getInstancia().iniciarJuego(gameBeginView.getNombreDelJugador(), gameBeginView.getUadeCoinsJugador(), gameBeginView.getNaveJugador(), gameBeginView.getCantidadSistemasEstelares(), gameBeginView.getDificultad());
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

}
