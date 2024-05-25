package org.example.mapaEstelar.sistemas.planetas;

import org.example.enums.TipoDePlaneta;
import org.example.nave.NaveAliada;
import org.example.nave.tiposDeNaves.NavePirata;

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

    public boolean combate(NaveAliada naveAliada) {
        float acumDeDanio =0;

        while (naveEnemiga.getVida() > 0 && naveAliada.getVida() > 0) {
            while(naveEnemiga.poderAtaque()<=naveAliada.getEscudo()&& naveEnemiga.getVida() > 0){
                naveAliada.agregarEscudo((0 - naveEnemiga.poderAtaque()));
                naveEnemiga.setVida((0 - naveAliada.poderAtaque()));
                acumDeDanio+=naveEnemiga.poderAtaque();
            }
            // estaria copado que si salis de este while y continua la pelea, que primero te saque
            // todo el escudo antes de sacarte vida, para una proxima iteracion.
            if(naveEnemiga.getVida()>0) {
                naveAliada.setVida((0 - naveEnemiga.poderAtaque()));
                naveEnemiga.setVida((0 - naveAliada.poderAtaque()));
                acumDeDanio+=naveEnemiga.poderAtaque();
            }
        }
        if(naveEnemiga.getVida()==0) {
            naveAliada.setRecompensa(((2*naveEnemiga.poderAtaque())-acumDeDanio));
        }
        // true si la nave enemiga fue destruida, false si la nave aliada fue destruida
        return naveEnemiga.getVida() <= 0;
    }
}