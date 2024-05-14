package org.example.partesDeLaNave;

import org.example.partesDeLaNave.Armas.*;

public abstract class Item {
    private double precio;

    private TipoDeArma cañonDeIones = new CañonDeIones();
    private TipoDeArma laserDeFusion = new LaserDeFusion();
    private TipoDeArma misilDeAntiMateria = new MisilDeAntiMateria();
    private TipoDeArma cañonDeParticulas = new CañonDeParticulas();
    private TipoDeArma cañonGauss = new CañonGauss();

    public Item(double precio) {
        this.precio = precio;
    }

}
