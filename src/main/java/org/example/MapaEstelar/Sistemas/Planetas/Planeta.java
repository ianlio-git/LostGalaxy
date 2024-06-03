package org.example.MapaEstelar.Sistemas.Planetas;

import org.example.Enums.Acciones;
import org.example.Enums.TipoDeCuerpoCeleste;
import org.example.GameMaster.Jugador;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;

public abstract class Planeta {
    private String codigoDePlaneta;
    public Planeta(String codigoDePlaneta) {
        this.codigoDePlaneta = codigoDePlaneta;
    }
    public String getCodigoDePlaneta() {
        return codigoDePlaneta;
    }
    public abstract TipoDeCuerpoCeleste soyPlanetaTipo();
    public abstract void realizarAccionEnMercado(Acciones accion, Jugador jugador, double cantidad);
    public abstract void combate(Jugador jugador);
    public abstract void repararNaveAliada(Jugador jugador);
    public abstract void obtenerInformacion(SistemaEstelar sistemaEstelar, Jugador jugador);

}