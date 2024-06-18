package org.example.MapaEstelar.Sistemas;

import org.example.Enums.TipoDeNave;
import org.example.GameMaster.Jugador;

public class CinturonAsteroide {
    private int cantidadDeAsteroides;

    public CinturonAsteroide(int cantidadDeAsteroides) {
        this.cantidadDeAsteroides = cantidadDeAsteroides;
    }


    private double calcularPoder() {
        return this.cantidadDeAsteroides * 10;
    }

    public String atravesar(Jugador jugador) {

        if (jugador.getNave().soyNaveTipo() != TipoDeNave.NAVE_PHANTOM) {
            double vidaInicial = jugador.getNave().getVida();
            jugador.getNave().recibirGolpe(this.calcularPoder());

            if (jugador.getNave().tengoVida()) {
                double uadeCoinsConseguidos = calcularRecompensa(vidaInicial, jugador.getNave().getVida());
                jugador.agregarUadeCoins(uadeCoinsConseguidos);

                if (!jugador.getNave().getTanque().tengoCombustible()) {
                    jugador.setPlanetaActual(null);
                }
                return ("¡CUIDADO! Atravezando el cinturon de Asteroides... \n ¡Conseguiste atravesar el cinturón! \n Ganaste:"+ uadeCoinsConseguidos +"UADEcoins");
            }
        }
            return "Atraveze el cinturon sin problemas, soy una nave Phantom.";
    }

    private double calcularRecompensa(double vidaInicial,double vidaFinal){
        return this.calcularPoder() - (vidaInicial-vidaFinal);
    }
}