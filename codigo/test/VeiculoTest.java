import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class VeiculoTest {

    private Veiculo veiculo;
    private Vaga vaga;

    @Before
    public void setUp() {
        veiculo = new Veiculo("ABC123");
        vaga = new Vaga("V1");
    }

    @Test
    public void testEstacionar() {
        LocalDateTime entrada = LocalDateTime.now();
        veiculo.estacionar(vaga, entrada);
        assertEquals(1, veiculo.totalDeUsos());
    }

    @Test
    public void testSair() {
        LocalDateTime entrada = LocalDateTime.now();
        veiculo.estacionar(vaga, entrada);
        LocalDateTime saida = entrada.plusHours(1);
        double valorPago = veiculo.sair(vaga, saida);
        assertEquals(4.0, valorPago, 0.01);
    }

    @Test
    public void testTotalArrecadado() {
        LocalDateTime entrada1 = LocalDateTime.now();
        veiculo.estacionar(vaga, entrada1);
        LocalDateTime saida1 = entrada1.plusHours(1);
        veiculo.sair(vaga, saida1);

        LocalDateTime entrada2 = saida1.plusMinutes(30);
        veiculo.estacionar(vaga, entrada2);
        LocalDateTime saida2 = entrada2.plusHours(2);
        veiculo.sair(vaga, saida2);

        assertEquals(14.0, veiculo.totalArrecadado(), 0.01);
    }

    @Test
    public void testArrecadadoNoMes() {
        LocalDateTime entrada1 = LocalDateTime.of(2023, 10, 15, 12, 0);
        veiculo.estacionar(vaga, entrada1);
        LocalDateTime saida1 = entrada1.plusHours(1);
        veiculo.sair(vaga, saida1);

        LocalDateTime entrada2 = LocalDateTime.of(2023, 11, 5, 15, 30);
        veiculo.estacionar(vaga, entrada2);
        LocalDateTime saida2 = entrada2.plusMinutes(45);
        veiculo.sair(vaga, saida2);

        assertEquals(50.0, veiculo.arrecadadoNoMes(10), 0.01); // Apenas a primeira transação está em outubro.
        assertEquals(0.0, veiculo.arrecadadoNoMes(12), 0.01); // Nenhuma transação em dezembro.
        assertEquals(4.0, veiculo.arrecadadoNoMes(11), 0.01); // Apenas a segunda transação está em novembro.
    }
}