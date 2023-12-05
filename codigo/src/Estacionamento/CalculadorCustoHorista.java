import java.time.Duration;
import java.time.LocalDateTime;

public class CalculadorCustoHorista implements ICalculadorCusto {
    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private double custoBase;

    @Override
    public double calcularCusto(LocalDateTime entrada, LocalDateTime saida) {
        if (entrada == null || saida == null) {
            return VALOR_FRACAO;
        }

        Duration duracao = Duration.between(entrada, saida);
        duracao = duracao.plusMinutes(15 - (duracao.toMinutes() % 15));
        long minutosEstacionados = duracao.toMinutes();

        if (minutosEstacionados <= 60) {
            custoBase = Math.min(VALOR_FRACAO, VALOR_MAXIMO);
        } else {
            double valorExcedente = Math.ceil((minutosEstacionados - 60) / 15.0) * FRACAO_USO * VALOR_FRACAO;
            custoBase = Math.min(valorExcedente, VALOR_MAXIMO);
        }

        return custoBase;
    }

    public double adicionarCustoServico(Servico servico) {
        if (servico != null) {
            return custoBase + servico.getValor();
        }
        return custoBase;
    }
}
