package org.example.nave;

import org.example.enums.TipoDeArma;
import org.example.partesDeLaNave.Arma;

import java.util.ArrayList;
import java.util.List;

public abstract class NaveAliada extends Nave {
    private double combustible;
    protected List<Arma> armas;
    private double escudo = 0;

    public NaveAliada(double velocidad, double vida, double combustible, double escudo) {
        super(velocidad, vida);
        this.combustible = combustible;
        this.armas = new ArrayList<>();
        this.escudo = escudo;
    }
    public void agregarEscudo(double cantDeEscudo){
        this.escudo += cantDeEscudo;

    }
    public void quitarEscudo(double cantDeEscudo) {
        this.escudo -= cantDeEscudo;
        if (this.escudo < 0) {
            this.quitarVida(this.escudo);
            this.escudo = 0;
        }
    }

    public double getEscudo() {
        return escudo;
    }

    public boolean tengoEscudo() {
        return this.escudo>0;
    }


    public void llenarTanqueDeCombustible(double combustible) {
        this.combustible += combustible;
    }

    public double getCombustible() {
        return combustible;
    }
    public void agregarArma(Arma nuevaArma) {
        this.armas.add(nuevaArma);
    }
    public void quitarArma(TipoDeArma tipoDeArma) {
        for (Arma arma : armas){
            if (tipoDeArma == arma.soyTipoDeArma()){
                this.armas.remove(arma);
            }else {
                System.out.println("no podes quitar un arma que no tenes!");
            }
        }
    }
    public boolean tengoArmas(){
        return armas.size()>0;
    }
    public boolean limiteDeArmas(){
        return armas.size()<=2;
    }

    public Arma elegirMiArma(TipoDeArma tipoDeArma) {
        for (Arma arma : armas) {
            if (arma.soyTipoDeArma() == tipoDeArma) {
                return arma;
            }
        }
        return null;
    }
    public void getArmas(){
        for (Arma arma : armas) {
            System.out.println(arma.soyTipoDeArma());
        }
    }


}