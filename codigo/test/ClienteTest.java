import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClienteTest {
    
    private Cliente cliente;
    private Veiculo veiculo1;
    private Veiculo veiculo2;

    @Before
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
        assertNull(cliente.possuiVeiculo("123XYZ"));
    }

    @Test
    public void testTotalDeUsos() {
        veiculo1.estacionar(new Vaga("A01"), LocalDateTime.now());
        veiculo2.estacionar(new Vaga("B02"), LocalDateTime.now());
        veiculo1.sair(null,LocalDateTime.now().plusHours(1));
        veiculo2.sair(null, LocalDateTime.now().plusHours(2));
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(2, cliente.totalDeUsos());
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        veiculo1.estacionar(new Vaga("A01"), LocalDateTime.now());
        veiculo1.sair(null,LocalDateTime.now().plusHours(1));
        cliente.addVeiculo(veiculo1);
        assertEquals(4.0, cliente.arrecadadoPorVeiculo("ABC321"), 0.001);
        assertEquals(0.0, cliente.arrecadadoPorVeiculo("XYZ789"), 0.001);
    }

    @Test
    public void testArrecadadoTotal() {
        veiculo1.estacionar(new Vaga("A01"), LocalDateTime.now());
        veiculo2.estacionar(new Vaga("B02"), LocalDateTime.now());
        veiculo1.sair(null,LocalDateTime.now().plusHours(1));
        veiculo2.sair(null,LocalDateTime.now().plusHours(2));
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(8.0, cliente.arrecadadoTotal(), 0.001);
    }

    @Test
    public void testArrecadadoNoMes() {
        veiculo1.estacionar(new Vaga("A01"), LocalDateTime.now());
        veiculo2.estacionar(new Vaga("B02"), LocalDateTime.now());
        veiculo1.sair(null,LocalDateTime.now().plusHours(1));
        veiculo2.sair(null,LocalDateTime.now().plusHours(2));
        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);
        assertEquals(8.0, cliente.arrecadadoNoMes(10), 0.001);
        assertEquals(0.0, cliente.arrecadadoNoMes(11), 0.001);
    }
}
