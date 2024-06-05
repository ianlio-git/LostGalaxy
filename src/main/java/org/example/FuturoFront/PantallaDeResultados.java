package org.example.FuturoFront;

import org.example.Views.MessageView;

public class PantallaDeResultados {

    MessageView messageView;

    public PantallaDeResultados(MessageView messageView) {
        this.messageView = messageView;
    }

    public void mostrarMensaje(){
        System.out.println(messageView.getMessage());
    }
}
