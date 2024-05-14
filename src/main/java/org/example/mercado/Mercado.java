package org.example.mercado;

import org.example.Jugador;
import org.example.mercado.accionesMercado.*;

public class Mercado {
    private AccionMercado comprarCombustibleAccion = new ComprarCombustibleAccion();
    private AccionMercado comprarEscudoAccion = new ComprarEscudoAccion();
    private AccionMercado comprarArmaAccion = new ComprarArmaAccion();
    private AccionMercado venderArmaAccion = new VenderArmaAccion();

    // Método para realizar una acción del mercado
    public void realizarAccionMercado(AccionMercado accion, Jugador jugador) {
        accion.realizarAccion(jugador);
    }
}
