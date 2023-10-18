public class EstacionamentoTest {

    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento("Meu Estacionamento", 5, 10);

        Cliente cliente1 = new Cliente("Cliente1");
        Cliente cliente2 = new Cliente("Cliente2");
        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);

        Veiculo veiculo1 = new Veiculo("ABC123");
        Veiculo veiculo2 = new Veiculo("DEF456");

        estacionamento.addVeiculo(veiculo1, cliente1.getId());
        estacionamento.addVeiculo(veiculo2, cliente2.getId());

        estacionamento.estacionar("ABC123");
        estacionamento.estacionar("DEF456");

        // Simule a saída dos veículos e calcule o valor pago
        double valorSaida1 = estacionamento.sair("ABC123");
        double valorSaida2 = estacionamento.sair("DEF456");

        System.out.println("Valor pago por ABC123: " + valorSaida1);
        System.out.println("Valor pago por DEF456: " + valorSaida2);

        double totalArrecadado = estacionamento.totalArrecadado();
        System.out.println("Total arrecadado: " + totalArrecadado);

        double arrecadacaoNoMes = estacionamento.arrecadacaoNoMes(10); // Substitua o mês pelo mês desejado
        System.out.println("Arrecadação no mês 10: " + arrecadacaoNoMes);

        double valorMedio = estacionamento.valorMedioPorUso();
        System.out.println("Valor médio por uso: " + valorMedio);

        String topClientes = estacionamento.top5Clientes(10); // Substitua o mês pelo mês desejado
        System.out.println("Top 5 clientes do mês 10: " + topClientes);
    }
}
