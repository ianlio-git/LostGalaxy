package org.example.partesDeLaNave;

public class Escudo {
    private double proteccion;


    public Escudo(double proteccion) {
        this.proteccion = proteccion;
    }

    public double getProteccion() {
        return proteccion;
    }

    public void setProteccion(double proteccion) {
        this.proteccion = proteccion;
    }

    public void aumentarEscudo(double escudo){
        proteccion = proteccion + escudo;
    }
}
