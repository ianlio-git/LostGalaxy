package org.example.Views;

import org.example.enums.Dificultades;
import org.example.enums.TipoDeNave;

public class GameBeginView {

    String nombreDelJugador;
    double uadeCoinsJugador;
    TipoDeNave naveJugador;
    int cantidadSistemasEstelares;
    Dificultades dificultad;
    int turno;

    public GameBeginView(String nombreDelJugador, double uadeCoinsJugador, TipoDeNave naveJugador, int cantidadSistemasEstelares, Dificultades dificultad, int turno) {
        this.nombreDelJugador = nombreDelJugador;
        this.uadeCoinsJugador = uadeCoinsJugador;
        this.naveJugador = naveJugador;
        this.cantidadSistemasEstelares = cantidadSistemasEstelares;
        this.dificultad = dificultad;
        this.turno = turno;
    }

    public String getNombreDelJugador() {
        return nombreDelJugador;
    }

    public double getUadeCoinsJugador() {
        return uadeCoinsJugador;
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

    public Dificultades getDificultad() {
        return dificultad;
    }

    public void setNombreDelJugador(String nombreDelJugador) {
        this.nombreDelJugador = nombreDelJugador;
    }

    public void setUadeCoinsJugador(double uadeCoinsJugador) {
        this.uadeCoinsJugador = uadeCoinsJugador;
    }

    public void setNaveJugador(TipoDeNave naveJugador) {
        this.naveJugador = naveJugador;
    }

    public void setCantidadSistemasEstelares(int cantidadSistemasEstelares) {
        this.cantidadSistemasEstelares = cantidadSistemasEstelares;
    }

    public void setDificultad(Dificultades dificultad) {
        this.dificultad = dificultad;
    }
}
