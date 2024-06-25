package org.example._MVC.Controller;

import org.example.GameMaster.Juego;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeResultados;
import org.example._MVC.Views.MessageView;

public class CambiarDeSistemaController {
    public static void cambiarSistema(String codigoDeSistema) {
        try {
            Juego.getInstancia().indicarRumboANuevoSistema(codigoDeSistema);
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

