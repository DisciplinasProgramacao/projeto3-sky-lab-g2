//adicionado ao classpath;
/**
 * Esta classe representa uma exceção que é lançada quando uma tentativa é feita de adicionar um cliente
 * que já existe no estacionamento.
 */
public class ClienteJaExistenteException extends Exception {
    /**
     * Construtor padrão da classe. Chama o construtor da classe pai Exception e passa a mensagem de erro.
     */
    public ClienteJaExistenteException() {
        super("Cliente já existente no estacionamento.");
    }
}
