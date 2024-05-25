package org.example.mapaEstelar.sistemas.planetas;

import org.example.gameMaster.Jugador;
import org.example.enums.TipoDePlaneta;

public class Aliado extends Planeta {
    private static int count;

    public Aliado() {
        super("ALI-" + count++);
    }

    @Override
    public TipoDePlaneta soyPlanetaTipo() {
        return TipoDePlaneta.ALIADO;
    }

    public void realizarAccionAliada(Jugador jugador) {

    }
}
