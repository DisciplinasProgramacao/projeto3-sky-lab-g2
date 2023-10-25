import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class EstacionamentoTest {

    private Estacionamento estacionamento;
    private Cliente cliente;
    private Veiculo veiculo;

    @Before
    public void setUp() {
        estacionamento = new Estacionamento("Estacionamento Teste", 2, 10);
        cliente = new Cliente("Cliente Teste", "ID-123");
        veiculo = new Veiculo("ABC-1234");
    }

    @Test
    public void testAdicionarCliente() {
        try {
            estacionamento.addCliente(cliente);
        } catch (ClienteJaExistenteException e) {
            e.printStackTrace();
        }
        assertTrue(estacionamento.getClientes().contains(cliente));
    }

    @Test
    public void testAdicionarVeiculo() {
        try {
            estacionamento.addCliente(cliente);
        } catch (ClienteJaExistenteException e) {
            e.printStackTrace();
        }
        try {
            estacionamento.addVeiculo(veiculo, "ID-123");
        } catch (VeiculoJaExistenteException e) {
            e.printStackTrace();
        }
        assertTrue(cliente.possuiVeiculo("ABC-1234") != null);
    }

    @Test
    public void testEstacionarESair() {
        try {
            estacionamento.addCliente(cliente);
        } catch (ClienteJaExistenteException e) {
            e.printStackTrace();
        }
        try {
            estacionamento.addVeiculo(veiculo, "ID-123");
        } catch (VeiculoJaExistenteException e) {
            e.printStackTrace();
        }

        LocalDateTime entrada = LocalDateTime.now();
        try {
            estacionamento.estacionar("ABC-1234", entrada);
        } catch (VagaOcupadaException e) {
            e.printStackTrace();
        }
        LocalDateTime saida = entrada.plusHours(2);
        try {
            assertEquals(4.0, estacionamento.sair("ABC-1234", saida), 0.01);
        } catch (UsoDeVagaFinalizadoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testContratarServico() {
        try {
            estacionamento.addCliente(cliente);
        } catch (ClienteJaExistenteException e) {
            e.printStackTrace();
        }
        try {
            estacionamento.addVeiculo(veiculo, "ID-123");
        } catch (VeiculoJaExistenteException e) {
            e.printStackTrace();
        }
        try {
            estacionamento.estacionar("ABC-1234", LocalDateTime.now());
        } catch (VagaOcupadaException e) {
            e.printStackTrace();
        }
        assertTrue(estacionamento.contratarServico("ABC-1234", Servico.MANOBRISTA));
    }

    @Test
    public void testTotalArrecadado() {
        try {
            estacionamento.addCliente(cliente);
        } catch (ClienteJaExistenteException e) {
            e.printStackTrace();
        }
        try {
            estacionamento.addVeiculo(veiculo, "ID-123");
        } catch (VeiculoJaExistenteException e) {
            e.printStackTrace();
        }
        LocalDateTime entrada = LocalDateTime.now();
        try {
            estacionamento.estacionar("ABC-1234", entrada);
        } catch (VagaOcupadaException e) {
            e.printStackTrace();
        }
        LocalDateTime saida = entrada.plusHours(2);
        try {
            estacionamento.sair("ABC-1234", saida);
        } catch (UsoDeVagaFinalizadoException e) {
            e.printStackTrace();
        }
        assertEquals(4.0, estacionamento.totalArrecadado(), 0.01);
    }

}
