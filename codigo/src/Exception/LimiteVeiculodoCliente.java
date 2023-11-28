public class LimiteVeiculodoCliente extends Exception {
    //adicionado ao classpath;
    public LimiteVeiculodoCliente() {
        super("Limite de veículos para este cliente foi atingido.");
    }
}
