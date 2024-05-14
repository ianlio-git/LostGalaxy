package org.example.mercado;

import org.example.Jugador;
import org.example.mercado.accionesMercado.*;

public class Mercado {

    public void realizarAccionMercado(AccionMercado accion, Jugador jugador) {
        accion.realizarAccion(jugador);
    }
}
