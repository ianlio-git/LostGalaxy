package org.example._MVC.Model.Nave.PartesDeLaNave;

import org.example._MVC.Model.Enums.TipoDeArma;

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