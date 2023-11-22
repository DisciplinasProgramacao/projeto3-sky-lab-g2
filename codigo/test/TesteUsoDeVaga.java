import java.time.LocalDateTime;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TesteUsoDeVaga {

    private UsoDeVaga usoDeVaga;
    private Vaga vaga;

    @Before
    public void setUp() {
        vaga = new Vaga(1, "A");
        usoDeVaga = new UsoDeVaga(vaga);
    }

    @Test
    public void testUsarVaga() {
        LocalDateTime entrada = LocalDateTime.of(2023, 10, 20, 12, 0);
        usoDeVaga.usarVaga(vaga, entrada);
        assertEquals(entrada, usoDeVaga.getEntrada());
    }

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

    //fazer teste de serviços contratados

    @Test
    public void testValorPago() {
        LocalDateTime entrada = LocalDateTime.of(2023, 10, 20, 12, 0);
        LocalDateTime saida = LocalDateTime.of(2023, 10, 20, 13, 0);
        usoDeVaga.usarVaga(vaga, entrada);
        usoDeVaga.sair(saida);
        assertEquals(4.0, usoDeVaga.valorPago(), 0.01);
    }
}