package org.example._MVC.Model.GameMaster.Exception;

import org.example._MVC.Controller.MainController;

public class TesoroEncontradoException extends Exception {

    public TesoroEncontradoException(String mensaje) {
        MainController.cerrarApp(5000);
        super(mensaje);
    }
}