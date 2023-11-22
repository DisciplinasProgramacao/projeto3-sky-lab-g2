import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class VeiculoTest {
    
}
public class VeiculoTest {
    @Test
    public void testEstacionar() {
        Veiculo veiculo = new Veiculo("ABC-1234");
        Vaga vaga = new Vaga(1);
        LocalDateTime entrada = LocalDateTime.now();
        veiculo.estacionar(vaga, entrada);
        assertEquals(vaga, veiculo.getVaga());
    }
    
    @Test
    public void testEstacionar() {
        Veiculo veiculo = new Veiculo("ABC-1234");
        Vaga vaga = new Vaga(1);
        LocalDateTime entrada = LocalDateTime.now();
        veiculo.estacionar(vaga, entrada);
        assertEquals(vaga, veiculo.getVaga());
    }

    @Test
    public void testSair() {
        Veiculo veiculo = new Veiculo("ABC-1234");
        Vaga vaga = new Vaga(1);
        LocalDateTime entrada = LocalDateTime.now();
        veiculo.estacionar(vaga, entrada);
        LocalDateTime saida = entrada.plusHours(2);
        double valor = veiculo.sair(saida);
        assertEquals(10.0, valor, 0.01);
    }

    @Test
    public void testTotalArrecadado() {
        Veiculo veiculo = new Veiculo("ABC-1234");
        Vaga vaga = new Vaga(1);
        LocalDateTime entrada = LocalDateTime.now();
        veiculo.estacionar(vaga, entrada);
        LocalDateTime saida = entrada.plusHours(2);
        veiculo.sair(saida);
        double total = veiculo.totalArrecadado();
        assertEquals(10.0, total, 0.01);
    }

    @Test
    public void testArrecadadoNoMes() {
        Veiculo veiculo = new Veiculo("ABC-1234");
        Vaga vaga = new Vaga(1);
        LocalDateTime entrada = LocalDateTime.of(2023, 11, 1, 0, 0);
        veiculo.estacionar(vaga, entrada);
        LocalDateTime saida = entrada.plusHours(2);
        veiculo.sair(saida);
        double total = veiculo.arrecadadoNoMes(11);
        assertEquals(10.0, total, 0.01);
    }

    @Test
    public void testTotalDeUsos() {
        Veiculo veiculo = new Veiculo("ABC-1234");
        Vaga vaga = new Vaga(1);
        LocalDateTime entrada = LocalDateTime.now();
        veiculo.estacionar(vaga, entrada);
        LocalDateTime saida = entrada.plusHours(2);
        veiculo.sair(saida);
        int total = veiculo.totalDeUsos();
        assertEquals(1, total);
    }
}
