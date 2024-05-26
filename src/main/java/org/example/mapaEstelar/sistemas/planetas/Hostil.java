package org.example.mapaEstelar.sistemas.planetas;

import org.example.enums.Acciones;
import org.example.enums.TipoDePlaneta;
import org.example.gameMaster.Jugador;
import org.example.nave.NaveAliada;
import org.example.nave.tiposDeNaves.NavePirata;

public class Hostil extends Planeta {
    private static int count;
    private boolean tesoro;
    private NavePirata naveEnemiga;

    public Hostil( boolean tesoro, NavePirata naveEnemiga) {
        super("ENE-"+count++);
        this.tesoro = tesoro;
        this.naveEnemiga = naveEnemiga;
    }
    @Override
    public TipoDePlaneta soyPlanetaTipo() {
        return TipoDePlaneta.HOSTIL;
    }

    @Override
    public void realizarAccionEnMercado(Acciones accion, Jugador jugador, double cantidad) {
        System.out.println("Un planeta Hostil no tiene Mercado");
    }

    public void combate(Jugador jugador) {
        double acumDeDanio =0;
        while (jugador.getNave().tengoVida()&& naveEnemiga.tengoVida()) {
            while(jugador.getNave().tengoEscudo()&& naveEnemiga.tengoVida()){
                jugador.getNave().quitarEscudo(naveEnemiga.poderAtaque());
                naveEnemiga.quitarVida(jugador.getNave().poderAtaque());
                acumDeDanio+=naveEnemiga.poderAtaque();
            }
            if(naveEnemiga.tengoVida()) {
                jugador.getNave().quitarVida(naveEnemiga.poderAtaque());
                naveEnemiga.quitarVida(jugador.getNave().poderAtaque());
                acumDeDanio+=naveEnemiga.poderAtaque();
            }
        }
        if(!naveEnemiga.tengoVida()) {
            asignarRecomprensas(jugador,acumDeDanio);
        }
    }
    private void asignarRecomprensas(Jugador jugador,double acumDeDanio){
        jugador.agregarUadeCoins(2*naveEnemiga.poderAtaque()-acumDeDanio);
        jugador.encontreElTesoro(this.tesoro);
    }
}