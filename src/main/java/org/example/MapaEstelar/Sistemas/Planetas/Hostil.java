package org.example.MapaEstelar.Sistemas.Planetas;

import org.example.Enums.Acciones;
import org.example.Enums.TipoDeCuerpoCeleste;
import org.example.GameMaster.Jugador;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example.Mercado.Mercado;
import org.example.Nave.TiposDeNaves.NavePirata;

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
    public TipoDeCuerpoCeleste soyPlanetaTipo() {
        return TipoDeCuerpoCeleste.PLANETA_HOSTIL;
    }

    @Override
    public Mercado ingresarAlMercado() {
        return null;
    }

    public void combate(Jugador jugador) {
        double acumDeDanio =0;
        while (jugador.getNave().tengoVida()&& naveEnemiga.tengoVida()) {
            jugador.getNave().recibirGolpe(naveEnemiga.poderAtaque());
            naveEnemiga.quitarVida(jugador.getNave().poderAtaque());
            jugador.getNave().updateVida();
            acumDeDanio+=naveEnemiga.poderAtaque();
        }
        if(!naveEnemiga.tengoVida()&&jugador.getNave().tengoVida()) {
            asignarRecomprensas(jugador,acumDeDanio);

        }
    }

    @Override
    public void repararNaveAliada(Jugador jugador) {
        System.out.println("Aca haces todo menos reparar master");
    }

    private void asignarRecomprensas(Jugador jugador,double acumDeDanio){
        jugador.agregarUadeCoins(2*naveEnemiga.poderAtaque()-acumDeDanio);
        jugador.encontreElTesoro(this.tesoro);
    }
    @Override
    public String obtenerInformacion(SistemaEstelar sistemaEstelar, Jugador jugador) {
        return "No puedes obtener informacion aqui.";
    }




}