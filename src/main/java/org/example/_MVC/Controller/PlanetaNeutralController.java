package org.example._MVC.Controller;

import org.example.Enums.TipoDeArma;
import org.example.GameMaster.Juego;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeResultados;
import org.example._MVC.Views.MessageView;

public class PlanetaNeutralController {

    public static void comprarArma(TipoDeArma tipoDeArma) {
        try {
            Juego.getInstancia().comprarArma(tipoDeArma);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void venderArma(TipoDeArma tipoDeArma) {
        try {
            Juego.getInstancia().venderArma(tipoDeArma);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void recargarEscudo(double cantidad) {
        try {
            Juego.getInstancia().recargarEscudo(cantidad);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void comprarEscudoMaximo(double cantidad) {
        try {
            Juego.getInstancia().comprarEscudoMaximo(cantidad);
        } catch (Exception e) {
            mostrarError(e);
        }
    }

    public static void comprarCombustible(double cantidad) {
        try {
            Juego.getInstancia().comprarCombustible(cantidad);
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
