package org.example.planeta;

public abstract class Planeta {
    private boolean tipoDePlaneta;
    private String codigoDePlaneta;

    public Planeta(String codigoDePlaneta) {
        this.codigoDePlaneta = codigoDePlaneta;
    }

    public String getCodigoDePlaneta() {
        return codigoDePlaneta;
    }
    public abstract boolean soyPlanetaTipo();

}