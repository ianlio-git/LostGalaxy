package org.example.Mercado;

import org.example.Enums.TipoDeArma;
import org.example.GameMaster.Jugador;
import org.example.Nave.PartesDeLaNave.Arma;

public class Mercado {
    private Arma arma;
    public Mercado() {
    }

    public boolean accionDeComprarEscudoMaximo(Jugador jugador, double cantidadDeEscudoMaximo) {
        boolean pudeComprarEscudoMax = false;
        if (jugador.puedoComprar(cantidadDeEscudoMaximo)) {
            jugador.getNave().getEscudo().agregarEscudoMaximo(cantidadDeEscudoMaximo);
            jugador.quitarUadeCoins(cantidadDeEscudoMaximo);
            pudeComprarEscudoMax = true;
        }
        return pudeComprarEscudoMax;
    }
    public  boolean accionDeRecargarEscudo(Jugador jugador, double cantidadDeEscudo){
        boolean pudeRecargarEscudo = false;
        if (jugador.puedoComprar(cantidadDeEscudo)) {
            jugador.getNave().getEscudo().agregarEscudo(cantidadDeEscudo);
            jugador.quitarUadeCoins(cantidadDeEscudo);
            pudeRecargarEscudo = true;
        }
        return pudeRecargarEscudo;
    }

    public boolean accionDeComprarCombustible(Jugador jugador, double cantidadDeCombustible) {
        boolean pudeComprarCombustible = false;
        if (jugador.puedoComprar(cantidadDeCombustible)) {
                double combustibleCargado = jugador.getNave().getTanque().cargarCombustible(cantidadDeCombustible);
                jugador.quitarUadeCoins(combustibleCargado);
                pudeComprarCombustible = true;
            }
        return pudeComprarCombustible;
    }

    public boolean accionDeComprarArma(Jugador jugador, TipoDeArma tipoDeArma) {
        boolean pudeComprar = false;
        switch (tipoDeArma) {
            case TipoDeArma.CAÑON_DE_IONES:
                arma = new Arma(tipoDeArma,150,5);
                break;
            case TipoDeArma.LASER_DE_FUSION:
                arma = new Arma(tipoDeArma,300,10);
                break;
            case TipoDeArma.MISIL_DE_ANTIMATERIA:
                arma = new Arma(tipoDeArma,450,15);
                break;
            case TipoDeArma.CAÑON_DE_PARTICULAS:
                arma = new Arma(tipoDeArma,600,20);
                break;
            case TipoDeArma.CAÑON_GAUSS:
                arma = new Arma(tipoDeArma,750,25);
                break;
            default:
                throw new IllegalArgumentException("Número de arma no válida.");
        }
        if (jugador.puedoComprar(arma.getPrecio()) && jugador.getNave().limiteDeArmas()) {
            jugador.getNave().agregarArma(arma);
            jugador.quitarUadeCoins(arma.getPrecio());
            System.out.println("Usted ha comprado un: " + arma.soyTipoDeArma());
            pudeComprar = true;
        }
        return pudeComprar;
    }


    public boolean accionDeVenderArma(Jugador jugador, TipoDeArma tipoDeArma) {
        boolean pudeVenderElArma = false;
        if (jugador.getNave().tengoEsaArma(tipoDeArma)){
            double venta = jugador.getNave().elegirMiArma(tipoDeArma).getPrecio();
            jugador.agregarUadeCoins(venta);
            pudeVenderElArma = jugador.getNave().quitarArma(tipoDeArma);
        }
        return pudeVenderElArma;
    }
}

