import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class EstacionamentoDAO implements DAO<Estacionamento> {

    private String nomeArq;
    private Scanner arqLeitura;
    private FileWriter arqEscrita;

    public EstacionamentoDAO(String nomeArq) {
        this.nomeArq = nomeArq;
        this.arqEscrita = null;
        this.arqLeitura = null;
    }

    public void abrirLeitura() throws IOException {
        if (arqEscrita != null) {
            arqEscrita.close();
            arqEscrita = null;
        }
        arqLeitura = new Scanner(new File(nomeArq), Charset.forName("UTF-8"));
    }

    public void abrirEscrita() throws IOException {
        if (arqLeitura != null) {
            arqLeitura.close();
            arqLeitura = null;
        }
        arqEscrita = new FileWriter(nomeArq, Charset.forName("UTF-8"), true);
    }

    public void fechar() throws IOException {
        if (arqEscrita != null) arqEscrita.close();
        if (arqLeitura != null) arqLeitura.close();
        arqEscrita = null;
        arqLeitura = null;
    }

    public Estacionamento getNext() {
        String nome = null;
        int numFileiras = 0;
        int vagasPorFileira = 0;

        while (arqLeitura.hasNextLine()) {
            String linha = arqLeitura.nextLine().trim();
            if (linha.startsWith("Nome do Estacionamento")) {
                nome = linha.substring("Nome do Estacionamento:".length()).trim();
            } else if (linha.startsWith("Quantidade de Fileiras")) {
                numFileiras = Integer.parseInt(linha.substring("Quantidade de Fileiras:".length()).trim());
            } else if (linha.startsWith("Vagas por Fileira")) {
                vagasPorFileira = Integer.parseInt(linha.substring("Vagas por Fileira:".length()).trim());
                break;  // Termina a leitura após encontrar as informações desejadas
            }
        }

        return new Estacionamento(nome, numFileiras, vagasPorFileira);
    }

    public void add(Estacionamento estacionamento) throws IOException {
        arqEscrita.append("Nome do Estacionamento: " + estacionamento.getNome() + "\n");
        arqEscrita.append("Quantidade de Fileiras: " + estacionamento.getNumFileiras() + "\n");
        arqEscrita.append("Vagas por Fileira: " + estacionamento.getVagasPorFileira() + "\n");
    }

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
