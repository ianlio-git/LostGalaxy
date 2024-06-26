package org.example._MVC.Model.GameMaster.Exception;

import org.example._MVC.Controller.MainController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import static java.lang.System.exit;

public class NaveDestruidaException extends Exception {
    public NaveDestruidaException(String mensaje) {
        MainController.cerrarApp(5000);
        super(mensaje);
    }
}