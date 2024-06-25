package org.example._MVC.Controller;

import org.example.GameMaster.Exception.*;
import org.example.GameMaster.Juego;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaDeCombate;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeResultados;
import org.example._MVC.Views.MessageView;

public class PlanetaHostilController {

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
