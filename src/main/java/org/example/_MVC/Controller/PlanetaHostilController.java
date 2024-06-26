package org.example._MVC.Controller;

import org.example.GameMaster.Exception.*;
import org.example.GameMaster.Juego;
import org.example.GameMaster.Jugador;
import org.example.MapaEstelar.Sistemas.Planetas.Hostil;
import org.example.MapaEstelar.Sistemas.Planetas.Planeta;
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
    public static Planeta obtenerPlaneta(String codigoDePlaneta) {
        try {
            Jugador jugador = Juego.getInstancia().getJugador();
            return Juego.getInstancia().getMapaEstelar().obtenerSistemaEstelar(jugador.getSistemaActual().mostrarNombre()).obtenerPlanetaHostil(codigoDePlaneta);
        } catch (Exception e) {
            mostrarError(e);
            return null;
        }
    }
}
