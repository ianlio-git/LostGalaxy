package org.example.mapaEstelar.sistemas;

import org.example.Views.SistemasView;
import org.example.enums.Dificultades;
import org.example.enums.TipoDeCuerpoCeleste;
import org.example.mapaEstelar.sistemas.planetas.Aliado;
import org.example.mapaEstelar.sistemas.planetas.Neutral;
import org.example.mapaEstelar.sistemas.planetas.Planeta;
import org.example.nave.tiposDeNaves.NavePirata;
import org.example.mapaEstelar.sistemas.planetas.Hostil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SistemaEstelar {
    private String nombre;
    static private int contador;
    private List<Planeta> planetas;
    private CinturonAsteroide cinturonAsteroides;

    public SistemaEstelar(Dificultades dificultad,boolean tesoro,boolean cinturon) {
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

    public void mostarListadoPlanetas() {
        for (Planeta planeta : planetas) {
            System.out.println(planeta.getCodigoDePlaneta());
        }
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

    public Planeta obtenerPlanetaHostil() {
        for (Planeta planeta : planetas) {
            if (planeta.soyPlanetaTipo()== TipoDeCuerpoCeleste.PLANETA_HOSTIL) {
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
    private void crearSistemaEstelar( Dificultades dificultad,boolean tieneTesoro, boolean tieneAsteriode) {
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
