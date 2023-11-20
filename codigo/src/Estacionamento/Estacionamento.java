import java.util.*;
import java.time.LocalDateTime;

/**
 * Classe que representa um estacionamento e suas operações relacionadas a clientes, veículos e vagas.
 */
public class Estacionamento implements IDataToText {

    private String nome;
    private List<Cliente> clientes;
    private List<Vaga> vagas;
    private int quantFileiras;
    private int vagasPorFileira;

    /**
     * Construtor que cria uma instância de estacionamento com o nome e configurações especificados.
     *
     * @param nome            O nome do estacionamento.
     * @param fileiras        A quantidade de fileiras de vagas no estacionamento.
     * @param vagasPorFila    O número de vagas por fileira.
     */
    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new ArrayList<>();
        this.vagas = new ArrayList<>();
        gerarVagas();
    }

    /**
     * Adiciona um veículo a um cliente existente no estacionamento.
     *
     * @param veiculo O veículo a ser adicionado.
     * @param idCli   O identificador do cliente ao qual o veículo será associado.
     */
    public void addVeiculo(Veiculo veiculo, String idCli) throws VeiculoJaExistenteException, ClienteNaoExisteException {
        Cliente cliente = encontrarCliente(idCli);
        if (cliente != null) {
            if (cliente.possuiVeiculo(veiculo.getPlaca()) == null) {
                throw new VeiculoJaExistenteException();
            } else {
                cliente.addVeiculo(veiculo);
            }
        } else {
            throw new ClienteNaoExisteException();
        }
    }

    /**
     * Adiciona um cliente ao estacionamento.
     *
     * @param cliente O cliente a ser adicionado.
     */
    public void addCliente(Cliente cliente) throws ClienteJaExistenteException {
        if (clientes.contains(cliente)) {
            throw new ClienteJaExistenteException();
        } else {
            clientes.add(cliente);
        }
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

    /**
     * Estaciona um veículo em uma vaga disponível no estacionamento.
     *
     * @param placa   A placa do veículo a ser estacionado.
     * @param entrada A data e hora de entrada do veículo na vaga.
     * @throws VeiculoNaoExisteException
     */
    public void estacionar(Veiculo veiculo, Vaga vaga, LocalDateTime entrada)
            throws VagaOcupadaException, VeiculoNaoExisteException {
        veiculo.estacionar(vaga, entrada);
    }

    /**
     * Registra a saída de um veículo do estacionamento e calcula o valor a ser pago.
     *
     * @param placa A placa do veículo que está saindo.
     * @param saida A data e hora de saída do veículo.
     * @return O valor a ser pago pelo uso da vaga.
     */
    public void sair(Veiculo veiculo, Vaga vaga, LocalDateTime saida) throws UsoDeVagaFinalizadoException, VeiculoNaoExisteException {
        veiculo.sair(vaga, saida);
    }

    /**
     * Calcula o valor total arrecadado pelo estacionamento.
     *
     * @return O valor total arrecadado.
     */
    public double totalArrecadado() {
        double total = 0;
        for (Cliente cliente : clientes) {
            total += cliente.arrecadadoTotal();
        }
        return total;
    }

    /**
     * Calcula a arrecadação total do estacionamento em um mês específico.
     *
     * @param mes O mês para o qual a arrecadação será calculada.
     * @return A arrecadação total no mês especificado.
     */
    public double arrecadacaoNoMes(int mes) {
        double total = 0;
        for (Cliente cliente : clientes) {
            total += cliente.arrecadadoNoMes(mes);
        }
        return total;
    }

    /**
     * Calcula o valor médio arrecadado por uso de vaga no estacionamento.
     *
     * @return O valor médio por uso de vaga.
     */
    public double valorMedioPorUso() {
        double media = 0;
        double soma = 0;
        int numClientes = 0;
        for (Cliente cliente : clientes) {
            soma += cliente.arrecadadoTotal();
            numClientes++;
        }
        media = soma / numClientes;
        return media;
    }

    /**
     * Obtém uma lista dos cinco melhores clientes em termos de arrecadação em um mês específico.
     *
     * @param mes O mês para o qual o ranking dos clientes será calculado.
     * @return Uma string que lista os cinco melhores clientes no mês especificado.
     */
    public String top5Clientes(int mes) {
        List<Cliente> topClientes = new ArrayList<>(clientes);
        topClientes.sort((c1, c2) -> {
            double arrecadacao1 = c1.arrecadadoNoMes(mes);
            double arrecadacao2 = c2.arrecadadoNoMes(mes);
            if (arrecadacao1 > arrecadacao2) {
                return -1;
            } else if (arrecadacao1 < arrecadacao2) {
                return 1;
            } else {
                return 0;
            }
        });
    
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

        public Cliente encontrarCliente(String idCli) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCli)) {
                return cliente;
            }
        }

        return null;
        //duvida
    }

    public Veiculo encontrarVeiculo(String placa) {
        for (Cliente cliente : clientes) {
            Veiculo veiculo = cliente.possuiVeiculo(placa);
            if (veiculo != null) {
                return veiculo;
            }
        }

        //duvida
        return null;
    }

    /**
     * Contrata um serviço adicional para um veículo que está usando uma vaga no estacionamento.
     *
     * @param placa   A placa do veículo ao qual o serviço será adicionado.
     * @param servico O serviço a ser contratado.
     * @return true se o serviço foi contratado com sucesso, false caso contrário.
     */
    public boolean contratarServico(String placa, Servico servico) {
        Veiculo veiculo = encontrarVeiculo(placa);

        if (veiculo != null) {
            UsoDeVaga ultimoUso = veiculo.getUltimoUso();

            if (ultimoUso != null && ultimoUso.getVaga().disponivel()) {
                // A vaga precisa estar ocupada para contratar um serviço adicional
                ultimoUso.contratarServico(servico);
                return true;
            }
        }

        return false;
    }

    @Override
    public String dataToText() {
        StringBuilder data = new StringBuilder();
        data.append("Nome do Estacionamento: ").append(nome).append("\n");
        data.append("Quantidade de Fileiras: ").append(quantFileiras).append("\n");
        data.append("Vagas por Fileira: ").append(vagasPorFileira).append("\n\n");

        // Informações sobre os clientes
        data.append("Clientes:\n");
        for (Cliente cliente : clientes) {
            data.append("Nome: ").append(cliente.getNome()).append(", ID: ").append(cliente.getId()).append("\n");

            // Informações sobre os veículos do cliente
            data.append("Veículos do Cliente:\n");
            Veiculo[] veiculos = cliente.getVeiculos();
            for (Veiculo veiculo : veiculos) {
                if (veiculo != null) {
                    data.append("Placa: ").append(veiculo.getPlaca()).append(", custando: R$").append(cliente.arrecadadoPorVeiculo(veiculo.getPlaca())).append("\n");
                }
            }

            data.append("\n");
        }

        data.append("Vagas:\n");
        for (Vaga vaga : vagas) {
            data.append("ID da Vaga: ").append(vaga.getId()).append(", Disponível: ").append(vaga.disponivel() ? "Desocupada" : "Ocupada").append("\n");
        }

        return data.toString();
    }

    /**
     * Obtém o nome do estacionamento.
     *
     * @return O nome do estacionamento.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém a quantidade de fileiras de vagas no estacionamento.
     *
     * @return A quantidade de fileiras.
     */
    public int getQuantFileiras() {
        return quantFileiras;
    }

    /**
     * Obtém o número de vagas por fileira no estacionamento.
     *
     * @return O número de vagas por fileira.
     */
    public int getVagasPorFileira() {
        return vagasPorFileira;
    }

    /**
     * Obtém a lista de clientes do estacionamento.
     *
     * @return A lista de clientes.
     */
    public List<Cliente> getClientes() {
        return clientes;
    }

}
