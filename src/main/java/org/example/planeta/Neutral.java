package org.example.planeta;

import org.example.Jugador;
import org.example.enums.Acciones;
import org.example.enums.TipoDePlaneta;
import org.example.mercado.accionesMercado.ComprarCombustibleAccion;
import org.example.mercado.accionesMercado.ComprarEscudoAccion;
import org.example.mercado.accionesMercado.ComprarArmaAccion;
import org.example.mercado.Mercado;

public class Neutral extends Planeta {
    private static int count;
    private Mercado mercado;

    public Neutral() {
        super("NEU-"+ count++);
        this.mercado = new Mercado();
    }

    @Override
    public TipoDePlaneta soyPlanetaTipo() {
        return TipoDePlaneta.NEUTRAL;
    }

    public void realizarAccionEnMercado(Acciones accion, Jugador jugador) {
        switch (accion) {
            case COMPRAR_COMBUSTIBLE:
                mercado.realizarAccion(new ComprarCombustibleAccion(), jugador);
                break;
            case COMPRAR_ESCUDO:
                mercado.realizarAccion(new ComprarEscudoAccion(), jugador);
                break;
            case COMPRAR_ARMA:
                mercado.realizarAccion(new ComprarArmaAccion(), jugador);
                break;
            default:
                throw new IllegalArgumentException("Acción no válida para el mercado.");
            // A futuro, se puede agregar la venta de armas, cuando se tenga una o mas por ejemplo.
        }
    }
}