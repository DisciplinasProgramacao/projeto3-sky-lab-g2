import java.time.Duration;

/**
 * Enumeração que representa diferentes tipos de serviços em um estacionamento.
 */
enum Servico {
    /**
     * Serviço de manobrista.
     * Valor: R$ 5.0
     * Tempo mínimo: Nenhum tempo mínimo.
     */
    MANOBRISTA("Manobrista", 5.0, Duration.ZERO),

    /**
     * Serviço de lavagem de veículo.
     * Valor: R$ 20.0
     * Tempo mínimo: 1 hora.
     */
    LAVAGEM("Lavagem", 20.0, Duration.ofHours(1)),

    /**
     * Serviço de polimento de veículo, que inclui lavagem.
     * Valor: R$ 45.0
     * Tempo mínimo: 2 horas.
     */
    POLIMENTO("Polimento (inclui lavagem)", 45.0, Duration.ofHours(2));

    private final String descricao;
    private final double valor;
    private final Duration tempoMinimo;

    /**
     * Construtor privado da enumeração Servico.
     *
     * @param descricao     A descrição do serviço.
     * @param valor         O valor do serviço.
     * @param tempoMinimo   O tempo mínimo necessário para o serviço.
     */
    Servico(String descricao, double valor, Duration tempoMinimo) {
        this.descricao = descricao;
        this.valor = valor;
        this.tempoMinimo = tempoMinimo;
    }

    /**
     * Obtém a descrição do serviço.
     *
     * @return A descrição do serviço.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Obtém o valor do serviço.
     *
     * @return O valor do serviço.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Obtém o tempo mínimo necessário para o serviço.
     *
     * @return O tempo mínimo necessário para o serviço.
     */
    public Duration getTempoMinimo() {
        return tempoMinimo;
    }
}
