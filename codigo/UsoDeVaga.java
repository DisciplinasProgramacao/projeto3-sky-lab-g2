import java.time.Duration;
import java.time.LocalDateTime;

public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;
    
    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.entrada = null;
        this.saida = null;
        this.valorPago = 0.0;
    }

    public void usarVaga() {
        if (entrada == null) {
            entrada = LocalDateTime.now();
            vaga.setOcupada(true); // Marcar a vaga como ocupada
        } else {
            System.out.println("A vaga já está em uso.");
        }
    }

    public void sair() {
        if (entrada != null) {
            this.saida = LocalDateTime.now();
            Duration duracao = Duration.between(entrada, saida);
            long minutosEstacionados = duracao.toMinutes();

            // Calcula o valor a ser pago com base no tempo de estacionamento
            if (minutosEstacionados <= 15) {
                this.valorPago = 0.0;
            } else if (minutosEstacionados <= 60) {
                this.valorPago = VALOR_FRACAO;
            } else {
                double valorExcedente = Math.ceil((minutosEstacionados - 60) / 15.0) * VALOR_FRACAO;
                this.valorPago = Math.min(valorExcedente, VALOR_MAXIMO);
            }
            vaga.setOcupada(false); // Marcar a vaga como desocupada
        } else {
            System.out.println("Você não usou a vaga.");
        }
    }

    public double valorPago() {
        return valorPago;
    }
}
