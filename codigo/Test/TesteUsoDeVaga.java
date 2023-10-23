import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.Duration;
import java.time.LocalDateTime;

public class UsoDeVagaTest {

    private Vaga vaga;
    private UsoDeVaga usoDeVaga;

    @Before
    public void setUp() {
        // Configuração inicial antes de cada teste
        vaga = new Vaga("Vaga de Teste");
        usoDeVaga = new UsoDeVaga(vaga);
    }

    @Test
    public void testUsarVaga() {
        // Verifica se a vaga é marcada como ocupada após usarVaga()
        usoDeVaga.usarVaga();
        assertTrue(vaga.isOcupada());
    }

    @Test
    public void testSair() {
        // Simula um uso da vaga e verifica se o valorPago é correto
        usoDeVaga.usarVaga();
        LocalDateTime entrada = usoDeVaga.getEntrada();
        
        // Simulando um período de 30 minutos (meia hora)
        LocalDateTime saida = entrada.plusMinutes(30);
        usoDeVaga.setSaida(saida);
        usoDeVaga.sair();
        
        assertEquals(VALOR_FRACAO, usoDeVaga.valorPago(), 0.01); // Esperado: VALOR_FRACAO (4.0)
        assertFalse(vaga.isOcupada()); // A vaga deve ser desocupada após sair()
    }

    @Test
    public void testSairSemUsar() {
        // Verifica se sair() retorna o valorPago como 0.0 se a vaga não foi usada
        usoDeVaga.sair();
        assertEquals(0.0, usoDeVaga.valorPago(), 0.01);
    }
}
