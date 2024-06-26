package org.example._MVC.Views;

import org.example._MVC.Model.Enums.Dificultad;
import org.example._MVC.Model.Enums.TipoDeNave;

public class GameBeginView {

    String nombreDelJugador;
    TipoDeNave naveJugador;
    int cantidadSistemasEstelares;
    Dificultad dificultad;
    int turno;

    public GameBeginView(String nombreDelJugador, TipoDeNave naveJugador, int cantidadSistemasEstelares, Dificultad dificultad, int turno) {
        this.nombreDelJugador = nombreDelJugador;
        this.naveJugador = naveJugador;
        this.cantidadSistemasEstelares = cantidadSistemasEstelares;
        this.dificultad = dificultad;
        this.turno = turno;
    }

    public String getNombreDelJugador() {
        return nombreDelJugador;
    }


    public TipoDeNave getNaveJugador() {
        return naveJugador;
    }

    public int getTurno() {
        return turno;
    }

    public int getCantidadSistemasEstelares() {
        return cantidadSistemasEstelares;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }

    public void setNombreDelJugador(String nombreDelJugador) {
        this.nombreDelJugador = nombreDelJugador;
    }


    public void setNaveJugador(TipoDeNave naveJugador) {
        this.naveJugador = naveJugador;
    }

    public void setCantidadSistemasEstelares(int cantidadSistemasEstelares) {
        this.cantidadSistemasEstelares = cantidadSistemasEstelares;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }
}
