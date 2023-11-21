import java.time.Duration;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa o uso de uma vaga de estacionamento por um cliente.
 */
public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;

    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private EnumSet<Servico> servicosContratados;
    private Cliente cliente;

    private static final Map<Veiculo, Vaga> veiculoVagaMap = new HashMap<>();

    /**
     * Construtor que cria uma instância de UsoDeVaga associada a uma vaga.
     *
     * @param vaga A vaga utilizada no uso.
     */
    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.valorPago = 0.0;
        this.servicosContratados = EnumSet.noneOf(Servico.class);
    }

    /**
     * Obtém a data e hora de entrada na vaga.
     *
     * @return A data e hora de entrada.
     */
    public LocalDateTime getEntrada() {
        return entrada;
    }

    /**
     * Obtém a data e hora de entrada na vaga.
     *
     * @return A data e hora de entrada.
     */
    public LocalDateTime getSaida() {
        return saida;
    }

    /**
     * Obtém a vaga associada ao uso.
     *
     * @return A vaga associada.
     */
    public Vaga getVaga() {
        return this.vaga;
    }

    /**
     * Obtém a vaga associada ao uso.
     *
     * @return A vaga associada.
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Registra o uso da vaga com a data e hora de entrada especificadas.
     *
     * @param veiculo O veículo utilizando a vaga.
     * @param entrada A data e hora de entrada na vaga.
     */
    public void usarVaga(Veiculo veiculo, LocalDateTime entrada) {
        if (this.entrada == null) {
            this.entrada = entrada;
            veiculoVagaMap.put(veiculo, vaga);
        }
    }

    /**
     * Registra a saída do veículo da vaga com a data e hora de saída especificadas.
     *
     * @param saida A data e hora de saída da vaga.
     * @return O valor a ser pago pelo uso da vaga.
     */
    public double sair(LocalDateTime saida) {
        this.saida = saida;

        Vaga vagaDoUso = this.vaga;

        if (vagaDoUso != null) {
            // Verifica se a vaga ainda está mapeada no veiculoVagaMap
            Veiculo veiculoDoUso = veiculoVagaMap.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue().equals(vagaDoUso))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

            if (veiculoDoUso != null) {

                veiculoVagaMap.remove(veiculoDoUso);
                vagaDoUso.disponivel();
            }
            double valorPago = calcularCusto(veiculoDoUso, this.entrada, this.saida);
            return valorPago + calcularCustoServicos();
        }

        return valorPago;
    }



    /**
     * Contrata um serviço adicional para o uso da vaga.
     *
     * @param servico O serviço a ser contratado.
     */
    public void contratarServico(Servico servico) {
        servicosContratados.add(servico);
    }

    public double calcularCusto(Veiculo veiculo, LocalDateTime entrada, LocalDateTime saida) {
        Cliente cliente = veiculo.getCliente();
    
        switch (cliente.getModalidade()) {
            case HORISTA:
                return calcularCustoHorista(entrada, saida);
            case DE_TURNO:
                return calcularCustoDeTurno(entrada, saida, cliente);
            case MENSALISTA:
                return calcularCustoServicos();
        }
    
        return VALOR_FRACAO;
    }
    
    private double calcularCustoServicos() {
        double custoServicos = 0.0;
    
        for (Servico servico : servicosContratados) {
            custoServicos += servico.getValor();
        }
    
        return custoServicos;
    }    

    private double calcularCustoHorista(LocalDateTime entrada, LocalDateTime saida) {
        if (entrada == null || saida == null) {
            return VALOR_FRACAO;
        }

        Duration duracao = Duration.between(entrada, saida);

        // Adiciona fração mínima de 15 minutos
        duracao = duracao.plusMinutes(15 - (duracao.toMinutes() % 15));

        long minutosEstacionados = duracao.toMinutes();

        if (minutosEstacionados <= 60) {
            return Math.min(VALOR_FRACAO, VALOR_MAXIMO);
        } else {
            double valorExcedente = Math.ceil((minutosEstacionados - 60) / 15.0) * FRACAO_USO * VALOR_FRACAO;
            return Math.min(valorExcedente, VALOR_MAXIMO);
        }
    }

    private double calcularCustoDeTurno(LocalDateTime entrada, LocalDateTime saida, Cliente cliente) {
        
        if (estaDentroDoTurno(entrada, cliente)) {
            double turno = 0.0;
            return turno; 
        } else {
            return calcularCustoHorista(entrada, saida);
        }
    }

    private boolean estaDentroDoTurno(LocalDateTime horario, Cliente cliente) {
        int hora = horario.getHour();

        switch (cliente.getModalidade()) {
            case DE_TURNO:
                
                switch (cliente.getTurnoEscolhido()) {
                    case MANHA:
                        return hora >= 8 && hora <= 12;
                    case TARDE:
                        return hora > 12 && hora <= 18;
                    case NOITE:
                        return hora > 18 && hora <= 23;
                }
                break;
            default:
                break;
        }

        return false;
    }

    public Cliente getCliente() {
        return this.cliente;
    }
}
