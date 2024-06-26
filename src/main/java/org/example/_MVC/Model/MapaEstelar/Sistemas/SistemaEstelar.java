package org.example._MVC.Model.MapaEstelar.Sistemas;

import org.example._MVC.Views.SistemasView;
import org.example._MVC.Model.Enums.Dificultad;
import org.example._MVC.Model.Enums.TipoDeCuerpoCeleste;
import org.example._MVC.Model.MapaEstelar.Sistemas.Planetas.Aliado;
import org.example._MVC.Model.MapaEstelar.Sistemas.Planetas.Neutral;
import org.example._MVC.Model.MapaEstelar.Sistemas.Planetas.Planeta;
import org.example._MVC.Model.Nave.TiposDeNaves.NavePirata;
import org.example._MVC.Model.MapaEstelar.Sistemas.Planetas.Hostil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SistemaEstelar {
    private String nombre;
    static private int contador;
    private List<Planeta> planetas;
    private CinturonAsteroide cinturonAsteroides;

    public int getContador(){
        return this.contador;
    }

    public void resetListaPlanetas() {
        Iterator<Planeta> iterator = planetas.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    public SistemaEstelar(Dificultad dificultad, boolean tesoro, boolean cinturon) {
        this.contador = contador;
        this.nombre = "SIST-"+this.contador++;
        this.planetas = new ArrayList<>();
        crearSistemaEstelar(dificultad,tesoro,cinturon);
    }

    public String mostrarNombre() {
        return nombre;
    }

    public CinturonAsteroide mostrarCinturonAsteroides() {
        return cinturonAsteroides;
    }


    public Planeta obtenerPlanetaNeutral() {
        for (Planeta planeta : planetas) {
            if (planeta.soyPlanetaTipo()== TipoDeCuerpoCeleste.PLANETA_NEUTRAL) {
                return planeta;
            }
        }
        return null;
    }

    public List<Planeta> getPlanetas() {
        return planetas;
    }

    public Planeta obtenerPlanetaHostil(String codigoDePlaneta) {
        for (Planeta planeta : planetas) {
            if (planeta.getCodigoDePlaneta().equals(codigoDePlaneta)) {
                return planeta;
            }
        }
        return null;
    }
    public Planeta obtenerPlanetaAliado() {
        for (Planeta planeta : planetas) {
            if (planeta.soyPlanetaTipo()== TipoDeCuerpoCeleste.PLANETA_ALIADO) {
                return planeta;
            }
        }
        return null;
    }

    public void quitarPlaneta(Planeta planeta){
        this.planetas.remove(planeta);
    }
    public boolean tieneCinturonAsteroides() {
        return cinturonAsteroides != null;
    }
    private void crearSistemaEstelar(Dificultad dificultad, boolean tieneTesoro, boolean tieneAsteriode) {
        Random rand = new Random();
        int cantPlanetasNeutrales = 0;
        int cantPlanetasHostiles = 0;
        int cantPlanetasAliados = 0;
        int cantAsteroides = rand.nextInt(10) + 1;

        switch (dificultad) {
            case FACIL:
                // Para el nivel FÁCIL, el total de planetas estará entre 3 y 6.
                int totalFacil = rand.nextInt(4) + 3;
                cantPlanetasNeutrales = (int) (totalFacil * 0.4); // 30% de planetas neutrales
                cantPlanetasAliados = (int) (totalFacil * 0.3);  // 30% de planetas Aliados
                cantPlanetasHostiles  = totalFacil - cantPlanetasNeutrales - cantPlanetasAliados;
                break;
            case MEDIO:
                // Para el nivel MEDIO, el total de planetas estará entre 4 y 8.
                int totalMedio = rand.nextInt(5) + 4;
                cantPlanetasNeutrales = (int) (totalMedio * 0.3); // 30% de planetas neutrales
                cantPlanetasAliados = (int) (totalMedio * 0.2); // 20% de planetas Aliados
                cantPlanetasHostiles  = totalMedio - cantPlanetasNeutrales - cantPlanetasAliados;
                break;
            case DIFICIL:
                // Para el nivel DIFÍCIL, el total de planetas estará entre 6 y 12.
                int totalDificil = rand.nextInt(7) + 6;
                cantPlanetasNeutrales = (int) (totalDificil * 0.3); // 30% de planetas neutrales
                cantPlanetasAliados = (int) (totalDificil * 0.2);  // 10% de planetas Aliados
                cantPlanetasHostiles  = totalDificil - cantPlanetasNeutrales - cantPlanetasAliados;
                break;
            default:
        }

        for (int i = 0; i < cantPlanetasNeutrales; i++) {
            Neutral planetaNeutral = new Neutral();
            agregarPlaneta(planetaNeutral);
        }
        if (tieneTesoro){
            int indicePlanetaConTesoro = rand.nextInt(cantPlanetasHostiles);
            for (int i = 0; i < cantPlanetasHostiles; i++) {
                boolean tesoro = (i == indicePlanetaConTesoro);
                NavePirata navePirata = new NavePirata();
                Hostil planetaHostil = new Hostil(tesoro, navePirata);
                agregarPlaneta(planetaHostil);
            }
        }else{
            for (int i = 0; i < cantPlanetasHostiles; i++) {
                NavePirata navePirata = new NavePirata();
                Hostil planetaHostil = new Hostil(tieneTesoro, navePirata);
                agregarPlaneta(planetaHostil);
            }
        }

        for (int i = 0; i < cantPlanetasAliados; i++) {
            Aliado planetaAliado = new Aliado();
            agregarPlaneta(planetaAliado);
        }

        if (tieneAsteriode) {
            this.cinturonAsteroides = new CinturonAsteroide(cantAsteroides);
        }
    }
    private void agregarPlaneta(Planeta planeta) {
        this.planetas.add(planeta);
    }
    public SistemasView toViewSistema(){
        return new SistemasView(this.nombre,this.planetas,tieneCinturonAsteroides());
    }
}
