package org.example.futuraPantalla;

import org.example.Views.GameBeginView;
import org.example.Views.JugadorView;

public class PantallaTurno {
    GameBeginView gameBeginView;

    public PantallaTurno(GameBeginView gameBeginView) {
        this.gameBeginView = gameBeginView;
    }

    public void mostrarTurno(){
        System.out.println("---------------------------------------------------------------");
        System.out.println("Turno: " + gameBeginView.getTurno());
        System.out.println("---------------------------------------------------------------");
    }
}
