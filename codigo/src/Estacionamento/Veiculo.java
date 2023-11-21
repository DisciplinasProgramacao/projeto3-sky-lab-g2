import java.time.LocalDateTime;

/**
 * Classe que representa um veículo que pode ser estacionado em uma vaga.
 */
public class Veiculo {

    private String placa;
    private UsoDeVaga[] usos;
    private int numUsos;
    private Vaga vaga;
    private Cliente cliente;

    /**
     * Construtor que cria um novo veículo com a placa especificada.
     *
     * @param placa A placa do veículo.
     */
    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new UsoDeVaga[100];
        this.numUsos = 0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Obtém a placa do veículo.
     *
     * @return A placa do veículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a placa do veículo.
     *
     * @param placa A nova placa do veículo.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Estaciona o veículo em uma vaga na data de entrada especificada.
     *
     * @param vaga    A vaga onde o veículo será estacionado.
     * @param entrada A data de entrada do veículo na vaga.
     */
    public void estacionar(Vaga vaga, LocalDateTime entrada) {
        if (numUsos < usos.length) {
            UsoDeVaga uso = new UsoDeVaga(vaga);
            uso.usarVaga(vaga, entrada);
            usos[numUsos] = uso;
            numUsos++;

            this.vaga = vaga;
        }
    }

    public Vaga getVaga() {
        return this.vaga;
    }

    /**
     * Obtém o último uso da vaga feito por este veículo.
     *
     * @return O último uso da vaga.
     */
    public UsoDeVaga getUltimoUso() {
        if (numUsos > 0) {
            return usos[numUsos - 1];
        } else {
            return null;
        }
    }

    /**
     * Registra a saída do veículo de uma vaga na data de saída especificada e calcula o valor a ser pago.
     *
     * @param saida A data de saída do veículo da vaga.
     * @return O valor a ser pago pelo uso da vaga.
     */
    public double sair(LocalDateTime saida) {
        double vPagar = 0.0;
        for (int i = 0; i < numUsos; i++) {
            if (usos[i] != null && usos[i].getVaga().equals(vaga)) {
                UsoDeVaga uso = usos[i];
                uso.sair(saida);
                vPagar = uso.valorPago();
                return vPagar;
            }
        }
        return vPagar;
    }

    /**
     * Calcula o valor total arrecadado com o uso das vagas por este veículo.
     *
     * @return O valor total arrecadado.
     */
    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (int i = 0; i < numUsos; i++) {
            if (usos[i] != null) {
                totalArrecadado += usos[i].valorPago();
            }
        }
        return totalArrecadado;
    }

    /**
     * Calcula o valor arrecadado no mês especificado.
     *
     * @param mes O mês para o qual o valor arrecadado será calculado.
     * @return O valor arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double arrecadadoNoMes = 0.0;
        for (int i = 0; i < numUsos; i++) {
            if (usos[i] != null) {
                LocalDateTime dataUso = usos[i].getEntrada();
                if (dataUso.getMonthValue() == mes) {
                    arrecadadoNoMes += usos[i].valorPago();
                }
            }
        }
        return arrecadadoNoMes;
    }

    /**
     * Obtém o número total de usos da vaga feitos por este veículo.
     *
     * @return O número total de usos da vaga.
     */
    public int totalDeUsos() {
        return numUsos;
    }

}
