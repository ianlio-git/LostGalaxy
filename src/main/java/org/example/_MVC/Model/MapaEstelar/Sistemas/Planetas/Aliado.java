package org.example._MVC.Model.MapaEstelar.Sistemas.Planetas;

import org.example._MVC.Model.GameMaster.Exception.UadeCoinsInsuficientesException;
import org.example._MVC.Model.GameMaster.Jugador;
import org.example._MVC.Model.Enums.TipoDeCuerpoCeleste;
import org.example._MVC.Model.MapaEstelar.MapaEstelar;
import org.example._MVC.Model.Mercado.Mercado;
import org.example._MVC.Model.Nave.TiposDeNaves.NavePirata;

public class Aliado extends Planeta {
    private MapaEstelar mapaEstelar;
    private static int count;
    private static double costeDeInformacion;

    public Aliado() {
        super("ALI-" + count++);
        this.mapaEstelar = MapaEstelar.getInstancia();
        costeDeInformacion = 4000;
    }

    @Override
    public NavePirata getNavePirata() {
        return null;
    }

    @Override
    public TipoDeCuerpoCeleste soyPlanetaTipo() {
        return TipoDeCuerpoCeleste.PLANETA_ALIADO;
    }

    @Override
    public Mercado ingresarAlMercado() {
        return null;
    }

    @Override
    public void combate(Jugador jugador) {
        System.out.println("Un planeta Aliado no tiene puede combatir");
    }

    public void repararNaveAliada(Jugador jugador) {
        jugador.getNave().reestablecerVida();
        jugador.getNave().getEscudo().restablecerEscudo();
    }


    public void obtenerInformacion( Jugador jugador) throws UadeCoinsInsuficientesException {

        if(jugador.puedoComprar(costeDeInformacion)){
            jugador.quitarUadeCoins(costeDeInformacion);
        }
        else {
            throw new UadeCoinsInsuficientesException("No tienes suficiente uade coins para obtener informacion.");
        }
    }
}
