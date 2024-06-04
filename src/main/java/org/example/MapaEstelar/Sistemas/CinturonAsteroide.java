package org.example.MapaEstelar.Sistemas;

import org.example.Enums.TipoDeNave;
import org.example.GameMaster.Jugador;

public class CinturonAsteroide {
    private int cantidadDeAsteroides;

    public CinturonAsteroide(int cantidadDeAsteroides) {
        this.cantidadDeAsteroides = cantidadDeAsteroides;
    }


    public double calcularPoder() {
        return this.cantidadDeAsteroides * 10;
    }

    public void atravesar(Jugador jugador) {
        System.out.println("¡CUIDADO! Atravesando cinturón de asteroides.....");
        if (jugador.getNave().soyNaveTipo() != TipoDeNave.NAVE_PHANTOM) {
            double vidaInicial = jugador.getNave().getVida();
            jugador.getNave().recibirGolpe(this.calcularPoder());

            if (jugador.getNave().tengoVida()) {
                double uadeCoinsConseguidos = calcularRecompensa(vidaInicial, jugador.getNave().getVida());
                System.out.println("¡Conseguiste atravesar el cinturón!");
                System.out.println("Ganaste: " + uadeCoinsConseguidos + " UADEcoins");
                jugador.agregarUadeCoins(uadeCoinsConseguidos);

                if (!jugador.getNave().getTanque().tengoCombustible()) {
                    jugador.setPlanetaActual(null);
                }
            }
        }
    }

    private double calcularRecompensa(double vidaInicial,double vidaFinal){
        return this.calcularPoder() - (vidaInicial-vidaFinal);
    }
}