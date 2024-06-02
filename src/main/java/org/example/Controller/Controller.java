package org.example.Controller;
import javax.swing.JOptionPane;

import org.example.Views.JugadorView;
import org.example.enums.Acciones;
import org.example.futuraPantalla.PantallaJugador;
import org.example.gameMaster.Juego;
import org.example.gameMaster.Jugador;

public class Controller {
    public static void mostrarDetalleDelJugador() {
        Jugador jugador;
            jugador = Juego.getInstancia().getJugador();
            JugadorView jugadorView = jugador.toViewJugador();
            PantallaJugador pantallaJugador = new PantallaJugador(jugadorView);
            pantallaJugador.mostrarDatosDelJugador();
    }




}
