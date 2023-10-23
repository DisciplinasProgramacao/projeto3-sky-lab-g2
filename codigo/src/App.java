import java.io.IOException;
import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento("Meu Estacionamento", 2, 5);

        Cliente cliente1 = new Cliente("Cliente 1", "ID1");
        Cliente cliente2 = new Cliente("Cliente 2", "ID2");

        Veiculo veiculo1 = new Veiculo("ABC-1234");
        Veiculo veiculo2 = new Veiculo("XYZ-5678");

        cliente1.addVeiculo(veiculo1);
        cliente2.addVeiculo(veiculo2);

        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);

        LocalDateTime entradaVeiculo = LocalDateTime.of(2023, 10, 23, 10, 0);
        LocalDateTime saidaVeiculo = LocalDateTime.of(2023, 10, 23, 12, 0);

        System.out.println("Teste de estacionamento:");

        System.out.println("Estacionando veículo ABC-1234...");
        estacionamento.estacionar("ABC-1234", entradaVeiculo);

        System.out.println("Estacionando veículo XYZ-5678...");
        estacionamento.estacionar("XYZ-5678",entradaVeiculo);

        System.out.println("Veículo ABC-1234 saindo...");
        double valorPago1 = estacionamento.sair("ABC-1234", saidaVeiculo);
        System.out.println("Valor pago pelo veículo ABC-1234: R$" + valorPago1);

        System.out.println("Veículo XYZ-5678 saindo...");
        double valorPago2 = estacionamento.sair("XYZ-5678", saidaVeiculo);
        System.out.println("Valor pago pelo veículo XYZ-5678: R$" + valorPago2);

        System.out.println("Total arrecadado no estacionamento: R$" + estacionamento.totalArrecadado());
        System.out.println("Arrecadação no mês 10: R$" + estacionamento.arrecadacaoNoMes(10));
        System.out.println("Valor médio por uso: R$" + estacionamento.valorMedioPorUso());

        System.out.println(estacionamento.top5Clientes(10));

        EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO("estacionamento.txt");
        try {
            estacionamentoDAO.abrirEscrita();
            estacionamentoDAO.add(estacionamento);
            estacionamentoDAO.fechar();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Estacionamento[] estacionamentos;
        try {
            estacionamentoDAO.abrirLeitura();
            estacionamentos = estacionamentoDAO.getAll();
            estacionamentoDAO.fechar();
        } catch (IOException e) {
            e.printStackTrace();
            estacionamentos = new Estacionamento[0];
        }

        for (Estacionamento e : estacionamentos) {
            System.out.println("Nome: " + e.getNome());
            System.out.println("Quantidade de Fileiras: " + e.getQuantFileiras());
            System.out.println("Vagas por Fileira: " + e.getVagasPorFileira());
            System.out.println();
        }
    }
}
