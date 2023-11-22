import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// A classe de teste VagaTest testa os métodos da classe Vaga.
public class VagaTest {

    // Instância de Vaga a ser testada.
    private Vaga vaga;

    // Método executado antes de cada teste para configurar o ambiente de teste.
    @Before
    public void setUp() {
        vaga = new Vaga("V1");
    }

    // Testa o método estacionar quando a vaga está disponível, esperando que retorne verdadeiro.
    @Test
    public void testEstacionarVagaDisponivel() {
        assertTrue(vaga.estacionar());
    }

    // Testa o método estacionar quando a vaga já está ocupada, esperando que retorne falso.
    @Test
    public void testEstacionarVagaOcupada() {
        vaga.estacionar(); // Ocupa a vaga.
        assertFalse(vaga.estacionar());
    }

    // Testa o método sair quando a vaga está ocupada, esperando que retorne verdadeiro.
    @Test
    public void testSairVagaOcupada() {
        vaga.estacionar(); // Ocupa a vaga.
        assertTrue(vaga.sair());
    }

    // Testa o método sair quando a vaga está disponível, esperando que retorne falso.
    @Test
    public void testSairVagaDisponivel() {
        assertTrue(vaga.disponivel());
        assertFalse(vaga.sair());
    }
}
