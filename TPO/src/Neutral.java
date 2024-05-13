class Neutral extends Planeta {
    private Mercado mercado;

    public Neutral(String codigoDePlaneta, Mercado mercado) {
        this.codigoDePlaneta = codigoDePlaneta;
        this.mercado = mercado;
    }

    public Mercado getMercado() {
        return mercado;
    }
}