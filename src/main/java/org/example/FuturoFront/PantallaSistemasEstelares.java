package org.example.FuturoFront;

import org.example.Views.SistemasView;
import org.example.MapaEstelar.Sistemas.Planetas.Planeta;

public class PantallaSistemasEstelares {
    SistemasView sistemasView;

    public PantallaSistemasEstelares(SistemasView sistemasView) {
        this.sistemasView = sistemasView;
    }

    public void mostrarSistema() {
        System.out.println("=============================================");
        System.out.println("           Información del Sistema           ");
        System.out.println("=============================================");
        System.out.println("Sistema Estelar: " + sistemasView.getNombreSistemaEstelar());
        System.out.println("---------------------------------------------");
        System.out.println("Lista de Planetas:");
        for (Planeta planeta : sistemasView.getPlanetas()) {
            System.out.println(" - Código del Planeta: " + planeta.getCodigoDePlaneta());
        }
        System.out.println("---------------------------------------------");
        System.out.println("Tiene Cinturón de Asteroides: " + (sistemasView.isTieneCinturonDeAsteroides() ? "Sí" : "No"));
        System.out.println("=============================================");
    }

}


