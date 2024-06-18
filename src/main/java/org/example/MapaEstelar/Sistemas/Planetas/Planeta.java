package org.example.MapaEstelar.Sistemas.Planetas;

import org.example.Enums.TipoDeCuerpoCeleste;
import org.example.GameMaster.Jugador;
import org.example.Mercado.Mercado;

public abstract class Planeta {
    private String codigoDePlaneta;
    public Planeta(String codigoDePlaneta) {
        this.codigoDePlaneta = codigoDePlaneta;
    }
    public String getCodigoDePlaneta() {
        return codigoDePlaneta;
    }
    public abstract TipoDeCuerpoCeleste soyPlanetaTipo();
    public abstract Mercado ingresarAlMercado();
    public abstract void combate(Jugador jugador);
    public abstract void repararNaveAliada(Jugador jugador);
    public abstract String obtenerInformacion( Jugador jugador);

}