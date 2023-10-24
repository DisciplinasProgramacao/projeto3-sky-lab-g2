import java.time.LocalDateTime;

public class Veiculo {

    private String placa;
    private UsoDeVaga[] usos;
    private int numUsos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new UsoDeVaga[100]; // Usando um array de tamanho fixo
        this.numUsos = 0;
    }

    public String getPlaca() {
        return placa;
    }

    public String vaga(Vaga vaga) {
        return vaga.getId(); 
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void estacionar(Vaga vaga, LocalDateTime entrada) {
        if (numUsos < usos.length) {
            UsoDeVaga uso = new UsoDeVaga(vaga);
            uso.usarVaga(vaga, entrada);
            usos[numUsos] = uso;
            numUsos++;
        } else {
            
        }
    }

    public double sair(Vaga vaga, LocalDateTime saida) {

        for (int i = 0; i < numUsos; i++) {
            if (usos[i] != null && usos[i].getVaga().equals(vaga)) {
                UsoDeVaga uso = usos[i];
                uso.sair(saida);
                return uso.valorPago();
            }
        }

        return 0.0;
    }

    public double totalArrecadado() {
        double totalArrecadado = 0.0;

        for (int i = 0; i < numUsos; i++) {
            if (usos[i] != null) {
                totalArrecadado += usos[i].valorPago();
            }
        }

        return totalArrecadado;
    }

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

    public int totalDeUsos() {
        return numUsos;
    }
}
