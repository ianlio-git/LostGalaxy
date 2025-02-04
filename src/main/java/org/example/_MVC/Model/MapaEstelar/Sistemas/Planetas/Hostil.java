package org.example._MVC.Model.MapaEstelar.Sistemas.Planetas;

import org.example._MVC.Model.Enums.TipoDeCuerpoCeleste;
import org.example._MVC.Model.GameMaster.Exception.NaveDestruidaException;
import org.example._MVC.Model.GameMaster.Exception.TesoroEncontradoException;
import org.example._MVC.Model.GameMaster.Jugador;
import org.example._MVC.Model.Mercado.Mercado;
import org.example._MVC.Model.Nave.TiposDeNaves.NavePirata;

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

    public NavePirata getNavePirata(){
        return naveEnemiga;
    }

    public void combate(Jugador jugador) throws NaveDestruidaException, TesoroEncontradoException {
        double acumDeDanio =0;
        while (jugador.getNave().tengoVida()&& naveEnemiga.tengoVida()) {
            jugador.getNave().recibirGolpe(naveEnemiga.poderAtaque());
            naveEnemiga.quitarVida(jugador.getNave().poderAtaque());
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

    private void asignarRecomprensas(Jugador jugador,double acumDeDanio) throws TesoroEncontradoException {
        jugador.agregarUadeCoins(2*naveEnemiga.poderAtaque()-acumDeDanio);
        jugador.encontreElTesoro(this.tesoro);
    }
    @Override
    public void obtenerInformacion(Jugador jugador) {
        System.out.println("No se puede hacer eso en un planeta hostil");
    }




}