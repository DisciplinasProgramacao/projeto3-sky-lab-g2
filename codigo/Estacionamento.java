import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Estacionamento {

    private String nome;
    private List<Cliente> clientes;
    private List<Vaga> vagas;
    private int quantFileiras;
    private int vagasPorFileira;

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new ArrayList<>();
        this.vagas = new ArrayList<>();
        gerarVagas();
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente cliente = encontrarCliente(idCli);
        if (cliente != null) {
            cliente.addVeiculo(veiculo);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    private void gerarVagas() {
        int numeroVaga = 1;
        for (int fileira = 1; fileira <= quantFileiras; fileira++) {
            for (int vaga = 1; vaga <= vagasPorFileira; vaga++) {
                Vaga novaVaga = new Vaga("V" + numeroVaga);
                vagas.add(novaVaga);
                numeroVaga++;
            }
        }
    }

    public void estacionar(String placa, LocalDateTime entrada) {
        Veiculo veiculo = encontrarVeiculo(placa);
        if (veiculo != null) {
            for (Vaga vaga : vagas) {
                if (vaga.estacionar()) {
                    veiculo.estacionar(vaga, entrada);
                    System.out.println("Veículo " + placa + " estacionado na vaga " + vaga.getId());
                    return;
                }
            }
            System.out.println("Não há vagas disponíveis.");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public double sair(String placa, LocalDateTime saida) {
        Veiculo veiculo = encontrarVeiculo(placa);
        if (veiculo != null) {
            for (Vaga vaga : vagas) {
                if (veiculo.sair(vaga, saida) > 0) {
                    System.out.println("Veículo " + placa + " saiu da vaga " + vaga.getId());
                    return veiculo.sair(vaga, LocalDateTime.now());
                }
            }
            System.out.println("Veículo não encontrado nas vagas.");
        } else {
            System.out.println("Veículo não encontrado.");
        }
        return 0.0;
    }

    public double totalArrecadado() {
        double total = 0;
        for (Cliente cliente : clientes) {
            total += cliente.arrecadadoTotal();
        }
        return total;
    }

    public double arrecadacaoNoMes(int mes) {
        double total = 0;
        for (Cliente cliente : clientes) {
            total += cliente.arrecadadoNoMes(mes);
        }
        return total;
    }

    public double valorMedioPorUso() {
        double media = 0;
        double soma = 0;
        int numClientes = 0;
        for (Cliente cliente : clientes) {
            soma += cliente.arrecadadoTotal();
            numClientes++;
        }
        media = soma/numClientes;
        return media;
    }

    public String top5Clientes(int mes) {
        List<Cliente> topClientes = new ArrayList<>(clientes);
        topClientes.sort((c1, c2) -> Double.compare(c2.arrecadadoNoMes(mes), c1.arrecadadoNoMes(mes)));

        StringBuilder result = new StringBuilder("Top 5 Clientes no mês " + mes + ":\n");
        int count = 0;
        for (Cliente cliente : topClientes) {
            if (count < 5) {
                result.append(cliente.getNome()).append(": R$ ").append(cliente.arrecadadoNoMes(mes)).append("\n");
                count++;
            } else {
                break;
            }
        }
        return result.toString();
    }

    private Cliente encontrarCliente(String idCli) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCli)) {
                return cliente;
            }
        }
        return null;
    }

    private Veiculo encontrarVeiculo(String placa) {
        for (Cliente cliente : clientes) {
            Veiculo veiculo = cliente.possuiVeiculo(placa);
            if (veiculo != null) {
                return veiculo;
            }
        }
        return null;
    }
}
