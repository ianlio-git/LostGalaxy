package org.example.FuturoFront;

import org.example.Views.GameBeginView;

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
