package org.example.gameMaster;

import org.example.Views.JugadorView;
import org.example.enums.TipoDePlaneta;
import org.example.mapaEstelar.sistemas.SistemaEstelar;
import org.example.mapaEstelar.sistemas.planetas.Planeta;
import org.example.nave.NaveAliada;

public class Jugador {
    private SistemaEstelar sistemaActual;
    private Planeta planetaActual;
    private NaveAliada nave;
    private String nombre;
    private double uadeCoins;
    private boolean tesoro;

    public Jugador(String nombre, double uadeCoins,NaveAliada nave,Planeta planetaInicial,SistemaEstelar sistemaEstelar) {
        this.planetaActual = planetaInicial;
        this.sistemaActual = sistemaEstelar;
        this.nombre = nombre;
        this.uadeCoins = uadeCoins;
        this.nave = nave;
        this.tesoro = false;
    }
    public boolean naveEstaDestruida(){
        return !this.getNave().tengoVida();
    }
    public Planeta getPlanetaActual(){
        return planetaActual;
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
    public void encontreElTesoro(boolean planetaTesoro){
         this.tesoro=planetaTesoro;
    }
    public boolean mostrarTesoro(){
        return tesoro;
    }

    public boolean puedoComprar(double precio) {
        return (this.getUadeCoins() > precio);
    }

    public void viajeAPlaneta(Planeta planeta){
        if( !planeta.getCodigoDePlaneta().equals(this.planetaActual.getCodigoDePlaneta())){
            nave.getTanque().consumirCombustible(combustibleParaViajar(planeta.soyPlanetaTipo()));
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
        return nave.getTanque().combustibleNecesario(tipo, getNave().cantidadDeArmas());
    }
    public boolean puedoVolverPlanetaNeutral(){
        return  this.nave.getTanque().getCombustible() >= combustibleParaViajar(TipoDePlaneta.NEUTRAL);
    }

    public boolean tengoUadeCoinsParaCombustible(){
        return (uadeCoins > combustibleParaViajar(TipoDePlaneta.HOSTIL));
    }
    public void cambioDeSistema(SistemaEstelar sistemaEstelar) {
        if (sistemaEstelar != null) {
            if (!sistemaActual.mostrarNombre().equals(sistemaEstelar.mostrarNombre())) {
                System.out.println("Cambiando de sistema....");
                nave.getTanque().consumirCombustible(combustibleParaViajar(TipoDePlaneta.CINTURON_ASTEROIDE));
                if (sistemaEstelar.tieneCinturonAsteroides()){
                    sistemaEstelar.mostrarCinturonAsteroides().atravesar(this);
                }
                sistemaActual = sistemaEstelar;
            }
        }else{
            System.out.println("el sistema donde queres ir no existe");
        }
    }

    public JugadorView toViewJugador() {
        return new JugadorView(sistemaActual,planetaActual.getCodigoDePlaneta(),nave,nombre,uadeCoins,tesoro);
    }


}

