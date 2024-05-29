package org.example.nave;

import org.example.enums.TipoDeArma;
import org.example.enums.TipoDeNave;
import org.example.partesDeLaNave.Arma;
import org.example.partesDeLaNave.Escudo;
import org.example.partesDeLaNave.TanqueDeCombustible;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public abstract class NaveAliada extends Nave {
    private Escudo escudo;
    private TanqueDeCombustible tanque;
    protected List<Arma> armas;

    public NaveAliada(double velocidad, double vida, double capacidadMaxTanque, double cantidadEscudo) {
        super(velocidad, vida);
        this.tanque = new TanqueDeCombustible(capacidadMaxTanque);
        this.armas = new ArrayList<>();
        this.escudo = new Escudo(cantidadEscudo);
    }
    public void recibirGolpe(double ataque){
        if(escudo.tengoEscudo()){
            escudo.quitarEscudo(ataque);
        } else if (escudo.cantidadEscudoActual()<0) {
            this.quitarVida(abs(escudo.cantidadEscudoActual()));
            escudo.escudoAcero();
        }else {
            this.quitarVida(ataque);
        }
    }

    public Escudo getEscudo() {
        return escudo;
    }

    public TanqueDeCombustible getTanque() {
        return tanque;
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

    public boolean tengoEsaArma(TipoDeArma tipoDeArma) {
        for (Arma arma : armas) {
            if (arma.soyTipoDeArma() == tipoDeArma) {
                return true;
            }
        }
        return false;
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
    abstract public TipoDeNave soyNaveTipo();


}