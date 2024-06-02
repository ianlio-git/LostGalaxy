package org.example.mapaEstelar.sistemas.planetas;

import org.example.enums.Acciones;
import org.example.enums.TipoDeCuerpoCeleste;
import org.example.gameMaster.Jugador;
import org.example.mapaEstelar.sistemas.SistemaEstelar;
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
    public TipoDeCuerpoCeleste soyPlanetaTipo() {
        return TipoDeCuerpoCeleste.PLANETA_HOSTIL;
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
        if(!naveEnemiga.tengoVida()&&jugador.getNave().tengoVida()) {
            asignarRecomprensas(jugador,acumDeDanio);
            resultadoCombate();
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


    private void resultadoCombate(){
        System.out.println("La nave enemiga fue destruida. ¡Muy bien!");
        if (this.tesoro){
            System.out.println("¡Felicidades, has encontrado el tesoro y has ganado el juego!");
        }else{
            System.out.println("Por desgracia no has encontrado el tesoro, sigue intentado!");
        }
    }

}