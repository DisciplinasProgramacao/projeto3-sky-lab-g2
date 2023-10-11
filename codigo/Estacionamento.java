import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private String nome;
    private Cliente[] clientes;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private double totalArrecadado;
    private List<RegistroEstacionamento> registros;

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.vagas = new Vaga[fileiras * vagasPorFila];
        this.clientes = new Cliente[100]; // Defina um limite máximo de clientes
        this.registros = new ArrayList<>();
        this.totalArrecadado = 0.0;
        gerarVagas();
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente cliente = encontrarCliente(idCli);
        if (cliente != null) {
            cliente.addVeiculo(veiculo);
        }
    }

    public void addCliente(Cliente cliente) {
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] == null) {
                clientes[i] = cliente;
                return;
            }
        }
    }

    private void gerarVagas() {
        char fila = 'A';
        int numeroVaga = 1;

        for (int i = 0; i < vagas.length; i++) {
            vagas[i] = new Vaga(String.valueOf(fila) + numeroVaga);
            numeroVaga++;
            if (numeroVaga > vagasPorFileira) {
                numeroVaga = 1;
                fila++;
            }
        }
    }

    public void estacionar(String placa) {
        Cliente cliente = encontrarClientePorPlaca(placa);

        if (cliente == null) {
            System.out.println("Cliente não encontrado para a placa " + placa);
            return;
        }

        Vaga vagaDisponivel = encontrarVagaDisponivel();

        if (vagaDisponivel == null) {
            System.out.println("Não há vagas disponíveis.");
            return;
        }

        RegistroEstacionamento registro = new RegistroEstacionamento(cliente, vagaDisponivel, placa);
        registros.add(registro);
        totalArrecadado += registro.getValor();
        vagaDisponivel.estacionar();
    }

    public double sair(String placa) {
        RegistroEstacionamento registro = encontrarRegistroPorPlaca(placa);

        if (registro == null) {
            System.out.println("Registro não encontrado para a placa " + placa);
            return 0.0;
        }

        registro.getVaga().sair();
        registros.remove(registro);
        return registro.getValor();
    }

    public double totalArrecadado() {
        return totalArrecadado;
    }

    public double arrecadacaoNoMes(int mes) {
        double arrecadacao = 0.0;
        for (RegistroEstacionamento registro : registros) {
            if (registro.getData().getMonthValue() == mes) {
                arrecadacao += registro.getValor();
            }
        }
        return arrecadacao;
    }

    public double valorMedioPorUso() {
        if (registros.isEmpty()) {
            return 0.0;
        }

        double totalValor = 0.0;
        for (RegistroEstacionamento registro : registros) {
            totalValor += registro.getValor();
        }

        return totalValor / registros.size();
    }

    public String top5Clientes(int mes) {
        // Implemente a lógica para encontrar os 5 principais clientes com base na arrecadação do mês especificado.
    }

    private Cliente encontrarCliente(String idCli) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getId().equals(idCli)) {
                return cliente;
            }
        }
        return null;
    }

    private Vaga encontrarVagaDisponivel() {
        for (Vaga vaga : vagas) {
            if (vaga.isDisponivel()) {
                return vaga;
            }
        }
        return null;
    }

    private RegistroEstacionamento encontrarRegistroPorPlaca(String placa) {
        for (RegistroEstacionamento registro : registros) {
            if (registro.getPlaca().equals(placa)) {
                return registro;
            }
        }
        return null;
    }

    private Cliente encontrarClientePorPlaca(String placa) {
        for (RegistroEstacionamento registro : registros) {
            if (registro.getPlaca().equals(placa)) {
                return registro.getCliente();
            }
        }
        return null;
    }
}
