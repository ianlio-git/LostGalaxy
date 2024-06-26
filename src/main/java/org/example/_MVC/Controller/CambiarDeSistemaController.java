package org.example._MVC.Controller;

import org.example.GameMaster.Juego;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeResultados;
import org.example._MVC.Views.MessageView;
import org.example._MVC.Views.MapaEstelarView;

public class CambiarDeSistemaController {
    public static void cambiarSistema(String codigoDeSistema) {
        try {
            Juego.getInstancia().indicarRumboANuevoSistema(codigoDeSistema);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static SistemaEstelar obtenerSistema(String codigoDeSistema) {
        try {
            SistemaEstelar sistema = Juego.getInstancia().getMapaEstelar().obtenerSistemaEstelar(codigoDeSistema);
            return sistema;
        } catch (Exception e) {
            mostrarError(e);
            return null;
        }
    }

    private static void mostrarError(Exception e) {
        MessageView messageView = new MessageView(e.getMessage());
        PantallaDeResultados pantallaDeResultados = new PantallaDeResultados(messageView);
        pantallaDeResultados.salirDelJuego(e);
    }
}

