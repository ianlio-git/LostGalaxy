package org.example.mapaEstelar.sistemas.planetas;

import org.example.enums.Acciones;
import org.example.gameMaster.Jugador;
import org.example.enums.TipoDePlaneta;
import org.example.mapaEstelar.MapaEstelar;
import org.example.mapaEstelar.sistemas.SistemaEstelar;

import java.util.List;

public class Aliado extends Planeta {
    private static int count;

    public Aliado() {
        super("ALI-" + count++);
    }

    @Override
    public TipoDePlaneta soyPlanetaTipo() {
        return TipoDePlaneta.ALIADO;
    }

    @Override
    public void realizarAccionEnMercado(Acciones accion, Jugador jugador, double cantidad) {
        System.out.println("Un planeta Aliado no tiene Mercado");
    }

    @Override
    public void combate(Jugador jugador) {
        System.out.println("Un planeta Aliado no tiene puede combatir");
    }

    public void repararNaveAliada(Jugador jugador) {
        bienvenidaAlTaller(jugador);
        double vidaMaxima = jugador.getNave().getVidaMaxima();
        jugador.getNave().setVida(vidaMaxima);
        double escudoMaximo = jugador.getNave().getEscudo().cantidadEscudoMax();
        jugador.getNave().getEscudo().setEscudo(escudoMaximo);
        reparacionDeNave(jugador);
    }

    private void bienvenidaAlTaller(Jugador jugador){
        System.out.println("Bienvenido al taller del UADE team!");
        System.out.println("Tu nave tiene "+jugador.getNave().getVida()+" de vida");
        System.out.println("Y tiene "+jugador.getNave().getEscudo().cantidadEscudoActual()+" de escudo!");
    }
    private void reparacionDeNave(Jugador jugador){
        System.out.println("Someteremos tu nave a reparacion!");
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("Hemos reparado tu nave con exito!");
        System.out.print("Ahora tiene "+jugador.getNave().getVida()+" de vida");
        System.out.println(" con "+jugador.getNave().getEscudo().cantidadEscudoActual()+" de escudo!");
    }


    public void obtenerInformacion(SistemaEstelar sistemaEstelar, Jugador jugador){
        if(jugador.puedoComprar(4000)){
            System.out.println("El sistema estelar que posee el tesoro es el:"+sistemaEstelar.mostrarNombre());
        }
        else {
            System.out.println("Reune mas uade coins para obtener esa informacion!");
        }
    }
}
