import java.time.LocalDateTime;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

// A classe de teste TesteUsoDeVaga testa os métodos da classe UsoDeVaga.
public class TesteUsoDeVaga {

    // Instância de UsoDeVaga a ser testada.
    private UsoDeVaga usoDeVaga;

    // Instância de Vaga associada ao uso.
    private Vaga vaga;

    // Método executado antes de cada teste para configurar o ambiente de teste.
    @Before
    public void setUp() {
        vaga = new Vaga(1, "A");
        usoDeVaga = new UsoDeVaga(vaga);
    }

    // Testa o método usarVaga, verificando se a entrada é registrada corretamente.
    @Test
    public void testUsarVaga() {
        LocalDateTime entrada = LocalDateTime.of(2023, 10, 20, 12, 0);
        usoDeVaga.usarVaga(vaga, entrada);
        assertEquals(entrada, usoDeVaga.getEntrada());
    }

    // Testa o método sair, verificando se a saída é registrada e o valor pago é não nulo.
    @Test
    public void testSair() {
        LocalDateTime entrada = LocalDateTime.of(2023, 10, 20, 12, 0);
        LocalDateTime saida = LocalDateTime.of(2023, 10, 20, 13, 0);
        usoDeVaga.usarVaga(vaga, entrada);
        usoDeVaga.sair(saida);
        assertNotNull(usoDeVaga.getEntrada());
        assertNotNull(usoDeVaga.getVaga());
        assertNotNull(usoDeVaga.valorPago());
    }

    // Testa o método valorPago, verificando se o valor pago é calculado corretamente.
    @Test
    public void testValorPago() {
        LocalDateTime entrada = LocalDateTime.of(2023, 10, 20, 12, 0);
        LocalDateTime saida = LocalDateTime.of(2023, 10, 20, 13, 0);
        usoDeVaga.usarVaga(vaga, entrada);
        usoDeVaga.sair(saida);
        assertEquals(4.0, usoDeVaga.valorPago(), 0.01);
    }
}
