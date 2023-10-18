public class Veiculo {

    private String placa;
    private UsoDeVaga[] usos;
    private int numUsos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new UsoDeVaga[100];
        this.numUsos = 0;
    }

    public void estacionar(Vaga vaga) {
        if (numUsos < usos.length) {
            usos[numUsos] = new UsoDeVaga(vaga.getId());
            vaga.estacionar();
            numUsos++;
        } else {
            System.out.println("Limite de usos excedido para este veículo.");
        }
    }

    public double sair() {
        double tarifaPorHora = 2.0;
        double valorASerPago = 0.0;

        for (int i = 0; i < numUsos; i++) {
            if (usos[i] != null) {
                valorASerPago += usos[i].calcularValorASerPago(tarifaPorHora);
                usos[i] = null; 
            }
        }

        return valorASerPago;
    }

    public double totalArrecadado() {
        double totalArrecadado = 0.0;

        for (int i = 0; i < numUsos; i++) {
            if (usos[i] != null) {
                totalArrecadado += usos[i].getValorPago();
            }
        }

        return totalArrecadado;
    }

    public double arrecadadoNoMes(int mes) {
        double arrecadadoNoMes = 0.0;

        for (int i = 0; i < numUsos; i++) {
            if (usos[i] != null) {
                LocalDate dataUso = usos[i].getDataEntrada();
                if (dataUso.getMonthValue() == mes) {
                    arrecadadoNoMes += usos[i].getValorPago();
                }
            }
        }

        return arrecadadoNoMes;
    }

    public int totalDeUsos() {
        return numUsos;
    }
}
