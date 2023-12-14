import java.util.*;

/**
 * Classe que representa um cliente do estacionamento.
 */
public class Cliente {

    public enum ModalidadeCliente {
        HORISTA(new CalculadorCustoHorista()), 
        DE_TURNO(new CalculadorCustoTurnista(Turno.MANHA)), 
        MENSALISTA(new CalculadorCustoMensalista());
        ICalculadorCusto meuCalc;

        ModalidadeCliente(ICalculadorCusto c){
            meuCalc = c;
        }
        
        public ICalculadorCusto getCalc(){
            return this.meuCalc;
        }
    }

    private String nome;
    private String id;
    private List<Veiculo> veiculos;
    private ModalidadeCliente modalidade;
    private Turno turnoEscolhido;

    /**
     * Construtor da classe Cliente.
     *
     * @param nome O nome do cliente.
     * @param id   O identificador único do cliente.
     */
    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new ArrayList<>();
    }

    /**
     * Obtém a modalidade de cliente.
     *
     * @return A modalidade de cliente.
     */
    public ModalidadeCliente getModalidade() {
        return modalidade;
    }

    /**
     * Define a modalidade de cliente.
     *
     * @param modalidade A nova modalidade de cliente.
     */
    public void setModalidade(ModalidadeCliente modalidade) {
        this.modalidade = modalidade;
    }

    /**
     * Obtém o turno escolhido pelo cliente.
     *
     * @return O turno escolhido pelo cliente.
     */
    public Turno getTurnoEscolhido() {
        return turnoEscolhido;
    }

    /**
     * Define o turno escolhido pelo cliente.
     *
     * @param turnoEscolhido O novo turno escolhido pelo cliente.
     */
    public void setTurnoEscolhido(Turno turnoEscolhido) {
        this.turnoEscolhido = turnoEscolhido;
    }

    /**
     * Define a lista de veículos associados ao cliente.
     *
     * @param veiculos A nova lista de veículos do cliente.
     */
    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
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
     * Obtém a lista de veículos do cliente.
     *
     * @return A lista de veículos do cliente.
     */
    public List<Veiculo> getVeiculos() {
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
        if (veiculos.size() < 10) {
            veiculos.add(veiculo);
        } else {
            System.out.println("Limite de veículos atingido para este cliente."); // EXCECAO
        }
    }

    private boolean validarMes(int mes) {
        if (mes >= 1 && mes <= 12) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se o cliente possui um veículo com a placa especificada.
     *
     * @param placa A placa do veículo a ser verificada.
     * @return O veículo com a placa especificada, ou null se o cliente não o possuir.
     */
    public Veiculo possuiVeiculo(String placa) {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getPlaca().equals(placa))
                .findFirst()
                .orElse(null);
    }

    /**
     * Obtém o total de usos de vaga realizados pelo cliente.
     *
     * @return O total de usos de vaga do cliente.
     */
    public int totalDeUsos() {
        return veiculos.stream()
                .mapToInt(Veiculo::totalDeUsos)
                .sum();
    }

    /**
     * Obtém o valor total arrecadado pelo cliente por um veículo com a placa especificada.
     *
     * @param placa A placa do veículo.
     * @return O valor total arrecadado pelo veículo com a placa especificada.
     */
    public double arrecadadoPorVeiculo(String placa) {
        Veiculo veiculo = possuiVeiculo(placa);
        return (veiculo != null) ? veiculo.totalArrecadado() : 0.0;
    }

    /**
     * Obtém o valor total arrecadado pelo cliente por todos os veículos.
     *
     * @return O valor total arrecadado pelo cliente.
     */
    public double arrecadadoTotal() {
        double totalArrecadado = veiculos.stream()
                .mapToDouble(Veiculo::totalArrecadado)
                .sum();

        switch (modalidade) {
            case DE_TURNO:
                totalArrecadado += 200.0;
                break;
            case MENSALISTA:
                totalArrecadado += 500.0;
                break;
            default:
                break;
        }

        return totalArrecadado;
    }

    /**
     * Obtém o valor arrecadado pelo cliente no mês especificado, considerando a modalidade.
     *
     * @param mes O mês para o qual se deseja calcular a arrecadação.
     * @return O valor arrecadado pelo cliente no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {

        if (validarMes(mes)) {
            double arrecadadoMes = veiculos.stream()
            .mapToDouble(veiculo -> veiculo.arrecadadoNoMes(mes))
            .sum();

        switch (modalidade) {
            case HORISTA:
                return arrecadadoMes;
            case DE_TURNO:
                return arrecadadoMes + 200.0;
            case MENSALISTA:
                arrecadadoMes = 500.0;
                break;
        }

        return arrecadadoMes;   
        }   else {
            throw new IllegalArgumentException("O mês informado é inválido: " + mes);
        }
    }
    
    /**
     * Calcula a arrecadação média dos clientes horistas neste mês usando Stream.
     *
     * @param mes O mês para o qual se deseja calcular a arrecadação média.
     * @return A arrecadação média dos clientes horistas neste mês.
     */
    public double calcularArrecadacaoMediaHoristas(int mes) {
        if (validarMes(mes)) {
            return veiculos.stream()
                    .filter(veiculo -> veiculo.getCliente().getModalidade() == ModalidadeCliente.HORISTA)
                    .mapToDouble(veiculo -> veiculo.arrecadadoNoMes(mes))
                    .average()
                    .orElse(0.0);            
        }   else {
            throw new IllegalArgumentException("O mês informado é inválido: " + mes);
        }
    }

    /**
     * Calcula a média de usos mensais para clientes mensalistas.
     *
     * @return A média de usos mensais para clientes mensalistas.
     */
    public double calcularMediaUsosMensalistas() {
        return veiculos.stream()
                .filter(veiculo -> veiculo.getCliente().getModalidade() == ModalidadeCliente.MENSALISTA)
                .mapToInt(Veiculo::totalDeUsos)
                .average()
                .orElse(0.0);
    }

    public double getValor(ModalidadeCliente modalidade) {
        if (modalidade == ModalidadeCliente.DE_TURNO) {
            return 200.0;
        } else if (modalidade == ModalidadeCliente.MENSALISTA) {
            return 500.0;
        } else {
            return 0.0;
        }
        
    }
}
