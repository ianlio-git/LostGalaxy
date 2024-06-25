package org.example._MVC.Controller;
import org.example.GameMaster.Jugador;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaCambioDeSistema;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaDeCombate;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaPlanetaAliado;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeResultados;
import org.example._MVC.PantallasOK.PantallasDePlanetas.PantallaMercado;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaJugador;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaSistemasEstelares;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaTurno;
import org.example._MVC.Views.GameBeginView;
import org.example._MVC.Views.JugadorView;
import org.example._MVC.Views.MessageView;
import org.example._MVC.Views.SistemasView;
import org.example.GameMaster.Juego;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
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
            PantallaTurno.getInstancia().setVisible(true);
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
                    PantallaCambioDeSistema pantallaCambioDeSistema= new PantallaCambioDeSistema();
                    pantallaCambioDeSistema.setVisible(true);
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
                    PantallaDeCombate pantallaDeCombate = new PantallaDeCombate();
                    pantallaDeCombate.setVisible(true);
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
                    PantallaMercado pantallaMercado = new PantallaMercado();
                    pantallaMercado.setVisible(true);
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
                    PantallaPlanetaAliado pantallaPlanetaAliado = new PantallaPlanetaAliado();
                    pantallaPlanetaAliado.setVisible(true);
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
