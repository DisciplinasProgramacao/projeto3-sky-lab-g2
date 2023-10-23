import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

    private Cliente cliente;
    private Veiculo veiculo1;
    private Veiculo veiculo2;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("John Doe", "12345");
        veiculo1 = new Veiculo("ABC123");
        veiculo2 = new Veiculo("XYZ789");
    }

    @Test
    public void testAddVeiculo() {
        cliente.addVeiculo(veiculo1);
        assertEquals(veiculo1, cliente.possuiVeiculo("ABC123"));
    }

    @Test
    public void testPossuiVeiculo() {
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(veiculo1, cliente.possuiVeiculo("ABC123"));
        assertEquals(veiculo2, cliente.possuiVeiculo("XYZ789"));
        assertNull(cliente.possuiVeiculo("123XYZ")); // Non-existent plate
    }

    @Test
    public void testTotalDeUsos() {
        veiculo1.estacionar("A01");
        veiculo2.estacionar("B02");
        veiculo1.sair();
        veiculo2.sair();
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(2, cliente.totalDeUsos());
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        veiculo1.estacionar("A01");
        veiculo1.sair();
        cliente.addVeiculo(veiculo1);
        assertEquals(4.0, cliente.arrecadadoPorVeiculo("ABC123"));
        assertEquals(0.0, cliente.arrecadadoPorVeiculo("XYZ789")); // Non-existent plate
    }

    @Test
    public void testArrecadadoTotal() {
        veiculo1.estacionar("A01");
        veiculo2.estacionar("B02");
        veiculo1.sair();
        veiculo2.sair();
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(8.0, cliente.arrecadadoTotal());
    }

    @Test
    public void testArrecadadoNoMes() {
        veiculo1.estacionar("A01");
        veiculo2.estacionar("B02");
        veiculo1.sair();
        veiculo2.sair();
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(4.0, cliente.arrecadadoNoMes(10));
        assertEquals(4.0, cliente.arrecadadoNoMes(11));
    }
}
