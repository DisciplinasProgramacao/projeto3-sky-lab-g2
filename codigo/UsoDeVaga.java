import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumSet;

public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private EnumSet<Servico> servicosContratados;

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.valorPago = 0.0;
        this.servicosContratados = EnumSet.noneOf(Servico.class);
    }

    public void usarVaga(Vaga vaga, LocalDateTime entrada) {
        if (this.entrada == null) {
            this.entrada = entrada;
            vaga.disponivel(false);
        } else {
            System.out.println("A vaga já está em uso.");
        }
    }

    public void sair(LocalDateTime saida) {
        if (entrada != null) {
            this.saida = saida;
            valorPago();
            vaga.disponivel(true);
        } else {
            System.out.println("Você não usou a vaga.");
        }
    }

    public void contratarServico(Servico servico) {
        servicosContratados.add(servico);
    }

    public double valorPago() {
        if (entrada != null && saida != null) {
            Duration duracao = Duration.between(entrada, saida);

            for (Servico servico : servicosContratados) {
                duracao = duracao.plus(servico.getTempoMinimo());
            }

            long minutosEstacionados = duracao.toMinutes();

            if (minutosEstacionados <= 15) {
                this.valorPago = 0.0;
            } else if (minutosEstacionados <= 60) {
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
            System.out.println("Entrada ou saída nula. Não é possível calcular o valor.");
            return 0.0;
        }
    }
}
