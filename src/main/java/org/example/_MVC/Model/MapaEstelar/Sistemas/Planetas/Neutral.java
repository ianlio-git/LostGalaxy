package org.example._MVC.Model.MapaEstelar.Sistemas.Planetas;

import org.example._MVC.Model.GameMaster.Jugador;
import org.example._MVC.Model.Enums.TipoDeCuerpoCeleste;
import org.example._MVC.Model.Mercado.Mercado;
import org.example._MVC.Model.Nave.TiposDeNaves.NavePirata;

public class Neutral extends Planeta {
    private static int count;
    private Mercado mercado;

    public Neutral() {
        super("NEU-"+ count++);
        this.mercado = new Mercado();
    }

    @Override
    public NavePirata getNavePirata() {
        return null;
    }

    @Override
    public TipoDeCuerpoCeleste soyPlanetaTipo() {
        return TipoDeCuerpoCeleste.PLANETA_NEUTRAL;
    }

    @Override
    public Mercado ingresarAlMercado() {
        return mercado;
    }

    @Override
    public void combate(Jugador jugador) {
        System.out.println("Un planeta Neutral no puede combatir");
    }

    @Override
    public void repararNaveAliada(Jugador jugador) {
        System.out.println("Debes pagar para realizar estas acciones aqui");
    }

    @Override
    public void obtenerInformacion(Jugador jugador) {
        System.out.println("No se puede obtener informacion en un planeta neutral");
    }



}