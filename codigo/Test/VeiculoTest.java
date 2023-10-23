import static org.junit.Assert.*;
import org.junit.Test;

public class VeiculoTest {

    @Test
    public void testEstacionar() {
        
        Vaga vaga = new Vaga(1);  
        Veiculo veiculo = new Veiculo();

        veiculo.estacionar(vaga);

        assertEquals(1, veiculo.getNumUsos());

        assertEquals(1, veiculo.getUsos()[0].getVagaId());
    }
}
