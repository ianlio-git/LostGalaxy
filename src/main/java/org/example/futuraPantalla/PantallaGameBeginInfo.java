package org.example.futuraPantalla;

import org.example.Views.GameBeginView;

public class PantallaGameBeginInfo {
    GameBeginView gameBeginView;

    public PantallaGameBeginInfo(GameBeginView gameBeginView) {
        this.gameBeginView = gameBeginView;
    }

    public void mostrarDatosDeInicio() {
        // Después esto se va a hacer con la pantalla introduciendo datos, todavía falta.
        System.out.println("=============================================");
        System.out.println("              - Datos de inicio -            ");
        System.out.println("=============================================");
        System.out.println("Nombre del Jugador:          " + gameBeginView.getNombreDelJugador());
        System.out.println("UADE Coins del Jugador:      " + gameBeginView.getUadeCoinsJugador());
        System.out.println("Nave del Jugador:            " + gameBeginView.getNaveJugador());
        System.out.println("Cantidad de Sistemas Estelares: " + gameBeginView.getCantidadSistemasEstelares());
        System.out.println("Dificultad:                  " + gameBeginView.getDificultad());
        System.out.println("=============================================");
    }

}
