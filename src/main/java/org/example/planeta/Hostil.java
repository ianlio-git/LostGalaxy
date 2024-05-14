package org.example.planeta;

import org.example.enums.TipoDePlaneta;
import org.example.nave.Nave;
import org.example.nave.tiposDeNaves.NaveAegis;
import org.example.nave.tiposDeNaves.NavePirata;
import org.example.nave.tiposDeNaves.NaveSwift;

public class Hostil extends Planeta {
    private static int count;
    private boolean tesoro;
    private NavePirata naveEnemiga;

    public Hostil( boolean tesoro, NavePirata naveEnemiga) {
        super("ENE-"+count++);
        this.tesoro = tesoro;
        this.naveEnemiga = naveEnemiga;
    }
    @Override
    public TipoDePlaneta soyPlanetaTipo() {
        return TipoDePlaneta.HOSTIL;
    }
    public boolean isTesoro() {
        return tesoro;
    }

    public boolean combate(Nave naveAliada) {
        while (naveEnemiga.getVida() > 0 && naveAliada.getVida() > 0) {
            if (naveAliada instanceof NaveSwift) {
                NaveSwift naveAliadaSwift = (NaveSwift) naveAliada;
                if (naveAliadaSwift.getEscudo() > naveEnemiga.poderAtaque()) {
                    naveAliadaSwift.setEscudo(naveAliadaSwift.getEscudo() - naveEnemiga.poderAtaque());
                } else {
                    double danio = naveEnemiga.poderAtaque() - naveAliadaSwift.getEscudo();
                    naveAliadaSwift.setEscudo(0);
                    naveAliadaSwift.setVida(naveAliadaSwift.getVida() - danio);
                }
                naveEnemiga.setVida(naveEnemiga.getVida() - naveAliadaSwift.poderAtaque());
            } else if (naveAliada instanceof NaveAegis) {
                NaveAegis naveAliadaAegis = (NaveAegis) naveAliada;
                if (naveAliadaAegis.getEscudo() > naveEnemiga.poderAtaque()) {
                    naveAliadaAegis.setEscudo(naveAliadaAegis.getEscudo() - naveEnemiga.poderAtaque());
                } else {
                    double danio = naveEnemiga.poderAtaque() - naveAliadaAegis.getEscudo();
                    naveAliadaAegis.setEscudo(0);
                    naveAliadaAegis.setVida(naveAliadaAegis.getVida() - danio);
                }
                naveEnemiga.setVida(naveEnemiga.getVida() - naveAliadaAegis.poderAtaque());
            }
        }
        // Devolver true si la nave enemiga fue destruida, false si la nave aliada fue destruida
        return naveEnemiga.getVida() <= 0;
    }
}