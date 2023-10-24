import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class App {

    public static void main(String[] args) {
        long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        LocalDateTime now = LocalDateTime.now();

        Estacionamento estacionamento = new Estacionamento("Estacionamento 4", 4, 4);

        for (int j = 0; j < 16; j++) {
            Cliente cliente = new Cliente("Cliente " + (j + 1), "ID" + (j + 1));
            Veiculo veiculo = new Veiculo("TES-" + (7700 + j));
            cliente.addVeiculo(veiculo);
            estacionamento.addCliente(cliente);
        }

        for (int k = 0; k < 16; k++) {
            int clienteIndex = random.nextInt(estacionamento.getClientes().size());
            Cliente cliente = estacionamento.getClientes().get(clienteIndex);
            Veiculo veiculo = cliente.getVeiculos()[0];

            LocalDateTime entrada = now.minusDays(random.nextInt(30)).plusHours(random.nextInt(24));
            estacionamento.estacionar(veiculo.getPlaca(), entrada);

            if (random.nextDouble() > 0.3) {
                int servicoIndex = random.nextInt(Servico.values().length);
                Servico servico = Servico.values()[servicoIndex];
                estacionamento.contratarServico(veiculo.getPlaca(), servico);
            }

            LocalDateTime saida = entrada.plusHours(random.nextInt(12) + 3);
            estacionamento.sair(veiculo.getPlaca(), saida);
        }

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
