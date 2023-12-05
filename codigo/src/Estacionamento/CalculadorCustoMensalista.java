import java.time.LocalDateTime;

public class CalculadorCustoMensalista implements ICalculadorCusto {

    private double custoBase;

    @Override
    public double calcularCusto(LocalDateTime entrada, LocalDateTime saida) {
        return 500.0 + custoBase;
    }

    public double adicionarCustoServico(Servico servico) {
        if (servico != null) {
            return custoBase + servico.getValor();
        }
        return custoBase;
    }
}
