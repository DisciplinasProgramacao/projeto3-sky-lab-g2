import java.time.LocalDateTime;

// A classe UsoDeVaga representa o uso de uma vaga de estacionamento por um veículo.
public class UsoDeVaga {

    // Constantes para cálculo do valor de pagamento.
    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    // A vaga associada ao uso.
    private Vaga vaga;

    // Data e hora de entrada do veículo na vaga.
    private LocalDateTime entrada;

    // Data e hora de saída do veículo da vaga.
    private LocalDateTime saida;

    // Valor pago pelo uso da vaga.
    private double valorPago;

    // Construtor da classe UsoDeVaga.
    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.entrada = LocalDateTime.now(); // Define a entrada como o momento atual.
        this.saida = null; // A saída ainda não ocorreu.
        this.valorPago = 0.0; // Inicializa o valor pago como 0.
    }

    // Registra a saída do veículo da vaga e calcula o valor a ser pago.
    public double sair() {
        // Implementação do método.
    }

    // Retorna o valor pago pelo uso da vaga.
    public double valorPago() {
        // Implementação do método.
    }
}
