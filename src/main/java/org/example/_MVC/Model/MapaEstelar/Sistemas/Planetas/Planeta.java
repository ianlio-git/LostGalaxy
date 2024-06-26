package org.example._MVC.Model.MapaEstelar.Sistemas.Planetas;

import org.example._MVC.Model.Enums.TipoDeCuerpoCeleste;
import org.example._MVC.Model.GameMaster.Exception.NaveDestruidaException;
import org.example._MVC.Model.GameMaster.Exception.TesoroEncontradoException;
import org.example._MVC.Model.GameMaster.Exception.UadeCoinsInsuficientesException;
import org.example._MVC.Model.GameMaster.Jugador;
import org.example._MVC.Model.Mercado.Mercado;
import org.example._MVC.Model.Nave.TiposDeNaves.NavePirata;

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