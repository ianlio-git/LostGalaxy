package org.example._MVC.Model.GameMaster;

import org.example._MVC.Model.GameMaster.Exception.*;
import org.example._MVC.Views.JugadorView;
import org.example._MVC.Model.Enums.TipoDeCuerpoCeleste;
import org.example._MVC.Model.MapaEstelar.Sistemas.SistemaEstelar;
import org.example._MVC.Model.MapaEstelar.Sistemas.Planetas.Planeta;
import org.example._MVC.Model.Nave.NaveAliada;

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
    public void encontreElTesoro(boolean planetaTesoro) throws TesoroEncontradoException {
        if (planetaTesoro) {
            throw new TesoroEncontradoException("¡Ganaste! Encontraste el tesoro.");
        }
    }


    public boolean puedoComprar(double precio) {
        return (this.getUadeCoins() >= precio);
    }

    public SistemaEstelar getSistemaActual() {
        return sistemaActual;
    }

    public Planeta getPlanetaActual() {
        return planetaActual;
    }

    public void viajeAPlaneta(Planeta planeta) throws PlanetaNoEncontradoException {
        if( !planeta.getCodigoDePlaneta().equals(this.planetaActual.getCodigoDePlaneta())){
            nave.getTanque().consumirCombustible(combustibleParaViajar(planeta.soyPlanetaTipo()));
            this.planetaActual = planeta;
            this.posicionEnElEspacio = planeta.getCodigoDePlaneta();
        }
    }
    public boolean puedoViajar(Planeta planeta) {
        return (nave.getTanque().getCombustible() >= combustibleParaViajar(planeta.soyPlanetaTipo()));
    }
    private double combustibleParaViajar(TipoDeCuerpoCeleste tipo){
        return nave.getTanque().combustibleNecesario(tipo, getNave().cantidadDeArmas());
    }
    public boolean puedoVolverPlanetaNeutral(){
        return  this.nave.getTanque().getCombustible() >= combustibleParaViajar(TipoDeCuerpoCeleste.PLANETA_NEUTRAL);
    }

    public void comenzarViajeANuevoSistema(SistemaEstelar sistemaEstelar) throws NaveDestruidaException, AtravesandoCinturonException, NavePhantomException, CambiandoDeSistemaExeption {
        nave.getTanque().consumirCombustible(combustibleParaViajar(TipoDeCuerpoCeleste.SISTEMA_ESTELAR));
        if (sistemaEstelar.tieneCinturonAsteroides()){
            sistemaActual = sistemaEstelar;
            sistemaEstelar.mostrarCinturonAsteroides().atravesar(this);
        }
        sistemaActual = sistemaEstelar;
        throw new CambiandoDeSistemaExeption("No se encuentran asteroides en este sistema.... ATRAVEZANDO SIN PROBLEMAS!");
    }

    public JugadorView toViewJugador() {
        if(planetaActual == null){
            return new JugadorView(sistemaActual,posicionEnElEspacio,nave,nombre,uadeCoins,tesoro,planetaActual);
        }
        return new JugadorView(sistemaActual,posicionEnElEspacio,nave,nombre,uadeCoins,tesoro,planetaActual);
    }


    public String getNombre() {
        return nombre;
    }

    public void setSistemaActual(SistemaEstelar sistemaActual) {
        this.sistemaActual = sistemaActual;
    }

    public void setNave(NaveAliada nave) {
        this.nave = nave;
    }

    public void setUadeCoins(double uadeCoins) {
        this.uadeCoins = uadeCoins;
    }

}

