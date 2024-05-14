package org.example.planeta;

import org.example.nave.Nave;
import org.example.nave.NavePirata;

class Hostil extends Planeta {
    private static int count;
    private boolean tesoro;
    private NavePirata naveEnemiga;

    public Hostil( boolean tesoro, NavePirata naveEnemiga) {
        super("ENE-"+count++);
        this.tesoro = tesoro;
        this.naveEnemiga = naveEnemiga;
    }

    @Override
    public boolean soyPlanetaTipo() {
        return false;
    }

    public void combate(Nave nave) {
        // Implementación
    }
}