package org.example.mapaEstelar.sistemas.planetas;

import org.example.enums.Acciones;
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

    @Override
    public void realizarAccionEnMercado(Acciones accion, Jugador jugador, double cantidad) {
        System.out.println("Un planeta Aliado no tiene Mercado");
    }

    @Override
    public void combate(Jugador jugador) {
        System.out.println("Un planeta Aliado no tiene puede combatir");
    }

    public void realizarAccionAliada(Jugador jugador) {

    }
}
