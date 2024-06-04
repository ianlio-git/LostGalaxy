package org.example.Views;

import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example.Nave.NaveAliada;

public class JugadorView {
    private SistemaEstelar sistemaActual;
    ;private String posicionEnElEspacio;
    private NaveAliada nave;
    private String nombre;
    private double uadeCoins;
    private boolean tesoro;


    public JugadorView(SistemaEstelar sistemaActual,  String posicionEnElEspacio, NaveAliada nave, String nombre, double uadeCoins, boolean tesoro) {
        this.sistemaActual = sistemaActual;
        this.posicionEnElEspacio = posicionEnElEspacio;
        this.nave = nave;
        this.nombre = nombre;
        this.uadeCoins = uadeCoins;
        this.tesoro = tesoro;
    }

    public SistemaEstelar getSistemaActual() {
        return sistemaActual;
    }

    public String getPosicionEnElEspacio() {
        return posicionEnElEspacio;
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
