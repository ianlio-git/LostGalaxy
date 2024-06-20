package org.example.Views;

import org.example.Enums.Dificultad;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;

import java.util.List;

public class MapaEstelarView {
    private List<SistemaEstelar> sistemasEstelares;
    private Dificultad dificultad;
    private String sistemaConTesoro;

    public MapaEstelarView(List<SistemaEstelar> sistemasEstelares, Dificultad dificultad, String sistemaConTesoro) {
        this.sistemasEstelares = sistemasEstelares;
        this.dificultad = dificultad;
        this.sistemaConTesoro = sistemaConTesoro;
    }

    public List<SistemaEstelar> getSistemasEstelares() {
        return sistemasEstelares;
    }

    public void setSistemasEstelares(List<SistemaEstelar> sistemasEstelares) {
        this.sistemasEstelares = sistemasEstelares;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public String getSistemaConTesoro() {
        return sistemaConTesoro;
    }

    public void setSistemaConTesoro(String sistemaConTesoro) {
        this.sistemaConTesoro = sistemaConTesoro;
    }
}
