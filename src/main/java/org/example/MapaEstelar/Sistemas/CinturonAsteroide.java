package org.example.MapaEstelar.Sistemas;

import org.example.Enums.TipoDeNave;
import org.example.GameMaster.Exception.AtravesandoCinturonException;
import org.example.GameMaster.Exception.NaveDestruidaException;
import org.example.GameMaster.Exception.NavePhantomException;
import org.example.GameMaster.Jugador;

public class CinturonAsteroide {
    private int cantidadDeAsteroides;

    public CinturonAsteroide(int cantidadDeAsteroides) {
        this.cantidadDeAsteroides = cantidadDeAsteroides;
    }


    public double calcularPoder() {
        return this.cantidadDeAsteroides * 10;
    }

    public void atravesar(Jugador jugador) throws NaveDestruidaException, AtravesandoCinturonException, NavePhantomException {

        if (jugador.getNave().soyNaveTipo() != TipoDeNave.NAVE_PHANTOM) {
            double vidaInicial = jugador.getNave().getVida();
            jugador.getNave().recibirGolpe(this.calcularPoder());

            if (jugador.getNave().tengoVida()) {
                double uadeCoinsConseguidos = calcularRecompensa(vidaInicial, jugador.getNave().getVida());
                jugador.agregarUadeCoins(uadeCoinsConseguidos);

                if (!jugador.getNave().getTanque().tengoCombustible()) {
                    jugador.setPlanetaActual(null);
                }
                System.out.println(uadeCoinsConseguidos);
                throw new AtravesandoCinturonException("¡CUIDADO! Atravesando el cinturón de Asteroides... \n ¡Conseguiste atravesar el cinturón! \n Ganaste: " + uadeCoinsConseguidos + " UADEcoins");
            }
        } else {
            throw new NavePhantomException("Atravesé el cinturón sin problemas, soy una nave Phantom.");
        }
    }

    public int getCantidadDeAsteroides() {
        return cantidadDeAsteroides;
    }

    private double calcularRecompensa(double vidaInicial, double vidaFinal){
        return this.calcularPoder() + (vidaInicial-vidaFinal);
    }
}