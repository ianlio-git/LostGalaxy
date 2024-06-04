package org.example.MapaEstelar.Sistemas.Planetas;

import org.example.Enums.Acciones;
import org.example.GameMaster.Jugador;
import org.example.Enums.TipoDeCuerpoCeleste;
import org.example.MapaEstelar.MapaEstelar;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;
import org.example.Mercado.Mercado;

public class Aliado extends Planeta {
    private MapaEstelar mapaEstelar;
    private static int count;

    public Aliado() {
        super("ALI-" + count++);
        this.mapaEstelar = MapaEstelar.getInstancia();
    }

    @Override
    public TipoDeCuerpoCeleste soyPlanetaTipo() {
        return TipoDeCuerpoCeleste.PLANETA_ALIADO;
    }

    @Override
    public Mercado ingresarAlMercado() {
        return null;
    }

    @Override
    public void combate(Jugador jugador) {
        System.out.println("Un planeta Aliado no tiene puede combatir");
    }

    public void repararNaveAliada(Jugador jugador) {
        bienvenidaAlTaller(jugador);
        jugador.getNave().reestablecerVida();
        jugador.getNave().getEscudo().restablecerEscudo();
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


    public String obtenerInformacion( Jugador jugador){
        if(jugador.puedoComprar(4000)){
            return ("El sistema estelar que posee el tesoro es el:"+mapaEstelar.getSistemaConTesoro());
        }
        else {
            return "Reune mas uade coins para obtener esa informacion!";
        }
    }
}
