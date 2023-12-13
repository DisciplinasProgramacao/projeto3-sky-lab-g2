import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EstacionamentoTest {

    private Estacionamento estacionamento;
    private Cliente cliente;
    private Veiculo veiculo;

    @Before
    public void setUp() {
        estacionamento = new Estacionamento("Estacionamento Teste", 3, 5);
        cliente = new Cliente("1", "Cliente Teste");
        veiculo = new Veiculo("ABC123");
    }

    @Test
    public void testAdicionarCliente() throws ClienteJaExistenteException {
        estacionamento.addCliente(cliente);
        assertTrue(estacionamento.getClientes().contains(cliente));
    }

    @Test(expected = ClienteJaExistenteException.class)
    public void testAdicionarClienteExistente() throws ClienteJaExistenteException {
        estacionamento.addCliente(cliente);
        estacionamento.addCliente(cliente); // Deve lançar exceção
    }

    @Test
    public void testAdicionarVeiculo() throws VeiculoJaExistenteException, ClienteNaoExisteException, ClienteJaExistenteException {
        estacionamento.addCliente(cliente);
        estacionamento.addVeiculo(veiculo, cliente.getId());
        assertTrue(cliente.possuiVeiculo(veiculo.getPlaca()) != null);
    }

    @Test(expected = ClienteNaoExisteException.class)
    public void testAdicionarVeiculoClienteNaoExistente() throws VeiculoJaExistenteException, ClienteNaoExisteException {
        estacionamento.addVeiculo(veiculo, "ClienteInexistente");
    }


}
