package org.example.mapaEstelar.sistemas;

import org.example.gameMaster.Jugador;

public class CinturonAsteroide {
    private int cantidadDeAsteroides;

    public CinturonAsteroide(int cantidadDeAsteroides) {
        this.cantidadDeAsteroides = cantidadDeAsteroides;
    }

    public int getCantidadDeAsteroides() {
        return cantidadDeAsteroides;
    }

    public double calcularPoder() {
        return cantidadDeAsteroides * 10;
    }

    public void atravesar(Jugador jugador) {
        double dañoRecibido = this.calcularPoder();
        jugador.getNave().setVida(0-dañoRecibido);
        double uadeCoinsGanados = this.calcularPoder() - dañoRecibido;
        jugador.agregarUadeCoins(uadeCoinsGanados);
    }
}