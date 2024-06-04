package org.example.GameMaster;

import org.example.Views.JugadorView;
import org.example.Enums.TipoDeCuerpoCeleste;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example.MapaEstelar.Sistemas.Planetas.Planeta;
import org.example.Nave.NaveAliada;

public class Jugador {
    private SistemaEstelar sistemaActual;
    private Planeta planetaActual;
    private NaveAliada nave;
    private String nombre;
    private double uadeCoins;
    private boolean tesoro;
    private String posicionEnElEspacio;

    public Jugador(String nombre, double uadeCoins,NaveAliada nave,Planeta planetaInicial,SistemaEstelar sistemaEstelar) {
        this.planetaActual = planetaInicial;
        this.sistemaActual = sistemaEstelar;
        this.nombre = nombre;
        this.uadeCoins = uadeCoins;
        this.nave = nave;
        this.tesoro = false;
        this.posicionEnElEspacio = planetaActual.getCodigoDePlaneta();
    }

    public void setPosicionEnElEspacio(String posicionEnElEspacio) {
        this.posicionEnElEspacio = posicionEnElEspacio;
    }

    public boolean naveEstaDestruida(){
        return !this.getNave().tengoVida();
    }
    public Planeta getPlanetaActual(){
        return planetaActual;
    }
    public String getPosicionEnElEspacio() {return posicionEnElEspacio;}
    public void setPlanetaActual(Planeta planetaActual) {
        this.planetaActual = planetaActual;
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

    public boolean puedoComprar(double precio) {
        return (this.getUadeCoins() > precio);
    }

    public SistemaEstelar getSistemaActual() {
        return sistemaActual;
    }

    public void viajeAPlaneta(Planeta planeta){
        if( !planeta.getCodigoDePlaneta().equals(this.planetaActual.getCodigoDePlaneta())){
            nave.getTanque().consumirCombustible(combustibleParaViajar(planeta.soyPlanetaTipo()));
            this.planetaActual = planeta;
            this.posicionEnElEspacio = planeta.getCodigoDePlaneta();
        }
    }
    public boolean puedoViajar(Planeta planeta) {
        if (planeta == null) {
            return false;
        }
        return nave.getTanque().getCombustible() >= combustibleParaViajar(planeta.soyPlanetaTipo());
    }
    private double combustibleParaViajar(TipoDeCuerpoCeleste tipo){
        return nave.getTanque().combustibleNecesario(tipo, getNave().cantidadDeArmas());
    }
    public boolean puedoVolverPlanetaNeutral(){
        return  this.nave.getTanque().getCombustible() >= combustibleParaViajar(TipoDeCuerpoCeleste.PLANETA_NEUTRAL);
    }

    public boolean tengoUadeCoinsParaCombustible(){
        return (uadeCoins > combustibleParaViajar(TipoDeCuerpoCeleste.PLANETA_HOSTIL));
    }
    public void viajarANuevoSistema(SistemaEstelar sistemaEstelar) {
        nave.getTanque().consumirCombustible(combustibleParaViajar(TipoDeCuerpoCeleste.SISTEMA_ESTELAR));
        if (sistemaEstelar.tieneCinturonAsteroides()){
            sistemaEstelar.mostrarCinturonAsteroides().atravesar(this);
        }
        sistemaActual = sistemaEstelar;
    }

    public JugadorView toViewJugador() {
        if(planetaActual == null){
            return new JugadorView(sistemaActual,posicionEnElEspacio,nave,nombre,uadeCoins,tesoro);
        }
        return new JugadorView(sistemaActual,posicionEnElEspacio,nave,nombre,uadeCoins,tesoro);
    }


    public String getNombre() {
        return nombre;
    }
}

