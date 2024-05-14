package org.example.partesDeLaNave;

import org.example.enums.TipoDeArma;

abstract public class Arma{

   protected float precio;
   protected float poderDeAtaque;
   protected TipoDeArma tipoDeArma;

    public Arma(TipoDeArma tipoDeArma, float precio, float poderDeAtaque) {
        this.precio = precio;
        this.poderDeAtaque = poderDeAtaque;
        this.tipoDeArma = tipoDeArma;
    }

    public double getPoder(){
        return poderDeAtaque;
    }
    public double getPrecio(){
        return precio;
    }
    public TipoDeArma getTipoDeArma(){
        return tipoDeArma;
    }
}