package org.example._MVC.PantallasOK.PantallasInfomacion;

import org.example.GameMaster.Exception.NaveDestruidaException;
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
        if (e instanceof NaveDestruidaException) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Vitacora del viaje", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Vitacora del viaje", JOptionPane.WARNING_MESSAGE);
        }
    }

}
