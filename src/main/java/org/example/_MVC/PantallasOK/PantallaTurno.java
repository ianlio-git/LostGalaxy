package org.example._MVC.PantallasOK;

import org.example._MVC.Views.GameBeginView;

public class PantallaTurno {
    GameBeginView gameBeginView;

    public PantallaTurno(GameBeginView gameBeginView) {
        this.gameBeginView = gameBeginView;
    }

    public void mostrarTurno(){
        System.out.println("=============================================");
        System.out.println("Turno: " + gameBeginView.getTurno());
        System.out.println("=============================================");
    }
}
