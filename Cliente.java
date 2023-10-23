public class Cliente {

    private String nome;
    private String id;
    private Veiculo[] veiculos;
    private int numVeiculos; //add opção anonimo

    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new Veiculo[10];
        this.numVeiculos = 0;
    }
 
    public String getNome() {
        return nome;
    }

    public Veiculo[] getVeiculos() {
        return veiculos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addVeiculo(Veiculo veiculo) {
        if (numVeiculos < veiculos.length) {
            veiculos[numVeiculos] = veiculo;
            numVeiculos++;
        } else {
            System.out.println("Limite de veículos atingido para este cliente.");
        }
    }

    public Veiculo possuiVeiculo(String placa) {
        for (int i = 0; i < numVeiculos; i++) {
            if (veiculos[i].getPlaca().equals(placa)) {
                return veiculos[i];
            }
        }
        return null;
    }

    public int totalDeUsos() {
        int totalUsos = 0;
        for (int i = 0; i < numVeiculos; i++) {
            totalUsos += veiculos[i].totalDeUsos();
        }
        return totalUsos;
    }

    public double arrecadadoPorVeiculo(String placa) {
        Veiculo veiculo = possuiVeiculo(placa);
        if (veiculo != null) {
            
            return veiculo.totalArrecadado();
        } else {
            return 0.0;  
        }
    }

    public double arrecadadoTotal() {
        double totalArrecadado = 0.0;
        for (int i = 0; i < numVeiculos; i++) {
            totalArrecadado += veiculos[i].totalArrecadado();
        }
        return totalArrecadado;
    }

    public double arrecadadoNoMes(int mes) {
        double arrecadadoMes = 0.0;
        for (int i = 0; i < numVeiculos; i++) {
            arrecadadoMes += veiculos[i].arrecadadoNoMes(mes);
        }
        return arrecadadoMes;
    }
}
