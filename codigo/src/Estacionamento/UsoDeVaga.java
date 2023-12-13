import java.time.LocalDateTime;

/**
 * Classe que representa o uso de uma vaga de estacionamento por um cliente.
 */
public class UsoDeVaga {

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private Cliente cliente;

    /**
     * Construtor que cria uma instância de UsoDeVaga associada a uma vaga.
     *
     * @param vaga A vaga utilizada no uso.
     */
    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.valorPago = 0.0;
    }

    /**
     * Obtém a data e hora de entrada na vaga.
     *
     * @return A data e hora de entrada.
     */
    public LocalDateTime getEntrada() {
        return entrada;
    }

    /**
     * Obtém a data e hora de entrada na vaga.
     *
     * @return A data e hora de entrada.
     */
    public LocalDateTime getSaida() {
        return saida;
    }

    /**
     * Obtém a vaga associada ao uso.
     *
     * @return A vaga associada.
     */
    public Vaga getVaga() {
        return this.vaga;
    }

    /**
     * Define o cliente associado ao uso.
     *
     * @param cliente O cliente associado ao uso.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Registra o uso da vaga com a data e hora de entrada especificadas.
     *
     * @param veiculo O veículo utilizando a vaga.
     * @param entrada A data e hora de entrada na vaga.
     */
    public void usarVaga(Veiculo veiculo, LocalDateTime entrada) {
        if (this.entrada == null) {
            this.entrada = entrada;
            setCliente(veiculo.getCliente());
        }
    }

    /**
     * Registra a saída do veículo da vaga com a data e hora de saída especificadas.
     *
     * @param saida A data e hora de saída da vaga.
     * @return O valor a ser pago pelo uso da vaga.
     */
    public double sair(LocalDateTime saida) {
        this.saida = saida;
        return calcularCusto(this.cliente);
    }

    /**
     * Calcula o custo total para o uso de uma vaga de estacionamento considerando o tipo de cliente.
     *
     * @param veiculo  O veículo utilizado.
     * @param entrada  A data e hora de entrada.
     * @param saida    A data e hora de saída.
     * @return O custo total do uso da vaga.
     */
    public double calcularCusto(Cliente cliente) {
        if (cliente != null && cliente.getModalidade() != null) {
            if (cliente.getModalidade().equals(Cliente.ModalidadeCliente.HORISTA)) {
                CalculadorCustoHorista custo = new CalculadorCustoHorista();
                valorPago = custo.calcularCusto(this.entrada, this.saida);

                return valorPago;
            } else if (cliente.getModalidade().equals(Cliente.ModalidadeCliente.DE_TURNO)) {
                CalculadorCustoTurnista custo = new CalculadorCustoTurnista(cliente);
                valorPago = custo.calcularCusto(this.entrada, this.saida);

                return valorPago;
            } else if (cliente.getModalidade().equals(Cliente.ModalidadeCliente.MENSALISTA)) {
                CalculadorCustoMensalista custo = new CalculadorCustoMensalista();
                valorPago = custo.calcularCusto(this.entrada, this.saida);

                return valorPago;
            }
        }
        return valorPago;
    }

}