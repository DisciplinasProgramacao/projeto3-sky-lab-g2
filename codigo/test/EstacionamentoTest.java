import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class EstacionamentoTest {

    private Estacionamento estacionamento;
    private Veiculo veiculo;
    private Cliente cliente;

    @Before
    public void setUp() {
        estacionamento = new Estacionamento("Estacionamento Teste", 2, 10);
        veiculo = new Veiculo("ABC-1234");
        cliente = new Cliente("Cliente Teste", "ID-123");
    }

    @Test
    public void testContratarServico() {
        estacionamento.addCliente(cliente);
        estacionamento.addVeiculo(veiculo, "ID-123");
        estacionamento.estacionar("ABC-1234", LocalDateTime.now());
        assertTrue(estacionamento.contratarServico("ABC-1234", Servico.MANOBRISTA));
    }

    @Test
    public void testTop5Clientes() {
        Cliente cliente1 = new Cliente("Cliente 1", "ID-1");
        Cliente cliente2 = new Cliente("Cliente 2", "ID-2");
        Cliente cliente3 = new Cliente("Cliente 3", "ID-3");
        Cliente cliente4 = new Cliente("Cliente 4", "ID-4");
        Cliente cliente5 = new Cliente("Cliente 5", "ID-5");
        Cliente cliente6 = new Cliente("Cliente 6", "ID-6");

        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);
        estacionamento.addCliente(cliente3);
        estacionamento.addCliente(cliente4);
        estacionamento.addCliente(cliente5);
        estacionamento.addCliente(cliente6);

        veiculo.setPlaca("AAA-111");
        cliente1.addVeiculo(veiculo);
        veiculo.setPlaca("BBB-222");
        cliente2.addVeiculo(veiculo);
        veiculo.setPlaca("CCC-333");
        cliente3.addVeiculo(veiculo);
        veiculo.setPlaca("DDD-444");
        cliente4.addVeiculo(veiculo);
        veiculo.setPlaca("EEE-555");
        cliente5.addVeiculo(veiculo);
        veiculo.setPlaca("FFF-666");
        cliente6.addVeiculo(veiculo);

        LocalDateTime entrada = LocalDateTime.now().minusDays(10);
        LocalDateTime saida = entrada.plusMinutes(30);

        estacionamento.estacionar("AAA-111", entrada);
        estacionamento.estacionar("BBB-222", entrada);
        estacionamento.estacionar("CCC-333", entrada);
        estacionamento.estacionar("DDD-444", entrada);
        estacionamento.estacionar("EEE-555", entrada);

        estacionamento.sair("AAA-111", saida);
        estacionamento.sair("BBB-222", saida);
        estacionamento.sair("CCC-333", saida);
        estacionamento.sair("DDD-444", saida);
        estacionamento.sair("EEE-555", saida);

        String top5 = estacionamento.top5Clientes(entrada.getMonthValue());

        assertEquals("Top 5 Clientes no mês " + entrada.getMonthValue() + ":\n" +
                "Cliente 1: R$20.0\n" +
                "Cliente 2: R$20.0\n" +
                "Cliente 3: R$20.0\n" +
                "Cliente 4: R$20.0\n" +
                "Cliente 5: R$20.0\n", top5);
    }
}
