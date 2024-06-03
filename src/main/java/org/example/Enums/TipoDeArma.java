package org.example.Enums;

public enum TipoDeArma {
    CAÑON_DE_IONES(1.0),
    LASER_DE_FUSION(2.0),
    MISIL_DE_ANTIMATERIA(3.0),
    CAÑON_DE_PARTICULAS(4.0),
    CAÑON_GAUSS(5.0);

    private final double id;

    TipoDeArma(double id) {
        this.id = id;
    }

    public double getId() {
        return id;
    }
    public static TipoDeArma buscarId(double id) {
        for (TipoDeArma tipo : values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }
        return null;
    }
}
