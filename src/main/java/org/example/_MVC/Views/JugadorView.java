package org.example._MVC.Views;

import org.example.MapaEstelar.Sistemas.Planetas.Planeta;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example.Nave.NaveAliada;

public class JugadorView {
    private SistemaEstelar sistemaActual;
    private String posicionEnElEspacio;
    private NaveAliada nave;
    private String nombre;
    private double uadeCoins;
    private boolean tesoro;
    private Planeta planetaActual;

    public JugadorView(SistemaEstelar sistemaActual, String posicionEnElEspacio, NaveAliada nave, String nombre, double uadeCoins, boolean tesoro, Planeta planetaActual) {
        this.sistemaActual = sistemaActual;
        this.posicionEnElEspacio = posicionEnElEspacio;
        this.nave = nave;
        this.nombre = nombre;
        this.uadeCoins = uadeCoins;
        this.tesoro = tesoro;
        this.planetaActual = planetaActual;
    }

    public SistemaEstelar getSistemaActual() {
        return sistemaActual;
    }

    public void setSistemaActual(SistemaEstelar sistemaActual) {
        this.sistemaActual = sistemaActual;
    }

    public String getPosicionEnElEspacio() {
        return posicionEnElEspacio;
    }

    public void setPosicionEnElEspacio(String posicionEnElEspacio) {
        this.posicionEnElEspacio = posicionEnElEspacio;
    }

    public NaveAliada getNave() {
        return nave;
    }

    public void setNave(NaveAliada nave) {
        this.nave = nave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getUadeCoins() {
        return uadeCoins;
    }

    public void setUadeCoins(double uadeCoins) {
        this.uadeCoins = uadeCoins;
    }

    public boolean isTesoro() {
        return tesoro;
    }

    public void setTesoro(boolean tesoro) {
        this.tesoro = tesoro;
    }

    public Planeta getPlanetaActual() {
        return planetaActual;
    }

    public void setPlanetaActual(Planeta planetaActual) {
        this.planetaActual = planetaActual;
    }
}
