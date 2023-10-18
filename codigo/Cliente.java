public class Cliente {

    private String nome;
    private String id;
    private Veiculo[] veiculos;
    private int numVeiculos;
    private static Cliente[] clientesCadastrados = new Cliente[100]; 
    private static int numClientes = 0;
    
    public Cliente(String nome, String id) throws Exception { //alterando o construtor para que respeite o requisito de exceção
        for (int i = 0; i < numClientes; i++) {
            if (clientesCadastrados[i].getId().equals(id)) {
                throws new Exception("Cliente com o ID " + id + " já cadastrado.");
            }
        }

        this.nome = nome;
        this.id = id;
        clientesCadastrados[numClientes] = this;
        numClientes++;
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
