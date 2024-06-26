package org.example._MVC.PantallasOK.PantallasInfomacion;

import org.example._MVC.Model.GameMaster.Exception.NaveDestruidaException;
import org.example._MVC.Views.MessageView;

import javax.swing.*;

public class PantallaDeResultados {

    MessageView messageView;

    public PantallaDeResultados(MessageView messageView) {
        this.messageView = messageView;
    }

    public void mostrarMensaje(){
        System.out.println(messageView.getMessage());
    }

    public void salirDelJuego(Exception e) {
        String message = e.getMessage();
        String title = "Vitacora del viaje";

        switch (e.getClass().getSimpleName()) {
            case "ArmaNoEncontradaException":
            case "PlanetaNoEncontradoException":
            case "NavePhantomException":
            case "SistemaInvalidoException":
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
                break;

            case "AtacarSoloPlanetasHostiles":
            case "AtravesandoCinturonException":
            case "CambianDeSistemaException":
            case "JuegoIniciadoException":
            case "SinCombustibleEnCinturonDeAsteroides":
            case "SinCombustibleNiUadeCoins":
            case "UadeCoinsInsuficientesException":
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
                break;

            case "CombustibleInsuficienteException":
            case "EscudoLleno":
            case "LimiteDeArmas":
            case "TanqueLlenoException":
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
                break;

            case "NaveDestruidaException":
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
                break;

            case "NaveEnemigaDestruidaException":
            case "TesoroEncontradoException":
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
                break;

            default:
                JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
                break;
        }
    }
}
