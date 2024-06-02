package org.example.Views;

import org.example.mapaEstelar.sistemas.SistemaEstelar;
import org.example.mapaEstelar.sistemas.planetas.Planeta;

import java.util.List;

public class SistemasView {
    private String nombreSistemaEstelar;
    private List<Planeta> planetas;
    private boolean tieneCinturonDeAsteroides;
    public SistemasView(String nombreSistemaEstelar, List<Planeta> planetas, boolean tieneCinturonDeAsteroides) {
        this.nombreSistemaEstelar = nombreSistemaEstelar;
        this.planetas = planetas;
        this.tieneCinturonDeAsteroides = tieneCinturonDeAsteroides;
    }

    public List<Planeta> getPlanetas() {
        return planetas;
    }

    public String getNombreSistemaEstelar() {
        return nombreSistemaEstelar;
    }

    public boolean isTieneCinturonDeAsteroides() {
        return tieneCinturonDeAsteroides;
    }
}

