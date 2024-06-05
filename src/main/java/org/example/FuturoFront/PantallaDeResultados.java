package org.example.FuturoFront;

import org.example.Views.MessageView;

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
        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
