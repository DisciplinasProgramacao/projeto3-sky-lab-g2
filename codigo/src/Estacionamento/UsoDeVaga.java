import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumSet;

/**
 * Classe que representa o uso de uma vaga de estacionamento por um cliente.
 */
public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private EnumSet<Servico> servicosContratados;

    /**
     * Construtor que cria uma instância de UsoDeVaga associada a uma vaga.
     *
     * @param vaga A vaga utilizada no uso.
     */
    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.valorPago = 0.0;
        this.servicosContratados = EnumSet.noneOf(Servico.class);
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
     * Obtém a vaga associada ao uso.
     *
     * @return A vaga associada.
     */
    public Vaga getVaga() {
        return this.vaga;
    }

    /**
     * Registra o uso da vaga com a data e hora de entrada especificadas.
     *
     * @param vaga    A vaga utilizada.
     * @param entrada A data e hora de entrada na vaga.
     */
    public void usarVaga(Vaga vaga, LocalDateTime entrada) {
        if (this.entrada == null) {
            this.entrada = entrada;
            vaga.disponivel();
        }
    }

    /**
     * Registra a saída do veículo da vaga com a data e hora de saída especificadas.
     *
     * @param saida A data e hora de saída da vaga.
     */
    public void sair(LocalDateTime saida) {
        if (entrada != null) {
            this.saida = saida;
            valorPago();
            vaga.disponivel();
        } else {
            // Condição não especificada
        }
    }

    /**
     * Contrata um serviço adicional para o uso da vaga.
     *
     * @param servico O serviço a ser contratado.
     */
    public void contratarServico(Servico servico) {
        servicosContratados.add(servico);
    }

    /**
     * Calcula o valor a ser pago pelo uso da vaga.
     *
     * @return O valor a ser pago.
     */
    public double valorPago() {
        if (entrada != null && saida != null) {
            Duration duracao = Duration.between(entrada, saida);

            for (Servico servico : servicosContratados) {
                duracao = duracao.plus(servico.getTempoMinimo());
            }

            long minutosEstacionados = duracao.toMinutes();

            if (minutosEstacionados <= 60) {
                this.valorPago = VALOR_FRACAO;
            } else {
                double valorExcedente = Math.ceil((minutosEstacionados - 60) / 15.0) * VALOR_FRACAO * FRACAO_USO;
                this.valorPago = Math.min(valorExcedente, VALOR_MAXIMO);
            }

            for (Servico servico : servicosContratados) {
                this.valorPago += servico.getValor();
            }

            return valorPago;
        } else {
            return 4.0; // Taxa mínima
        }
    }
}
