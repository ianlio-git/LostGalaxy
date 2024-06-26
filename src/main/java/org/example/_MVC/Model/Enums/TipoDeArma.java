package org.example._MVC.Model.Enums;

public enum TipoDeArma {
    CAÑON_DE_IONES(150),
    LASER_DE_FUSION(300),
    MISIL_DE_ANTIMATERIA(450),
    CAÑON_DE_PARTICULAS(600),
    CAÑON_GAUSS(750);

    private final int precio;

    TipoDeArma(int precio) {
        this.precio = precio;
    }

    public int getPrecio() {
        return precio;
    }
}
