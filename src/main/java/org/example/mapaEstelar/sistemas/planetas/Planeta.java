package org.example.mapaEstelar.sistemas.planetas;

import org.example.enums.TipoDePlaneta;

public abstract class Planeta {

    private String codigoDePlaneta;

    public Planeta(String codigoDePlaneta) {
        this.codigoDePlaneta = codigoDePlaneta;
    }

    public String getCodigoDePlaneta() {
        return codigoDePlaneta;
    }
    public abstract TipoDePlaneta soyPlanetaTipo();


}