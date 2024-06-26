package org.example.MapaEstelar.Sistemas.Planetas;

import org.example.Enums.TipoDeCuerpoCeleste;
import org.example.GameMaster.Exception.NaveDestruidaException;
import org.example.GameMaster.Exception.TesoroEncontradoException;
import org.example.GameMaster.Exception.UadeCoinsInsuficientesException;
import org.example.GameMaster.Jugador;
import org.example.Mercado.Mercado;
import org.example.Nave.TiposDeNaves.NavePirata;

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
    public abstract void combate(Jugador jugador) throws NaveDestruidaException, TesoroEncontradoException;
    public abstract void repararNaveAliada(Jugador jugador);
    public abstract void obtenerInformacion( Jugador jugador) throws UadeCoinsInsuficientesException;
    public abstract NavePirata getNavePirata();
}