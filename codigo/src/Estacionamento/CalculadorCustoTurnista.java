import java.time.LocalDateTime;

public class CalculadorCustoTurnista implements ICalculadorCusto {

    Cliente cliente;

    /**
     * Calcula o custo para clientes de turno, levando em consideração o turno escolhido.
     *
     * @param entrada A data e hora de entrada na vaga.
     * @param saida A data e hora de saída da vaga.
     * @param cliente O cliente utilizando a vaga.
     * @return O custo para clientes de turno.
     */
    @Override
    public double calcularCusto(LocalDateTime entrada, LocalDateTime saida) {
        if (estaDentroDoTurno(entrada, this.cliente)) {
            return 0.0;
        } else {
            CalculadorCustoHorista custo = new CalculadorCustoHorista();
            return custo.calcularCusto(entrada, saida);
        }
    }

    /**
     * Verifica se o horário está dentro do turno escolhido pelo cliente.
     *
     * @param horario O horário a ser verificado.
     * @param cliente O cliente utilizando a vaga.
     * @return true se estiver dentro do turno, false caso contrário.
     */
    private boolean estaDentroDoTurno(LocalDateTime horario, Cliente cliente) {
        int hora = horario.getHour();

        switch (cliente.getModalidade()) {
            case DE_TURNO:
                switch (cliente.getTurnoEscolhido()) {
                    case MANHA:
                        return hora >= 8 && hora <= 12;
                    case TARDE:
                        return hora > 12 && hora <= 18;
                    case NOITE:
                        return hora > 18 && hora <= 23;
                }
                break;
            default:
                break;
        }
        return false;
    }

}
