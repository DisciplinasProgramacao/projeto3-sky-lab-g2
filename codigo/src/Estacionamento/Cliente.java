import java.util.List;

/**
 * Classe que representa um cliente do estacionamento.
 */
public class Cliente {

    private String nome;
    private String id;
    private Veiculo[] veiculos;
    private int numVeiculos;
    private List<UsoDeVaga> usosDeVaga;

    /**
     * Construtor da classe Cliente.
     *
     * @param nome O nome do cliente.
     * @param id   O identificador único do cliente.
     */
    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new Veiculo[10];
        this.numVeiculos = 0;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Adiciona um uso de vaga à lista de usos do cliente.
     *
     * @param usoDeVaga O uso de vaga a ser adicionado.
     */
    public void adicionarUsoDeVaga(UsoDeVaga usoDeVaga) {
        usosDeVaga.add(usoDeVaga);
    }

    /**
     * Obtém a lista de usos de vaga do cliente.
     *
     * @return A lista de usos de vaga do cliente.
     */
    public List<UsoDeVaga> getUsosDeVaga() {
        return usosDeVaga;
    }

    /**
     * Obtém a lista de veículos do cliente.
     *
     * @return A lista de veículos do cliente.
     */
    public Veiculo[] getVeiculos() {
        return veiculos;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome O novo nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o identificador único do cliente.
     *
     * @return O identificador único do cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Define o identificador único do cliente.
     *
     * @param id O novo identificador único do cliente.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Adiciona um veículo à lista de veículos do cliente.
     *
     * @param veiculo O veículo a ser adicionado.
     */
    public void addVeiculo(Veiculo veiculo) {
        if (numVeiculos < veiculos.length) {
            veiculos[numVeiculos] = veiculo;
            numVeiculos++;
        } else {
            System.out.println("Limite de veículos atingido para este cliente.");
        }
    }

    /**
     * Verifica se o cliente possui um veículo com a placa especificada.
     *
     * @param placa A placa do veículo a ser verificada.
     * @return O veículo com a placa especificada, ou null se o cliente não o possuir.
     */
    public Veiculo possuiVeiculo(String placa) {
        for (int i = 0; i < numVeiculos; i++) {
            if (veiculos[i].getPlaca().equals(placa)) {
                return veiculos[i];
            }
        }
        return null;
    }

    /**
     * Obtém o total de usos de vaga realizados pelo cliente.
     *
     * @return O total de usos de vaga do cliente.
     */
    public int totalDeUsos() {
        int totalUsos = 0;
        for (int i = 0; i < numVeiculos; i++) {
            totalUsos += veiculos[i].totalDeUsos();
        }
        return totalUsos;
    }

    /**
     * Obtém o valor total arrecadado pelo cliente por um veículo com a placa especificada.
     *
     * @param placa A placa do veículo.
     * @return O valor total arrecadado pelo veículo com a placa especificada.
     */
    public double arrecadadoPorVeiculo(String placa) {
        Veiculo veiculo = possuiVeiculo(placa);
        if (veiculo != null) {
            return veiculo.totalArrecadado();
        } else {
            return 0.0;
        }
    }

    /**
     * Obtém o valor total arrecadado pelo cliente por todos os veículos.
     *
     * @return O valor total arrecadado pelo cliente.
     */
    public double arrecadadoTotal() {
        double totalArrecadado = 0.0;
        for (int i = 0; i < numVeiculos; i++) {
            totalArrecadado += veiculos[i].totalArrecadado();
        }
        return totalArrecadado;
    }

    /**
     * Obtém o valor arrecadado pelo cliente no mês especificado.
     *
     * @param mes O mês para o qual se deseja calcular a arrecadação.
     * @return O valor arrecadado pelo cliente no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double arrecadadoMes = 0.0;
        for (int i = 0; i < numVeiculos; i++) {
            arrecadadoMes += veiculos[i].arrecadadoNoMes(mes);
        }
        return arrecadadoMes;
    }
}
