package org.example.MapaEstelar.Sistemas;

import org.example.Enums.TipoDeNave;
import org.example.GameMaster.Exception.NaveDestruidaException;
import org.example.GameMaster.Jugador;

public class CinturonAsteroide {
    private int cantidadDeAsteroides;

    public CinturonAsteroide(int cantidadDeAsteroides) {
        this.cantidadDeAsteroides = cantidadDeAsteroides;
    }


    private double calcularPoder() {
        return this.cantidadDeAsteroides * 10;
    }

    public void atravesar(Jugador jugador) throws NaveDestruidaException {

        if (jugador.getNave().soyNaveTipo() != TipoDeNave.NAVE_PHANTOM) {
            double vidaInicial = jugador.getNave().getVida();
            jugador.getNave().recibirGolpe(this.calcularPoder());

            if (jugador.getNave().tengoVida()) {
                double uadeCoinsConseguidos = calcularRecompensa(vidaInicial, jugador.getNave().getVida());
                jugador.agregarUadeCoins(uadeCoinsConseguidos);

                if (!jugador.getNave().getTanque().tengoCombustible()) {
                    jugador.setPlanetaActual(null);
                }
                //hay que ver como pasar esto a la view
                //return ("¡CUIDADO! Atravezando el cinturon de Asteroides... \n ¡Conseguiste atravesar el cinturón! \n Ganaste:"+ uadeCoinsConseguidos +"UADEcoins");
            }
        }
            //hay que ver como pasar esto a la view
            //return "Atraveze el cinturon sin problemas, soy una nave Phantom.";
    }

    private double calcularRecompensa(double vidaInicial,double vidaFinal){
        return this.calcularPoder() - (vidaInicial-vidaFinal);
    }
}