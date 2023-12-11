public class CalculadorCustoServico {

    private double custoServico=0.0;

    public double adicionarCustoServico(Servico servico) {
        if (servico != null) {
            custoServico += servico.getValor();
            return custoServico;
        }
        return custoServico;
    }
}
