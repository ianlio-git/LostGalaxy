package org.example._MVC.Controller;

import org.example.GameMaster.Juego;
import org.example.GameMaster.Jugador;
import org.example._MVC.PantallasOK.PantallaDeResultados;
import org.example._MVC.PantallasOK.PantallaDeReparacionDeNave;
import org.example._MVC.Views.JugadorView;
import org.example._MVC.Views.MessageView;

public class PlanetaAliadoController {

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

    private static void mostrarError(Exception e) {
        MessageView messageView = new MessageView(e.getMessage());
        PantallaDeResultados pantallaDeResultados = new PantallaDeResultados(messageView);
        pantallaDeResultados.salirDelJuego(e);
    }
}
