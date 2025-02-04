package org.example._MVC.Model.MapaEstelar;

import org.example._MVC.Model.Enums.Dificultad;
import org.example._MVC.Model.MapaEstelar.Sistemas.SistemaEstelar;
import org.example._MVC.Views.MapaEstelarView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MapaEstelar {
    private static MapaEstelar instanciaMapaEstelar;
    private List<SistemaEstelar> sistemasEstelares;
    private Dificultad dificultad;
    private String sistemaConTesoro;



    public void mapaEstelarReset() {
        Iterator<SistemaEstelar> iterator = sistemasEstelares.iterator();
        while (iterator.hasNext()) {
            SistemaEstelar sistema = iterator.next();
            sistema.resetListaPlanetas();
            iterator.remove();
        }
    }


    private MapaEstelar() {
        this.sistemasEstelares = new ArrayList<>();
    }

    public static MapaEstelar getInstancia() {
        if (instanciaMapaEstelar == null) {
            instanciaMapaEstelar = new MapaEstelar();
        }
        return instanciaMapaEstelar;
    }


    private SistemaEstelar agregarSistemaEstelar(Dificultad dificutad, boolean tieneTesoro, boolean tieneCinturon) {
        SistemaEstelar nuevoSistema = new SistemaEstelar(dificutad, tieneTesoro, tieneCinturon);
        sistemasEstelares.add(nuevoSistema);
        dificultad = dificutad;
        return nuevoSistema;
    }

    public List<SistemaEstelar> getSistemasEstelares() {
        return sistemasEstelares;
    }

    public boolean verificarExistenciaDeSistemaEstelar(String codigoDeSistema) {
        boolean verificacion = false;
        for (SistemaEstelar sistema : sistemasEstelares) {
            if (sistema.mostrarNombre().equals(codigoDeSistema)) {
                verificacion = true;
            }
        }
        return verificacion;
    }

    public SistemaEstelar obtenerSistemRandom(){
        for (SistemaEstelar sistema : sistemasEstelares) {
                return sistema;
            }
        throw new RuntimeException("Sistema estelar no encontrado.");
    }
    public SistemaEstelar obtenerSistemaEstelar(String codigo) {
        for (SistemaEstelar sistema : sistemasEstelares) {
            if (sistema.mostrarNombre().equals(codigo)) {
                return sistema;
            }
        }
        throw new RuntimeException("Sistema estelar no encontrado: " + codigo);
    }

    public Dificultad getDificultad() {
        return dificultad;
    }


    public void crearMapaEstelar(Dificultad dificultad, int cantidadSistemasEstelares){
        Random random = new Random();
        int var = random.nextInt(cantidadSistemasEstelares);
        for (int i = 0; i < cantidadSistemasEstelares; i++) {
            boolean tieneTesoro = (i == var);
            boolean tieneCinturon = random.nextBoolean();
            SistemaEstelar sistemaEstelar = this.agregarSistemaEstelar(dificultad, tieneTesoro, tieneCinturon);
            if(tieneTesoro){
                sistemaConTesoro = sistemaEstelar.mostrarNombre();
            }
        }

    }

    public MapaEstelarView mapaEstelarToView(){
        return new MapaEstelarView(sistemasEstelares,dificultad,sistemaConTesoro);
    }
}