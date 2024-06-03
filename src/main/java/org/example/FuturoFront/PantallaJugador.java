package org.example.FuturoFront;

import org.example.Views.JugadorView;

public class PantallaJugador {

    JugadorView jugadorView;

    public PantallaJugador(JugadorView jugadorView) {
        this.jugadorView = jugadorView;
    }

    public void mostrarDatosDelJugador() {
        System.out.println("===============================================================");
        mostrarPosicion();
        mostrarDatosJugador();
        mostrarStats();
        mostrarArmas();
        System.out.println("===============================================================");
    }

    private void mostrarPosicion() {
        System.out.println("--Posición--");
        System.out.println("Dentro del sistema: " + jugadorView.getSistemaActual().mostrarNombre());
        if (jugadorView.getPlanetaActual() == null) {
            System.out.println("Estoy en el cinturón de asteroides");
        } else {
            System.out.println("Estoy en el planeta: " + jugadorView.getPlanetaActual());
        }
    }

    private void mostrarDatosJugador() {
        System.out.println("--Player--");
        System.out.println("Nombre: " + jugadorView.getNombre());
        System.out.println("Cantidad de uade coins: " + jugadorView.getUadeCoins());
        System.out.println("Mi nave es: " + jugadorView.getNave().soyNaveTipo());
    }

    private void mostrarStats() {
        System.out.println("--Stats--");
        System.out.println("Vida: " + jugadorView.getNave().getVida());
        System.out.println("Escudo Actual: " + jugadorView.getNave().getEscudo().cantidadEscudoActual());
        System.out.println("Escudo Máximo: " + jugadorView.getNave().getEscudo().cantidadEscudoMax());
        System.out.println("Combustible: " + jugadorView.getNave().getTanque().getCombustible());
    }

    private void mostrarArmas() {
        System.out.println("--Weapons--");
        if (jugadorView.getNave().tengoArmas()) {
            System.out.println("Mis armas son:");
            jugadorView.getNave().getArmas();
        } else {
            System.out.println("Aún no tengo un arma, ¡pero ya tendré una!");
        }
        System.out.println("Poder: " + jugadorView.getNave().poderAtaque());
    }
}

