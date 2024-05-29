package org.example.mapaEstelar.sistemas.planetas;

import org.example.enums.Acciones;
import org.example.enums.TipoDePlaneta;
import org.example.gameMaster.Jugador;
import org.example.mapaEstelar.MapaEstelar;
import org.example.mapaEstelar.sistemas.SistemaEstelar;

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
    public abstract void combate(Jugador jugador);
    public abstract void repararNaveAliada(Jugador jugador);
    public abstract void obtenerInformacion(SistemaEstelar sistemaEstelar, Jugador jugador);

}