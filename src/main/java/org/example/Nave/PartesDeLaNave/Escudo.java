package org.example.Nave.PartesDeLaNave;

public class Escudo {
    private double escudoMax;
    private double escudo;

    public Escudo (double escudoMax) {
        this.escudoMax = escudoMax;
        this.escudo = escudoMax;
    }

    public boolean tengoEscudo() {
        return this.escudo>0;
    }
    public void agregarEscudoMaximo(double cantDeEscudo){
        this.escudoMax += cantDeEscudo;

    }
    public void restablecerEscudo(){
        this.escudo = this.escudoMax;
    }
    public void quitarEscudo(double cantDeEscudo) {
        this.escudo -= cantDeEscudo;

    }
    public void agregarEscudo(double cantDeEscudo){
        this.escudo += cantDeEscudo;
    }

    public double cantidadEscudoActual() {
        return escudo;
    }

    public double cantidadEscudoMax() {
        return escudoMax;
    }

    public void escudoAcero() {
        this.escudo = 0;
    }

    public void setEscudo(double escudo) {
        this.escudo = escudo;
    }
}
