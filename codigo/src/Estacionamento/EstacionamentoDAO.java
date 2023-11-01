import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class EstacionamentoDAO implements DAO<Estacionamento> {

    private final String nomeArq;
    private Scanner arqLeitura;
    private FileWriter arqEscrita;

    private static final int TAM_MAX = 10000;

    public EstacionamentoDAO(String nomeArq) {
        this.nomeArq = nomeArq;
        this.arqEscrita = null;
        this.arqLeitura = null;
    }

    public void abrirLeitura() throws IOException {
        fecharEscrita();
        arqLeitura = new Scanner(new File(nomeArq), Charset.forName("UTF-8"));
    }

    public void abrirEscrita() throws IOException {
        fecharLeitura();
        arqEscrita = new FileWriter(nomeArq, Charset.forName("UTF-8"), true);
    }

    public void fechar() {
        fecharLeitura();
        fecharEscrita();
    }

    private void fecharLeitura() {
        if (arqLeitura != null) {
            arqLeitura.close();
            arqLeitura = null;
        }
    }

    private void fecharEscrita() {
        if (arqEscrita != null) {
            try {
                arqEscrita.close();
            } catch (IOException e) {
                // Lidar com a exceção de fechamento do arquivo, se necessário.
            }
            arqEscrita = null;
        }
    }

    public Estacionamento getNext() {
        if (arqLeitura != null && arqLeitura.hasNext()) {
            String nome = arqLeitura.nextLine();
            int numFileiras = Integer.parseInt(arqLeitura.nextLine());
            int vagasPorFileira = Integer.parseInt(arqLeitura.nextLine());
            return new Estacionamento(nome, numFileiras, vagasPorFileira);
        }
        return null;
    }

    public void add(Estacionamento estacionamento) throws IOException {
        if (arqEscrita != null) {
            arqEscrita.append(estacionamento.dataToText() + "\n");
        }
    }

    public Estacionamento[] getAll() {
        int cont = 0;
        Estacionamento[] dados = new Estacionamento[TAM_MAX];
        try {
            abrirLeitura();
            while (arqLeitura.hasNext() && cont < TAM_MAX) {
                dados[cont] = this.getNext();
                cont++;
            }
        } catch (IOException exception) {
            // Lidar com exceções de E/S, se necessário.
        } finally {
            fecharLeitura();
        }
        dados = Arrays.copyOf(dados, cont);
        return dados;
    }

    public void addAll(Estacionamento[] dados) {
        try {
            abrirEscrita();
            for (Estacionamento estacionamento : dados) {
                if (estacionamento != null)
                    add(estacionamento);
            }
        } catch (IOException e) {
            // Lidar com exceções de E/S, se necessário.
        } finally {
            fecharEscrita();
        }
    }
}
