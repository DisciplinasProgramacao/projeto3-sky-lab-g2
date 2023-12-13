import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class UsoDeVagaTest {

    private UsoDeVaga usoDeVaga;
    private Vaga vaga;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime entrada;
    private LocalDateTime saida;

    @Before
    public void setUp() {
        vaga = new Vaga("V1");
        usoDeVaga = new UsoDeVaga(vaga);
        cliente = new Cliente("1", "Cliente Teste");
        cliente.setModalidade(Cliente.ModalidadeCliente.HORISTA);
        veiculo = new Veiculo("ABC-1234");
        entrada = LocalDateTime.now().minusHours(1);
        saida = LocalDateTime.now();
    }

    @Test
    public void testUsarVaga() {
        assertNull(usoDeVaga.getEntrada());
        assertNull(usoDeVaga.getSaida());

        usoDeVaga.usarVaga(veiculo, entrada);

        assertEquals(entrada, usoDeVaga.getEntrada());
        assertNull(usoDeVaga.getSaida());
    }

    @Test
    public void testSair() {
        usoDeVaga.usarVaga(veiculo, entrada);
        usoDeVaga.setCliente(cliente);

        double valorPago = usoDeVaga.sair(saida);

        assertEquals(entrada, usoDeVaga.getEntrada());
        assertEquals(saida, usoDeVaga.getSaida());
        assertTrue(valorPago > 0.0);
    }

    @Test
    public void testCalcularCustoHorista() {
        cliente.setModalidade(Cliente.ModalidadeCliente.HORISTA);
        usoDeVaga.setCliente(cliente);

        usoDeVaga.usarVaga(veiculo, entrada);
        double valorPago = usoDeVaga.sair(saida);

        assertTrue(valorPago > 0.0);
    }

    @Test
    public void testCalcularCustoTurnista() {
        cliente.setModalidade(Cliente.ModalidadeCliente.DE_TURNO);
        usoDeVaga.setCliente(cliente);

        usoDeVaga.usarVaga(veiculo, entrada);
        double valorPago = usoDeVaga.sair(saida);

        assertTrue(valorPago > 0.0);
    }

    @Test
    public void testCalcularCustoMensalista() {
        cliente.setModalidade(Cliente.ModalidadeCliente.MENSALISTA);
        usoDeVaga.setCliente(cliente);

        usoDeVaga.usarVaga(veiculo, entrada);
        double valorPago = usoDeVaga.sair(saida);

        assertTrue(valorPago >= 0.0); //o valor será 0.0 porque mensalista paga apenas o valor mensal
    }
}
