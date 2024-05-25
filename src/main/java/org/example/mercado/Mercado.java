package org.example.mercado;

import org.example.gameMaster.Jugador;
import org.example.mercado.accionesMercado.*;

public class Mercado {
    public void realizarAccion(AccionMercado accionMercado, Jugador jugador) {
        accionMercado.realizar(jugador);
    }
}

