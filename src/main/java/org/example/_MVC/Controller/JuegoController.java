package org.example._MVC.Controller;
import org.example._MVC.Model.GameMaster.Jugador;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaCambioDeSistema;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaDeCombate;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaPlanetaAliado;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeResultados;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaMercado;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaJugador;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaSistemasEstelares;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaTurno;
import org.example._MVC.Views.JugadorView;
import org.example._MVC.Views.MessageView;
import org.example._MVC.Views.SistemasView;
import org.example._MVC.Model.GameMaster.Juego;
import org.example._MVC.Model.MapaEstelar.Sistemas.SistemaEstelar;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class JuegoController {



    public static void mostrarDetalleDelJugador() {
        try {
            Jugador jugador = Juego.getInstancia().getJugador();
            JugadorView jugadorView = jugador.toViewJugador();

            // Obtener la instancia única de PantallaJugador
            PantallaJugador pantallaJugador = PantallaJugador.obtenerInstancia(jugadorView);

            // Mostrar la ventana con los datos actualizados del jugador
            pantallaJugador.mostrarDatosDelJugador();

        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void mostrarTurno() {
        try {
            PantallaTurno.getInstancia().mostrarTurno();
            PantallaTurno.getInstancia().iniciarActualizarTurno(); // Iniciar la actualización del turno
        } catch (Exception e) {
            mostrarError(e);
        }
    }
    public static void mostrarSistemas() {
        try {
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

    public static void cambiarDeSistema() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    PantallaCambioDeSistema.getInstance().setVisible(true);
                } catch (Exception e) {
                    mostrarError(e);
                }
            }
        });
    }
    public static void combate() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    PantallaDeCombate.getInstance().setVisible(true);
                } catch (Exception e) {
                    mostrarError(e);
                }
            }
        });
    }
    public static void mercado() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    PantallaMercado.getInstance().setVisible(true);
                } catch (Exception e) {
                    mostrarError(e);
                }
            }
        });
    }

    public static void planetaAliado() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {

                    PantallaPlanetaAliado.getInstance().setVisible(true);
                } catch (Exception e) {
                    mostrarError(e);
                }
            }
        });
    }

    private static void mostrarError(Exception e) {
        MessageView messageView = new MessageView(e.getMessage());
        PantallaDeResultados pantallaDeResultados = new PantallaDeResultados(messageView);
        pantallaDeResultados.salirDelJuego(e);
    }
}
