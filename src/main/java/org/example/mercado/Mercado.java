package org.example.mercado;

import org.example.enums.TipoDeArma;
import org.example.gameMaster.Jugador;
import org.example.partesDeLaNave.Arma;
import org.example.partesDeLaNave.Armas.*;

public class Mercado {
    private Arma arma;
    public Mercado() {
    }

    public void comprarEscudo(Jugador jugador, double cantidadDeEscudo) {
        System.out.println("¡Bienvenido a la tienda de escudo!");
        if (jugador.getUadeCoins() > cantidadDeEscudo) {
            jugador.getNave().agregarEscudo(cantidadDeEscudo);
            System.out.println("¡Has comprado " + cantidadDeEscudo + " de escudo para tu nave!");
            jugador.quitarUadeCoins(cantidadDeEscudo);
        } else {
            System.out.println("No tienes suficientes uadeCoins.");
        }
    }

    public void comprarCombustible(Jugador jugador, double cantidadDeCombustible) {
        System.out.println("¡Bienvenido a YPF!");

        if (jugador.getNave().getCombustible() == 100) {
            System.out.println("Combustible lleno");
        } else {
            double combustibleFaltante = 100 - jugador.getNave().getCombustible();
            if (jugador.getUadeCoins() > combustibleFaltante) {
                jugador.getNave().llenarTanqueDeCombustible(combustibleFaltante);
                System.out.println("¡Has agregado " + combustibleFaltante + " de combustible a la nave!");
                jugador.quitarUadeCoins(combustibleFaltante);
            } else {
                System.out.println("No tienes suficientes uadeCoins.");
            }
        }
    }

    public void comprarArma(Jugador jugador, TipoDeArma tipoDeArma) {
        switch (tipoDeArma) {
            case TipoDeArma.CAÑON_DE_IONES:
                arma = new CañonDeIones();
                break;
            case TipoDeArma.LASER_DE_FUSION:
                arma = new LaserDeFusion();
                break;
            case TipoDeArma.MISIL_DE_ANTIMATERIA:
                arma = new MisilDeAntiMateria();
                break;
            case TipoDeArma.CAÑON_DE_PARTICULAS:
                arma = new CañonDeParticulas();
                break;
            case TipoDeArma.CAÑON_GAUSS:
                arma = new CañonGauss();
                break;
            default:
                throw new IllegalArgumentException("Número de arma no válida.");
        }
        validarCoins(arma, jugador);
    }

    private void validarCoins(Arma arma, Jugador jugador) {
        if (jugador.getUadeCoins() > arma.getPrecio() && jugador.getNave().limiteDeArmas()) {
            jugador.getNave().agregarArma(arma);
            jugador.quitarUadeCoins(arma.getPrecio());
            System.out.println("Usted ha comprado un: " + arma.soyTipoDeArma());
        } else {
            System.out.println("No tienes suficientes uade coins o alcanzaste tu límite de armas");
        }
    }

    public void venderArma(Jugador jugador, TipoDeArma tipoDeArma) {
        if (jugador.getNave().elegirMiArma(tipoDeArma)!= null){
            double venta = jugador.getNave().elegirMiArma(tipoDeArma).getPrecio();
            jugador.agregarUadeCoins(venta);
            jugador.getNave().quitarArma(tipoDeArma);
        }else{
            System.out.println("no podes vender un arma que no tenes!");
        }
    }
}

