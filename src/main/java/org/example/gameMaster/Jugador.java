package org.example.gameMaster;

import org.example.nave.NaveAliada;
import org.example.partesDeLaNave.Arma;

public class Jugador {
    private NaveAliada nave;
    private String nombre;
    private double uadeCoins;
    private boolean tesoro;

    public Jugador(String nombre, double uadeCoins,NaveAliada nave) {
        this.nombre = nombre;
        this.uadeCoins = uadeCoins;
        this.nave = nave;
        this.tesoro = false;
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
    public void encontreElTesoro(boolean planetaTesoro){
         this.tesoro=planetaTesoro;
    }
    public boolean mostrarTesoro(){
        return tesoro;
    }
    public void mostrarDatos(){
        System.out.println("==============================================");
        System.out.println("--Player--");
        System.out.println("Nombre:" + this.getNombre());
        System.out.println("Cant de uade coins:" + this.getUadeCoins());
        System.out.println("Mi Nave es:"+this.nave.soyNaveTipo());
        System.out.println("--Stats--");
        System.out.println("Vida:" + this.nave.getVida());
        System.out.println("Escudo:" + this.nave.getEscudo());
        System.out.println("Combustible:" + this.nave.getTanque().getCombustible());
        System.out.println("--Weapons--");
        if (this.nave.tengoArmas()) {
            System.out.println("Mis armas son:" );
            this.nave.getArmas();
        } else {
            System.out.println("Aun no tengo un arma, pero ya tendre una!");
        }
        System.out.println("Poder:" + this.nave.poderAtaque());
        System.out.println("==============================================");
    }
    public boolean puedoComprar(double precio) {
        return (this.getUadeCoins() > precio);
    }

}

