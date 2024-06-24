package org.example._MVC.Controller;

import org.example.Enums.TipoDeArma;
import org.example.GameMaster.Juego;
import org.example._MVC.PantallasOK.PantallasInfomacion.PantallaDeResultados;
import org.example._MVC.Views.MessageView;

public class PlanetaNeutralController {

    public static boolean comprarArma(TipoDeArma tipoDeArma) {
        try {
            Juego.getInstancia().comprarArma(tipoDeArma);
            return true;
        } catch (Exception e) {
            mostrarError(e);
            return false;
        }
    }

    public static boolean venderArma(TipoDeArma tipoDeArma) {
        try {
            Juego.getInstancia().venderArma(tipoDeArma);
            return true;
        } catch (Exception e) {
            mostrarError(e);
            return false;
        }
    }

    public static boolean recargarEscudo(double cantidad) {
        try {
            Juego.getInstancia().recargarEscudo(cantidad);
            return true;
        } catch (Exception e) {
            mostrarError(e);
            return false;
        }
    }

    public static boolean comprarEscudoMaximo(double cantidad) {
        try {
            Juego.getInstancia().comprarEscudoMaximo(cantidad);
            return true;
        } catch (Exception e) {
            mostrarError(e);
            return false;
        }
    }

    public static boolean comprarCombustible(double cantidad) {
        try {
            Juego.getInstancia().comprarCombustible(cantidad);
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
