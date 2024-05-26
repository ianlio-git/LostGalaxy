package org.example.mapaEstelar.sistemas.planetas;

import org.example.enums.Acciones;
import org.example.enums.TipoDePlaneta;
import org.example.gameMaster.Jugador;

public abstract class Planeta {
    private String codigoDePlaneta;
    public Planeta(String codigoDePlaneta) {
        this.codigoDePlaneta = codigoDePlaneta;
    }
    public String getCodigoDePlaneta() {
        return codigoDePlaneta;
    }
    public abstract TipoDePlaneta soyPlanetaTipo();
    public abstract void realizarAccionEnMercado(Acciones accion, Jugador jugador, double cantidad);
    public abstract void combate();


}