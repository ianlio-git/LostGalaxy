package org.example.MapaEstelar.Sistemas.Planetas;

import org.example.Enums.TipoDeArma;
import org.example.GameMaster.Jugador;
import org.example.Enums.Acciones;
import org.example.Enums.TipoDeCuerpoCeleste;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example.Mercado.Mercado;

public class Neutral extends Planeta {
    private static int count;
    private Mercado mercado;

    public Neutral() {
        super("NEU-"+ count++);
        this.mercado = new Mercado();
    }

    @Override
    public TipoDeCuerpoCeleste soyPlanetaTipo() {
        return TipoDeCuerpoCeleste.PLANETA_NEUTRAL;
    }

    @Override
    public void realizarAccionEnMercado(Acciones accion, Jugador jugador,double cantidad) {
        switch (accion) {
            case COMPRAR_COMBUSTIBLE:
                mercado.comprarCombustible(jugador,cantidad);
                break;
            case COMPRAR_ESCUDO:
                mercado.comprarEscudo(jugador,cantidad);
                break;
            case COMPRAR_ARMA, VENDER_ARMA:
                procesarCompraOVentaArma(accion,jugador,cantidad);
                break;
            case RECARGAR_ESCUDO:
                mercado.recargarEscudo(jugador,cantidad);
                break;
            default:
                System.out.println("Acción no válida para el mercado.");
        }
    }
    private void procesarCompraOVentaArma(Acciones accion,Jugador jugador,double cantidad) {
        TipoDeArma arma = TipoDeArma.buscarId(cantidad);
        if (arma != null) {
            if (accion == Acciones.COMPRAR_ARMA) {
                mercado.comprarArma(jugador, arma);
            } else {
                mercado.venderArma(jugador, arma);
            }
        } else {
            System.out.println("No ingresó un ID de arma válido");
        }
    }


    @Override
    public void combate(Jugador jugador) {
        System.out.println("Un planeta Neutral no puede combatir");
    }

    @Override
    public void repararNaveAliada(Jugador jugador) {
        System.out.println("Debes pagar para realizar estas acciones aqui");
    }

    @Override
    public void obtenerInformacion(SistemaEstelar sistemaEstelar, Jugador jugador) {
        System.out.println("No puedes obtener informacion aqui.");
    }



}