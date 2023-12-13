import static org.junit.Assert.*;
import org.junit.Test;
import java.time.LocalDateTime;

public class CalculadorCustoHoristaTest {

    @Test
    public void testCustoInferiorQuinzeMinutos() {
        CalculadorCustoHorista calculador = new CalculadorCustoHorista();
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = entrada.plusMinutes(10);
        assertEquals(4.0, calculador.calcularCusto(entrada, saida), 0.01);
    }

    @Test
    public void testCustoUmaHora() {
        CalculadorCustoHorista calculador = new CalculadorCustoHorista();
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = entrada.plusHours(1); //valor de uma hora é a taxa mínima, 4.0
        assertEquals(4.0, calculador.calcularCusto(entrada, saida), 0.01);
    }

    @Test
    public void testCustoSuperiorUmaHora() {
        CalculadorCustoHorista calculador = new CalculadorCustoHorista();
        LocalDateTime entrada = LocalDateTime.now();
        LocalDateTime saida = entrada.plusHours(10);
        assertTrue(calculador.calcularCusto(entrada, saida) <= 50.0); //sendo 50 o valor máximo
    }
}

