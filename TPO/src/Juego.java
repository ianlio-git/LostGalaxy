public class Juego {
    private List<Planeta> planetas;
    private int turno;
    private Jugador jugador;

    public Juego(Jugador jugador) {
        this.jugador = jugador;
    }

    public Nave elegirNave() {
        // Implementación
        return null;
    }

    public void siguienteTurno() {
        // Implementación
    }

    public void FinJuego(Planeta planeta, Jugador jugador) {
        // Implementación
    }

    public Planeta crearPlanetaNeutral() {
        // Implementación
        return null;
    }

    public Planeta crearPlanetaHostil() {
        // Implementación
        return null;
    }

    public Planeta getPlanetaNeutral() {
        // Implementación
        return null;
    }

    public Planeta getPlanetaHostil() {
        // Implementación
        return null;
    }
}