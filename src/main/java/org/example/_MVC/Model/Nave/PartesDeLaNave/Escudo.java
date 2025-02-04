package org.example._MVC.Model.Nave.PartesDeLaNave;

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
    public double agregarEscudo(double cantDeEscudo){
        if(cantDeEscudo + this.escudo <= this.escudoMax){
            this.escudo+=cantDeEscudo;
            return cantDeEscudo;
        }else{
            double faltante = this.cantidadEscudoMax() - this.escudo;
            this.escudo+= faltante;
            return faltante;
        }
    }


    public double cantidadEscudoActual() {
        return escudo;
    }

    public double cantidadEscudoMax() {
        return escudoMax;
    }

    public void escudoAcero() {
        this.escudo = 0;
        this.escudoMax = 0;
    }

    public void setEscudo(double escudo) {
        this.escudo = escudo;
    }

    public boolean escudoLleno(){
        return (this.escudoMax==escudo);
    }

}
