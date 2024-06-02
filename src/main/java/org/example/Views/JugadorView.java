package org.example.Views;

import org.example.mapaEstelar.sistemas.SistemaEstelar;
import org.example.nave.NaveAliada;

public class JugadorView {
    private SistemaEstelar sistemaActual;
    private String planetaActual;
    private NaveAliada nave;
    private String nombre;
    private double uadeCoins;
    private boolean tesoro;

    public JugadorView(SistemaEstelar sistemaActual, String codigoDePlaneta, NaveAliada nave, String nombre, double uadeCoins, boolean tesoro) {
        this.sistemaActual = sistemaActual;
        this.planetaActual = codigoDePlaneta;
        this.nave = nave;
        this.nombre = nombre;
        this.uadeCoins = uadeCoins;
        this.tesoro = tesoro;
    }

    public SistemaEstelar getSistemaActual() {
        return sistemaActual;
    }

    public String getPlanetaActual() {
        return planetaActual;
    }

    public NaveAliada getNave() {
        return nave;
    }

    public String getNombre() {
        return nombre;
    }

    public double getUadeCoins() {
        return uadeCoins;
    }

    public boolean isTesoro() {
        return tesoro;
    }
}
