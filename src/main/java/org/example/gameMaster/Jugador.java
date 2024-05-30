package org.example.gameMaster;

import org.example.enums.TipoDePlaneta;
import org.example.mapaEstelar.sistemas.planetas.Planeta;
import org.example.nave.NaveAliada;

public class Jugador {
    private Planeta planetaActual;
    private NaveAliada nave;
    private String nombre;
    private double uadeCoins;
    private boolean tesoro;

    public Jugador(String nombre, double uadeCoins,NaveAliada nave,Planeta planetaInicial) {
        this.planetaActual = planetaInicial;
        this.nombre = nombre;
        this.uadeCoins = uadeCoins;
        this.nave = nave;
        this.tesoro = false;
    }
    public boolean naveEstaDestruida(){
        return !this.getNave().tengoVida();
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
    public void encontreElTesoro(boolean planetaTesoro){
         this.tesoro=planetaTesoro;
    }
    public boolean mostrarTesoro(){
        return tesoro;
    }
    public void mostrarDatos(){
        System.out.println("===============================================================");
        if (this.planetaActual==null){
            System.out.println("estoy en el planeta: null");
        }else{
            System.out.println("estoy en el planeta:"+planetaActual.getCodigoDePlaneta());
        }
        System.out.println("--Player--");
        System.out.println("Nombre:" + this.nombre);
        System.out.println("Cant de uade coins:" + this.getUadeCoins());
        System.out.println("Mi Nave es:"+this.nave.soyNaveTipo());
        System.out.println("--Stats--");
        System.out.println("Vida:" + this.nave.getVida());
        System.out.println("Escudo Actual:" + this.nave.getEscudo().cantidadEscudoActual());
        System.out.println("Escudo Maximo:" + this.nave.getEscudo().cantidadEscudoMax());
        System.out.println("Combustible:" + this.nave.getTanque().getCombustible());
        System.out.println("--Weapons--");
        if (this.nave.tengoArmas()) {
            System.out.println("Mis armas son:" );
            this.nave.getArmas();
        } else {
            System.out.println("Aun no tengo un arma, pero ya tendre una!");
        }
        System.out.println("Poder:" + this.nave.poderAtaque());
        System.out.println("===============================================================");
    }
    public boolean puedoComprar(double precio) {
        return (this.getUadeCoins() > precio);
    }

    public void viajeAPlaneta(Planeta planeta){
        if( !planeta.getCodigoDePlaneta().equals(this.planetaActual.getCodigoDePlaneta())){
            nave.getTanque().cosumirCombustible(combustibleParaViajar(planeta.soyPlanetaTipo()));
            this.planetaActual = planeta;
        }
    }
    public boolean puedoViajar(Planeta planeta) {
        if (planeta == null) {
            return false;
        }
        return nave.getTanque().getCombustible() >= combustibleParaViajar(planeta.soyPlanetaTipo());
    }
    private double combustibleParaViajar(TipoDePlaneta tipo){
        return nave.getTanque().combustibleNecesario(tipo);
    }
    public boolean puedoVoleverPlanetaNeutral(){
        return  this.nave.getTanque().getCombustible() > combustibleParaViajar(TipoDePlaneta.NEUTRAL);
    }

    public boolean tengoUadeCoinsParaCombustible(){
        return uadeCoins > combustibleParaViajar(TipoDePlaneta.HOSTIL);
    }
}

