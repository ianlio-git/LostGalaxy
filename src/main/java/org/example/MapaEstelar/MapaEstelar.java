package org.example.MapaEstelar;

import org.example.Enums.Dificultades;
import org.example.MapaEstelar.Sistemas.SistemaEstelar;

import java.util.ArrayList;
import java.util.List;

public class MapaEstelar {
    private List<SistemaEstelar> sistemasEstelares;
    private Dificultades dificultad;

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

    public boolean verificarExistenciaDeSistemaEstelar(String codigoDeSistema){
        boolean verificacion = false;
        for (SistemaEstelar sistema : sistemasEstelares){
            if(sistema.mostrarNombre().equals(codigoDeSistema)){
               verificacion = true;
            }
        }
        return verificacion;
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