import java.time.LocalDateTime;
import java.time.Duration;

public class CalculadorCustoHorista implements ICalculadorCusto {

    /**
     * Calcula o custo do estacionamento com base nos horários de entrada e saída.
     * O custo é de R$4 a cada 15 minutos, com um limite máximo de R$50.
     *
     * @param entrada O LocalDateTime de entrada.
     * @param saida   O LocalDateTime de saída.
     * @return O custo total do estacionamento.
     */
    @Override
    public double calcularCusto(LocalDateTime entrada, LocalDateTime saida) {
        Duration duration = Duration.between(entrada, saida);

        double minutos = duration.toMinutes();
        double fracaoMinutos = Math.floor(minutos / 15);

        double valorFracao = 4;
        double valorMaximo = 50;

        if (minutos<=60) {
            return valorFracao;
        } else {
            return Math.min(fracaoMinutos * valorFracao, valorMaximo);            
        }
    }
}

