package org.example._MVC.Controller;

import org.example.GameMaster.Juego;
import org.example.GameMaster.Jugador;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeResultados;
import org.example._MVC.Views.MessageView;

public class PlanetaAliadoController {

    public static boolean repararNave() {
        try {
            Jugador jugador = Juego.getInstancia().realizarAccionDeReparacion();
            return true;
        } catch (Exception e) {
            mostrarError(e);
            return false;
        }
    }

    public static boolean localizarTesoro() {
        try {
            Juego.getInstancia().realizarAccionDeInformacion();
            return true;
        } catch (Exception e) {
            mostrarError(e);
            return false;
        }
    }

    private static void mostrarError(Exception e) {
        MessageView messageView = new MessageView(e.getMessage());
        PantallaDeResultados pantallaDeResultados = new PantallaDeResultados(messageView);
        pantallaDeResultados.salirDelJuego(e);
    }
}
