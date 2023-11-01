import java.time.Duration;

enum Servico {
    MANOBRISTA("Manobrista", 5.0, Duration.ZERO),
    LAVAGEM("Lavagem", 20.0, Duration.ofHours(1)),
    POLIMENTO("Polimento (inclui lavagem)", 45.0, Duration.ofHours(2));

    private final String descricao;
    private final double valor;
    private final Duration tempoMinimo;

    Servico(String descricao, double valor, Duration tempoMinimo) {
        this.descricao = descricao;
        this.valor = valor;
        this.tempoMinimo = tempoMinimo;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public Duration getTempoMinimo() {
        return tempoMinimo;
    }
}
