package org.example.Mercado;

import org.example.Enums.TipoDeArma;
import org.example.GameMaster.Jugador;
import org.example.Nave.PartesDeLaNave.Arma;

public class Mercado {
    private Arma arma;
    public Mercado() {
    }

    public void comprarEscudo(Jugador jugador, double cantidadDeEscudoMaximo) {
        System.out.println("¡Bienvenido a la tienda de escudo!");
        if (jugador.puedoComprar(cantidadDeEscudoMaximo)) {
            jugador.getNave().getEscudo().agregarEscudoMaximo(cantidadDeEscudoMaximo);
            System.out.println("¡Has comprado " + cantidadDeEscudoMaximo + " de escudo maximo para tu nave!");
            jugador.quitarUadeCoins(cantidadDeEscudoMaximo);
        } else {
            System.out.println("No tienes suficientes uadeCoins.");
        }
    }
    public  void recargarEscudo(Jugador jugador, double cantidadDeEscudo){
        System.out.println("¡Bienvenido a la tienda de escudo!");
        if (jugador.puedoComprar(cantidadDeEscudo)) {
            jugador.getNave().getEscudo().agregarEscudo(cantidadDeEscudo);
            System.out.println("¡Has comprado " + cantidadDeEscudo + " de escudo para tu nave!");
            jugador.quitarUadeCoins(cantidadDeEscudo);
        } else {
            System.out.println("No tienes suficientes uadeCoins.");
        }
    }

    public void comprarCombustible(Jugador jugador, double cantidadDeCombustible) {
        System.out.println("¡Bienvenido a YPF!");
        if (jugador.getNave().getTanque().tanqueLleno()) {
            System.out.println("Combustible lleno");
        } else {
            if (jugador.puedoComprar(cantidadDeCombustible)) {
                double combustibleCargado = jugador.getNave().getTanque().cargarCombustible(cantidadDeCombustible);
                System.out.println("¡Has agregado " + combustibleCargado + " de combustible a la nave!");
                jugador.quitarUadeCoins(combustibleCargado);
            } else {
                System.out.println("No tienes suficientes uadeCoins.");
            }
        }
    }

    public void comprarArma(Jugador jugador, TipoDeArma tipoDeArma) {
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
        comprarArma(arma, jugador);
    }

    private void comprarArma(Arma arma, Jugador jugador) {
        if (jugador.puedoComprar(arma.getPrecio()) && jugador.getNave().limiteDeArmas()) {
            jugador.getNave().agregarArma(arma);
            jugador.quitarUadeCoins(arma.getPrecio());
            System.out.println("Usted ha comprado un: " + arma.soyTipoDeArma());
        } else {
            System.out.println("No tienes suficientes uade coins o alcanzaste tu límite de armas");
        }
    }

    public void venderArma(Jugador jugador, TipoDeArma tipoDeArma) {
        if (jugador.getNave().tengoEsaArma(tipoDeArma)){
            double venta = jugador.getNave().elegirMiArma(tipoDeArma).getPrecio();
            jugador.agregarUadeCoins(venta);
            jugador.getNave().quitarArma(tipoDeArma);
        }else{
            System.out.println("no podes vender un arma que no tenes!");
        }
    }
}

