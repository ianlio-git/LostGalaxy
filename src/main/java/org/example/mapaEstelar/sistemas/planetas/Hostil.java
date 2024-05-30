package org.example.mapaEstelar.sistemas.planetas;

import org.example.enums.Acciones;
import org.example.enums.TipoDePlaneta;
import org.example.gameMaster.Jugador;
import org.example.mapaEstelar.MapaEstelar;
import org.example.mapaEstelar.sistemas.SistemaEstelar;
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
            jugador.getNave().recibirGolpe(naveEnemiga.poderAtaque());
            naveEnemiga.quitarVida(jugador.getNave().poderAtaque());
            jugador.getNave().updateVida();
            acumDeDanio+=naveEnemiga.poderAtaque();
        }
        if(!naveEnemiga.tengoVida()) {
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
    public void obtenerInformacion(SistemaEstelar sistemaEstelar, Jugador jugador) {
        System.out.println("No puedes obtener informacion aqui.");
    }

    @Override
    public double combustibleNecesario() {
        return 20;
    }

}