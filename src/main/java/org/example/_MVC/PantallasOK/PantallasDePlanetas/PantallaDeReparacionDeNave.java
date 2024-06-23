package org.example._MVC.PantallasOK.PantallasDePlanetas;

import org.example._MVC.Views.JugadorView;

public class PantallaDeReparacionDeNave {

    JugadorView jugadorView;

    public PantallaDeReparacionDeNave(JugadorView jugadorView) {
        this.jugadorView = jugadorView;
    }

    public void bienvenidaAlTaller(JugadorView jugadorView){
        System.out.println("=============================================");
        System.out.println("Bienvenido al taller del UADE team!");
        System.out.println("Tu nave tiene "+jugadorView.getNave().getVida()+" de vida");
        System.out.println("Y tiene "+jugadorView.getNave().getEscudo().cantidadEscudoActual()+" de escudo!");
    }

}
