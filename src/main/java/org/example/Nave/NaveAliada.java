package org.example.Nave;

import org.example.Enums.TipoDeArma;
import org.example.Enums.TipoDeNave;
import org.example.GameMaster.Exception.NaveDestruidaException;
import org.example.Nave.PartesDeLaNave.Arma;
import org.example.Nave.PartesDeLaNave.Escudo;
import org.example.Nave.PartesDeLaNave.TanqueDeCombustible;

import java.util.ArrayList;
import java.util.Iterator;
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
        } else {
            this.quitarVida(ataque);
        }
        if(escudo.cantidadEscudoActual()<0){
            this.vida+= escudo.cantidadEscudoActual();
            escudo.escudoAcero();
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

    public void vaciarArmamentos() {
        Iterator<Arma> iterator = armas.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    public boolean quitarArma(TipoDeArma tipoDeArma) {
        boolean removiElArma = false;
        for (Arma arma : armas){
            if(tipoDeArma.equals(arma.soyTipoDeArma())){
                this.armas.remove(arma);
                return true;
            }
        }
        return removiElArma;
    }

    public boolean tengoArmas(){
        return armas.size()>0;
    }

    public boolean limiteDeArmas(){
        return armas.size()<2;
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
    public List<Arma> getArmas(){
        return armas;
    }

    public double cantidadDeArmas(){
        return armas.size();
    }
    abstract public TipoDeNave soyNaveTipo();

    @Override
    public boolean tengoVida() throws NaveDestruidaException {
        if(this.vida<=0){
            throw new NaveDestruidaException("Tu nave fue destruida. ¡Game Over!");
        }
        return this.vida > 0 ;
    }

}