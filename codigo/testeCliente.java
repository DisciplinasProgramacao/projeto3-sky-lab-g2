import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testAdicionarVeiculo() {
        Cliente cliente = new Cliente("João", "12345");
        Veiculo veiculo = new Veiculo("ABC123", "Carro", 2020);

        cliente.addVeiculo(veiculo);
        assertEquals(1, cliente.numVeiculos);
    }

    @Test
    public void testPossuiVeiculo() {
        Cliente cliente = new Cliente("Maria", "54321");
        Veiculo veiculo = new Veiculo("XYZ987", "Moto", 2019);

        cliente.addVeiculo(veiculo);

        assertTrue(cliente.possuiVeiculo("XYZ987") != null);
        assertNull(cliente.possuiVeiculo("PLACA_NAO_EXISTENTE"));
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        Cliente cliente = new Cliente("Pedro", "67890");
        Veiculo veiculo = new Veiculo("DEF456", "Caminhão", 2018);

        cliente.addVeiculo(veiculo);

        veiculo.registrarUso(10);  

        assertEquals(10.0, cliente.arrecadadoPorVeiculo("DEF456"));
        assertEquals(0.0, cliente.arrecadadoPorVeiculo("PLACA_NAO_EXISTENTE"));
    }

    @Test
    public void testArrecadadoTotal() {
        Cliente cliente = new Cliente("Ana", "13579");
        Veiculo veiculo1 = new Veiculo("JKL111", "Carro", 2022);
        Veiculo veiculo2 = new Veiculo("MNO222", "Moto", 2021);

        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);

        veiculo1.registrarUso(15);  
        veiculo2.registrarUso(20);  

        assertEquals(35.0, cliente.arrecadadoTotal());
    }

    @Test
    public void testArrecadadoNoMes() {
        Cliente cliente = new Cliente("Luísa", "24680");
        Veiculo veiculo = new Veiculo("GHI333", "Caminhão", 2020);

        cliente.addVeiculo(veiculo);

        veiculo.registrarUso(10);  

        assertEquals(10.0, cliente.arrecadadoNoMes(1));
        assertEquals(0.0, cliente.arrecadadoNoMes(2));
    }
}
