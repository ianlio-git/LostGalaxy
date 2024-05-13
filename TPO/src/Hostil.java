class Hostil extends Planeta {
    private boolean tesoro;
    private NavePirata naveEnemiga;

    public Hostil(NavePirata naveEnemiga, boolean tesoro, String codigoDePlaneta) {
        this.naveEnemiga = naveEnemiga;
        this.tesoro = tesoro;
        this.codigoDePlaneta = codigoDePlaneta;
    }

    public void combate(Nave nave) {
        // Implementación
    }
}