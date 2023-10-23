import java.time.LocalDateTime;

public class Veiculo {

    private String placa;
    private UsoDeVaga[] usos;
    private int numUsos;

    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new UsoDeVaga[100];
        this.numUsos = 0;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void estacionar(Vaga vaga) {
        if (numUsos < usos.length) {
            UsoDeVaga uso = new UsoDeVaga(vaga);
            uso.usarVaga(vaga);
            usos[numUsos] = uso;
            numUsos++;
        } else {
            System.out.println("Limite de usos excedido para este veículo.");
        }
    }

    public double sair(String placa) {
        UsoDeVaga uso = null;
        Vaga vaga = new Vaga(placa);

        for (int i = 0; i < numUsos; i++) {
            if (usos[i] != null && usos[i].getVaga() == vaga) {
                uso = usos[i];
                break;
            }
        }

        if (uso != null) {
            uso.sair();
            return uso.valorPago();
        } else {
            System.out.println("Você não usou a vaga.");
            return 0.0;
        }
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
