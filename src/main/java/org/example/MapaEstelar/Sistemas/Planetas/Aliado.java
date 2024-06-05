package org.example.MapaEstelar.Sistemas.Planetas;

import org.example.GameMaster.Jugador;
import org.example.Enums.TipoDeCuerpoCeleste;
import org.example.MapaEstelar.MapaEstelar;
import org.example.Mercado.Mercado;

public class Aliado extends Planeta {
    private MapaEstelar mapaEstelar;
    private static int count;

    public Aliado() {
        super("ALI-" + count++);
        this.mapaEstelar = MapaEstelar.getInstancia();
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


    public String obtenerInformacion( Jugador jugador){
        if(jugador.puedoComprar(4000)){
            jugador.quitarUadeCoins(4000);
            return ("El sistema estelar que posee el tesoro es el:"+mapaEstelar.getSistemaConTesoro());
        }
        else {
            return "Reune mas uade coins para obtener esa informacion!";
        }
    }
}
