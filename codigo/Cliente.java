/**
 * Classe que representa um cliente do serviço de estacionamento.
 *
 * @author José Carlos Macoratti
 * @version 1.0
 */
public class Cliente {

    /** Nome do cliente */
    private String nome;

    /** ID do cliente */
    private String id;

    /** Array de veículos pertencentes ao cliente */
    private Veiculo[] veiculos;

    /**
     * Construtor da classe Cliente
     *
     * @param nome Nome do cliente
     * @param id ID do cliente
     */
    public Cliente(String nome, String id) {
        
    }

    /**
     * Adiciona um veículo ao cliente
     *
     * @param veiculo Veículo a ser adicionado
     */
    public void addVeiculo(Veiculo veiculo) {
        
    }

    /**
     * Verifica se o cliente possui um veículo com a placa informada
     *
     * @param placa Placa do veículo
     * @return Veículo, se encontrado. Caso contrário, nulo.
     */
    public Veiculo possuiVeiculo(String placa) {
        
    }

    /**
     * Retorna a quantidade total de vezes que os veículos do cliente foram usados
     *
     * @return Total de vezes que os veículos do cliente foram usados
     */
    public int totalDeUsos() {
        
    }

    /**
     * Retorna o valor arrecadado com a utilização do veículo de placa informada
     *
     * @param placa Placa do veículo
     * @return Valor arrecadado
     */
    public double arrecadadoPorVeiculo(String placa) {
        
    }

    /**
     * Retorna o valor total arrecadado com a utilização dos veículos do cliente
     *
     * @return Valor total arrecadado
     */
