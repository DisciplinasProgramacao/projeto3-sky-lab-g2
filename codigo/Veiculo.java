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

    }

    public double totalArrecadado() {
        
    }

    public double arrecadadoNoMes(int mes) {
        
    }

    public int totalDeUsos() {
        return numUsos;
    }
}
