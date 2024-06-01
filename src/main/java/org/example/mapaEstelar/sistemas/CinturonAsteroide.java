package org.example.mapaEstelar.sistemas;

import org.example.gameMaster.Jugador;

public class CinturonAsteroide {
    private int cantidadDeAsteroides;

    public CinturonAsteroide(int cantidadDeAsteroides) {
        this.cantidadDeAsteroides = cantidadDeAsteroides;
    }


    public double calcularPoder() {
        return this.cantidadDeAsteroides * 10;
    }

    public void atravesar(Jugador jugador) {
        System.out.println("CUIDADO! atravesando cinturon de asteroides.....");
        double vidaInicial= jugador.getNave().getVida();
        jugador.getNave().recibirGolpe(this.calcularPoder());
        if(jugador.getNave().tengoVida()){
            double uadeCoinsConseguidos = calcularRecompensa(vidaInicial,jugador.getNave().getVida());
            System.out.println("conseguiste atravezar el cinturon!");
            System.out.println("ganaste: "+uadeCoinsConseguidos+" UADEcoins");
            jugador.agregarUadeCoins(uadeCoinsConseguidos);
        }

    }
    private double calcularRecompensa(double vidaInicial,double vidaFinal){
        return this.calcularPoder() - (vidaInicial-vidaFinal);
    }
}