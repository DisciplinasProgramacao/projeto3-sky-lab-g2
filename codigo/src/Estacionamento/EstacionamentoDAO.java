import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe responsável por operações de leitura e escrita de objetos Estacionamento em um arquivo.
 */
public class EstacionamentoDAO implements DAO<Estacionamento> {

    private String nomeArq;
    private Scanner arqLeitura;
    private FileWriter arqEscrita;

    /**
     * Construtor da classe EstacionamentoDAO.
     *
     * @param nomeArq O nome do arquivo no qual os objetos Estacionamento serão armazenados.
     */
    public EstacionamentoDAO(String nomeArq) {
        this.nomeArq = nomeArq;
        this.arqEscrita = null;
        this.arqLeitura = null;
    }

    /**
     * Abre o arquivo para leitura.
     *
     * @throws IOException Se ocorrer um erro de E/S durante a abertura do arquivo.
     */
    public void abrirLeitura() throws IOException {
        if (arqEscrita != null) {
            arqEscrita.close();
            arqEscrita = null;
        }
        arqLeitura = new Scanner(new File(nomeArq), Charset.forName("UTF-8"));
    }

    /**
     * Abre o arquivo para escrita.
     *
     * @throws IOException Se ocorrer um erro de E/S durante a abertura do arquivo.
     */
    public void abrirEscrita() throws IOException {
        if (arqLeitura != null) {
            arqLeitura.close();
            arqLeitura = null;
        }
        arqEscrita = new FileWriter(nomeArq, Charset.forName("UTF-8"), true);
    }

    /**
     * Fecha o arquivo atualmente aberto, seja para leitura ou escrita.
     *
     * @throws IOException Se ocorrer um erro de E/S durante o fechamento do arquivo.
     */
    public void fechar() throws IOException {
        if (arqEscrita != null) arqEscrita.close();
        if (arqLeitura != null) arqLeitura.close();
        arqEscrita = null;
        arqLeitura = null;
    }

    /**
     * Lê e retorna o próximo objeto Estacionamento do arquivo.
     *
     * @return O próximo objeto Estacionamento lido do arquivo.
     */
    public Estacionamento getNext() {
        String nome = arqLeitura.nextLine();
        int numFileiras = Integer.parseInt(arqLeitura.nextLine());
        int vagasPorFileira = Integer.parseInt(arqLeitura.nextLine());

        return new Estacionamento(nome, numFileiras, vagasPorFileira);
    }

    /**
     * Adiciona um objeto Estacionamento ao arquivo.
     *
     * @param estacionamento O objeto Estacionamento a ser adicionado ao arquivo.
     * @throws IOException Se ocorrer um erro de E/S durante a escrita no arquivo.
     */
    public void add(Estacionamento estacionamento) throws IOException {
        arqEscrita.append(estacionamento.dataToText() + "\n");
    }

    /**
     * Lê todos os objetos Estacionamento do arquivo e os retorna em um array.
     *
     * @return Um array de objetos Estacionamento lidos do arquivo.
     */
    public Estacionamento[] getAll() {
        int TAM_MAX = 10000;
        int cont = 0;
        Estacionamento[] dados = new Estacionamento[TAM_MAX];
        try {
            fechar();
            abrirLeitura();
            while (arqLeitura.hasNext()) {
                dados[cont] = this.getNext();
                cont++;
            }
        } catch (IOException exception) {
            arqEscrita = null;
            arqLeitura = null;
            dados = null;
        }
        dados = Arrays.copyOf(dados, cont);
        return dados;
    }

    /**
     * Adiciona um array de objetos Estacionamento ao arquivo.
     *
     * @param dados O array de objetos Estacionamento a ser adicionado ao arquivo.
     */
    public void addAll(Estacionamento[] dados) {
        try {
            fechar();
            abrirEscrita();
            for (Estacionamento estacionamento : dados) {
                if (estacionamento != null)
                    add(estacionamento);
            }
        } catch (IOException e) {
            arqEscrita = null;
            arqLeitura = null;
        }
    }
}
