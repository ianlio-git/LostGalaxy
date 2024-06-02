package org.example.mapaEstelar;

import org.example.enums.Dificultades;
import org.example.mapaEstelar.sistemas.SistemaEstelar;

import java.util.ArrayList;
import java.util.List;

public class MapaEstelar {
    private List<SistemaEstelar> sistemasEstelares;
    Dificultades dificultad;
    public MapaEstelar() {
        this.sistemasEstelares = new ArrayList<>();
    }

    public SistemaEstelar agregarSistemaEstelar(Dificultades dificutad,boolean tieneTesoro,boolean tieneCinturon) {
        SistemaEstelar nuevoSistema = new SistemaEstelar(dificutad,tieneTesoro,tieneCinturon);
        sistemasEstelares.add(nuevoSistema);
        dificultad = dificutad;
        return nuevoSistema;
    }

    public List<SistemaEstelar> getSistemasEstelares() {
        return sistemasEstelares;
    }

    public SistemaEstelar obtenerSistemaEstelar(String codigo) {
        for (SistemaEstelar sistema : sistemasEstelares) {
            if (sistema.mostrarNombre().equals(codigo)) {
                return sistema;
            }
        }
        throw new RuntimeException("Sistema estelar no encontrado: " + codigo);
    }

    public Dificultades getDificultad() {
        return dificultad;
    }

}