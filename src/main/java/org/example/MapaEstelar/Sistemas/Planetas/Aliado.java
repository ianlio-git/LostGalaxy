package org.example.MapaEstelar.Sistemas.Planetas;

import org.example.GameMaster.Exception.UadeCoinsInsuficientesException;
import org.example.GameMaster.Jugador;
import org.example.Enums.TipoDeCuerpoCeleste;
import org.example.MapaEstelar.MapaEstelar;
import org.example.Mercado.Mercado;

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
