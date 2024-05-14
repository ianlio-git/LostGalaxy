package org.example.mercado;

import org.example.Jugador;
import org.example.enums.Dificultades;
import org.example.mercado.accionesMercado.*;
import org.example.nave.tiposDeNaves.NavePirata;
import org.example.partesDeLaNave.Arma;
import org.example.partesDeLaNave.Item;
import org.example.planeta.Hostil;
import org.example.planeta.Neutral;

import java.util.List;
import java.util.Random;

public class Mercado {
    private AccionMercado comprarCombustibleAccion = new ComprarCombustibleAccion();
    private AccionMercado comprarEscudoAccion = new ComprarEscudoAccion();
    private AccionMercado comprarArmaAccion = new ComprarArmaAccion();
    private AccionMercado venderArmaAccion = new VenderArmaAccion();
    private List<Item> items;


    // Método para realizar una acción del mercado
    public void realizarAccionMercado(AccionMercado accion, Jugador jugador) {
        accion.realizarAccion(jugador);
    }


}
