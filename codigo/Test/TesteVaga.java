public class VagaTest {

    @Test
    public void testVagaInicialmenteDisponivel() {
        Vaga vaga = new Vaga("A1");
        assertTrue(vaga.isDisponivel());
    }

    @Test
    public void testEstacionarVagaDisponivel() {
        Vaga vaga = new Vaga("A2");
        assertTrue(vaga.estacionar());
        assertFalse(vaga.isDisponivel());
    }

    @Test
    public void testEstacionarVagaOcupada() {
        Vaga vaga = new Vaga("A3");
        vaga.estacionar(); // Ocupar a vaga
        assertFalse(vaga.estacionar());
        assertFalse(vaga.isDisponivel());
    }

    @Test
    public void testSairVagaOcupada() {
        Vaga vaga = new Vaga("A4");
        vaga.estacionar(); // Ocupar a vaga
        assertTrue(vaga.sair());
        assertTrue(vaga.isDisponivel());
    }

    @Test
    public void testSairVagaDisponivel() {
        Vaga vaga = new Vaga("A5");
        assertTrue(vaga.sair());
        assertTrue(vaga.isDisponivel());
    }
}
