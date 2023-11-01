import java.io.IOException;

public interface DAO<T extends IDataToText> {
    /**
     * Obtém o próximo elemento de dados.
     *
     * @return O próximo elemento de dados.
     */
    public T getNext();

    /**
     * Obtém todos os elementos de dados.
     *
     * @return Um array de elementos de dados.
     * @throws IOException se ocorrer um erro de E/S.
     */
    public T[] getAll() throws IOException;

    /**
     * Adiciona um elemento de dados.
     *
     * @param dado O elemento de dados a ser adicionado.
     * @throws IOException se ocorrer um erro de E/S.
     */
    public void add(T dado) throws IOException;

    /**
     * Adiciona vários elementos de dados de uma vez.
     *
     * @param dado Um array de elementos de dados a serem adicionados.
     */
    public void addAll(T[] dado);
}

