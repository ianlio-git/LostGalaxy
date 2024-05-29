package org.example.nave.partesDeLaNave;

import org.example.enums.TipoDeArma;

 public class Arma{

   private double precio;
   private double poderDeAtaque;
   private TipoDeArma tipoDeArma;

    public Arma(TipoDeArma tipoDeArma, double precio, double poderDeAtaque) {
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
    public TipoDeArma soyTipoDeArma(){
        return tipoDeArma;
    }


}