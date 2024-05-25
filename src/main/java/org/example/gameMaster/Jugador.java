package org.example.gameMaster;

import org.example.nave.NaveAliada;

public class Jugador {
    private NaveAliada nave;
    private String nombre;
    private double uadeCoins;

    public Jugador(String nombre, double uadeCoins,NaveAliada nave) {
        this.nombre = nombre;
        this.uadeCoins = uadeCoins;
        this.nave = nave;
    }

    public double getUadeCoins() {
        return uadeCoins;
    }
    public void agregarUadeCoins(double uadeCoins){
        this.uadeCoins += uadeCoins;
    }
    public void quitarUadeCoins(double uadeCoins){
        this.uadeCoins -= uadeCoins;
    }


    public NaveAliada getNave() {
        return nave;
    }
    public String getNombre(){
        return nombre;
    }

}

