// A classe EstacionamentoTest é responsável por testar a funcionalidade do sistema de estacionamento.
public class EstacionamentoTest {

    // O método principal que é executado quando o programa é iniciado.
    public static void main(String[] args) {
        // Cria uma instância da classe Estacionamento com capacidade para 5 clientes e 10 veículos.
        Estacionamento estacionamento = new Estacionamento("Meu Estacionamento", 5, 10);

        // Cria duas instâncias da classe Cliente e adiciona ao estacionamento.
        Cliente cliente1 = new Cliente("Cliente1");
        Cliente cliente2 = new Cliente("Cliente2");
        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);

        // Cria duas instâncias da classe Veiculo.
        Veiculo veiculo1 = new Veiculo("ABC123");
        Veiculo veiculo2 = new Veiculo("DEF456");

        // Adiciona os veículos aos clientes no estacionamento.
        estacionamento.addVeiculo(veiculo1, cliente1.getId());
        estacionamento.addVeiculo(veiculo2, cliente2.getId());

        // Estaciona os veículos no estacionamento.
        estacionamento.estacionar("ABC123");
        estacionamento.estacionar("DEF456");

        // Simula a saída dos veículos e calcula o valor pago.
        double valorSaida1 = estacionamento.sair("ABC123");
        double valorSaida2 = estacionamento.sair("DEF456");

        // Exibe o valor pago por cada veículo.
        System.out.println("Valor pago por ABC123: " + valorSaida1);
        System.out.println("Valor pago por DEF456: " + valorSaida2);

        // Calcula e exibe o total arrecadado pelo estacionamento.
        double totalArrecadado = estacionamento.totalArrecadado();
        System.out.println("Total arrecadado: " + totalArrecadado);

        // Calcula e exibe a arrecadação no mês 10.
        double arrecadacaoNoMes = estacionamento.arrecadacaoNoMes(10);
        System.out.println("Arrecadação no mês 10: " + arrecadacaoNoMes);

        // Calcula e exibe o valor médio por uso do estacionamento.
        double valorMedio = estacionamento.valorMedioPorUso();
        System.out.println("Valor médio por uso: " + valorMedio);

        // Obtém e exibe os top 5 clientes do mês 10.
        String topClientes = estacionamento.top5Clientes(10); // Substitua o mês pelo mês desejado
        System.out.println("Top 5 clientes do mês 10: " + topClientes);
    }
}
