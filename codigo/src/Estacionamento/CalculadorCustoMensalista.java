import java.time.LocalDateTime;

public class CalculadorCustoMensalista implements ICalculadorCusto {

    private double custoBase = 0.0;

    @Override
    public double calcularCusto(LocalDateTime entrada, LocalDateTime saida) {
        return custoBase;
    }
}
