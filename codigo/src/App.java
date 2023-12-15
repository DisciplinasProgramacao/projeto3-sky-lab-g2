import java.io.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;

public class App {

    static Scanner teclado;
    /**
    * "Limpa" a tela (códigos de terminal VT-100)
    */
   public static void limparTela() {
       System.out.print("\033[H\033[2J");
       System.out.flush();
   }

    /**
    * Pausa para leitura de mensagens em console
    * 
    * @param teclado Scanner de leitura
    */
   static void pausa() {
       System.out.println("Enter para continuar.");
       teclado.nextLine();
    }

    /**
     * Encapsula uma leitura de teclado, com mensagem personalizada. A mensagem sempre é completada com ":". Retorna uma string 
     * que pode ser posteriormente convertida.
     * @param mensagem A mensagem a ser exibida, sem pontuação final.
     * @return String lida do usuário.
     */
    public static String leitura(String mensagem){
        System.out.print(mensagem+": ");
        return teclado.nextLine();
    }

    /**
     * Método para montagem de menu. Lê as opções de um arquivo e as numera automaticamente a partir de 1.
     *
     * @param nomeArquivo Nome do arquivo texto com as opções (uma por linha)
     * @return Opção do usuário (int)
     */
    public static int menu(String nomeArquivo) {
        try {
            limparTela();
            File arqMenu = new File(nomeArquivo);

            // Verifique se o arquivo existe antes de tentar lê-lo
            if (!arqMenu.exists()) {
                throw new FileNotFoundException("Arquivo não encontrado: " + nomeArquivo);
            }

            Scanner leitor = new Scanner(arqMenu, "UTF-8");
            System.out.println(leitor.nextLine());
            System.out.println("==========================");
            int contador = 1;
            while (leitor.hasNextLine()) {
                System.out.println(contador + " - " + leitor.nextLine());
                contador++;
            }
            System.out.println("0 - Sair");
            System.out.print("\nSua opção: ");
            int opcao = Integer.parseInt(teclado.nextLine());
            leitor.close();
            return opcao;
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return 0; // Ou outro valor padrão, indicando uma opção inválida
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            return 0; // Ou outro valor padrão, indicando uma opção inválida
        }
    }

    /**
     * Encapsula a ação de imprimir o relatório do estacionamento. Se a estacionamento for nula, informa que o veiculo não foi aberto.
     * @param estacionamento O estacionamento para obter o relatório.
     */
    public static void relatorio(Estacionamento estacionamento){
        limparTela();
        if(estacionamento!=null)
            System.out.println(estacionamento.toString());
        else
            System.out.println("O estacionamento não existe!");
    }

    /**
     * Encapsula a ação de acrescentar Clientes ao estacionamento. Interage com o usuário para saber quantos ingredientes
     * e, depois, para informar o sucesso ou não da modificação. Se a comida for nula, não permite interação.
     * @param comida A comida a ser modificada.
     * @throws ClienteJaExistenteException
     */
    public static void acrescentarClientes(Estacionamento estacionamento) throws ClienteJaExistenteException{

        if (estacionamento==null) {
            System.out.println("Crie um estacionamento primeiro.");
        } else {
            int quantos = Integer.parseInt(leitura("Quantidade de clientes para cadastrar"));
            Cliente[] clientes = new Cliente[quantos]; 

            for(int i=0; i<quantos; i++) {
                clientes[i] = new Cliente(leitura("Digite o nome do seu cliente"), leitura("Digite o ID do seu cliente"));
                String modalidadeInput = leitura("Digite a modalidade do seu cliente: \nT para Turno\nM para mensalista\nH para horista"); 

                if (modalidadeInput.equalsIgnoreCase("T")) {
                    clientes[i].setModalidade(Cliente.ModalidadeCliente.DE_TURNO);
                
                    // Adicione aqui a configuração do turno
                    String turnoInput = leitura("Digite o turno desejado (M - Manhã, T - Tarde, N - Noite): ");
                    switch (turnoInput.toUpperCase()) {
                        case "M":
                            clientes[i].setTurnoEscolhido(Turno.MANHA);
                            break;
                        case "T":
                            clientes[i].setTurnoEscolhido(Turno.TARDE);
                            break;
                        case "N":
                            clientes[i].setTurnoEscolhido(Turno.NOITE);
                            break;
                        default:
                            System.out.println("Opção inválida para turno. Será definido como Manhã por padrão.");
                            clientes[i].setTurnoEscolhido(Turno.MANHA);
                            break;
                    }
                } else if (modalidadeInput.equalsIgnoreCase("M")) {
                    clientes[i].setModalidade(Cliente.ModalidadeCliente.MENSALISTA);
                } else if (modalidadeInput.equalsIgnoreCase("H")) {
                    clientes[i].setModalidade(Cliente.ModalidadeCliente.HORISTA);
                } else {
                    System.out.println("Opção inválida. A modalidade será definida como HORISTA por padrão.");
                    clientes[i].setModalidade(Cliente.ModalidadeCliente.HORISTA);
                }
                
                
                try {
                    estacionamento.addCliente(clientes[i]);
                } catch (ClienteJaExistenteException e) {
                    System.out.println("Este cliente já existe, deseja adicionar outro? Responda S/N."); 

                    if (teclado.nextLine().toLowerCase().equals("s")){
                        clientes[i] = new Cliente(leitura("Digite o nome do seu cliente"), leitura("Digite o ID do seu cliente:"));
                        estacionamento.addCliente(clientes[i]);
                    } else {
                        pausa();
                    }
                }
            }
        }
    }

    /**
     * Encapsula a ação de acrescentar Clientes ao estacionamento. Interage com o usuário para saber quantos ingredientes
     * e, depois, para informar o sucesso ou não da modificação. Se a comida for nula, não permite interação.
     * @param estacionamento O estacionamento onde o veículo será cadastrado.
     * @throws ClienteNaoExisteException Se o cliente não for encontrado.
     * @throws VeiculoJaExistenteException Se o veículo já existir no estacionamento.
     */
    public static void acrescentarVeiculo(Estacionamento estacionamento) {
        try {
            if (estacionamento == null) {
                System.out.println("Crie um estacionamento primeiro.");
            } else {
                String idCliente = leitura("Digite o id do cliente dono do(s) veículo(s) a ser cadastrado");
                Cliente cliente = estacionamento.encontrarCliente(idCliente);
    
                if (cliente != null) {
                    int quantos = Integer.parseInt(leitura("Quantidade de veículos para cadastrar"));
                    Veiculo[] veiculos = new Veiculo[quantos];
    
                    for (int i = 0; i < quantos; i++) {
                        boolean placaDuplicada;
                        do {
                            String placa = leitura("Digite a placa do veículo");
                            veiculos[i] = new Veiculo(placa);
                            placaDuplicada = false;
    
                            for (Veiculo veiculoExistente : estacionamento.getVeiculos()) {
                                if (veiculoExistente.getPlaca().equalsIgnoreCase(placa)) {
                                    System.out.println("Este veículo já existe no estacionamento. Digite uma placa diferente.");
                                    placaDuplicada = true;
                                    break;
                                }
                            }
    
                            if (!placaDuplicada) {
                                try {
                                    estacionamento.addVeiculo(veiculos[i], cliente.getId());
                                    veiculos[i].setCliente(cliente);
                                } catch (VeiculoJaExistenteException e) {
                                    
                                    e.printStackTrace();
                                }
                            }
                        } while (placaDuplicada);
                    }
                }
            }
        } catch (ClienteNaoExisteException e) {
            System.out.println("Erro: Cliente não existe.");
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    /**
     * Retira um veículo do estacionamento e calcula o valor a ser pago.
     *
     * @param placa A placa do veículo que está saindo.
     * @param saida A data e hora de saída do veículo.
     * @return O valor a ser pago pelo uso da vaga.
     * @throws UsoDeVagaFinalizadoException Se o uso da vaga já estiver finalizado.
     * @throws VeiculoNaoExisteException   Se o veículo não for encontrado.
     */
    public static double retirarVeiculo(Estacionamento estacionamento) throws DuracaoMinimaNaoAtendidaException {
        try {
            String placaVeiculo = leitura("Digite a placa do veículo que deseja retirar da vaga");

            Veiculo veiculo = estacionamento.encontrarVeiculo(placaVeiculo);

            if (veiculo == null) {
                throw new VeiculoNaoExisteException();
            }

            Vaga vaga = veiculo.getVaga();

            if (vaga == null) {
                throw new UsoDeVagaNaoExisteException();
            }

            LocalDateTime horaSaida = LocalDateTime.parse(leitura("Digite o horário de saída"));
            double valorAPagar = estacionamento.sair(veiculo, horaSaida);
        
            
            if (valorAPagar > 0.0) {
                System.out.println("Veículo retirado da vaga " + vaga.getId());
                System.out.println("Valor a pagar: R$" + valorAPagar + "0");
                vaga.sair();
                return estacionamento.sair(veiculo, horaSaida);
            } else {
                throw new DuracaoMinimaNaoAtendidaException();
            }

        } catch (UsoDeVagaNaoExisteException e) {
            System.out.println("Erro: Uso de vaga finalizado.");
            
        } catch (VeiculoNaoExisteException e) {
            System.out.println("Erro: Veículo não encontrado.");
            
        } catch (DateTimeParseException e) {
            System.out.println("Erro: Formato de data e hora inválido.");
            
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        return 0.0; 
    }

    public static String alterarModalidadeCliente(Cliente cliente) {
                String modalidadeInput = leitura("Digite a modalidade do seu cliente que você deseja alterar: \nT para Turno\nM para mensalista\nH para horista"); 
                        if (modalidadeInput.equalsIgnoreCase("T")) {
                    cliente.setModalidade(Cliente.ModalidadeCliente.DE_TURNO);
                
                    String turnoInput = leitura("Digite o turno desejado (M - Manhã, T - Tarde, N - Noite): ");
                    switch (turnoInput.toUpperCase()) {
                        case "M":
                            cliente.setTurnoEscolhido(Turno.MANHA);
                            break;
                        case "T":
                            cliente.setTurnoEscolhido(Turno.TARDE);
                            break;
                        case "N":
                            cliente.setTurnoEscolhido(Turno.NOITE);
                            break;
                        default:
                            System.out.println("Opção inválida para turno. Será definido como Manhã por padrão.");
                            cliente.setTurnoEscolhido(Turno.MANHA);
                            break;
                    }
                } else if (modalidadeInput.equalsIgnoreCase("M")) {
                    cliente.setModalidade(Cliente.ModalidadeCliente.MENSALISTA);
                } else if (modalidadeInput.equalsIgnoreCase("H")) {
                    cliente.setModalidade(Cliente.ModalidadeCliente.HORISTA);
                } else {
                    System.out.println("Opção inválida. A modalidade será definida como HORISTA por padrão.");
                    cliente.setModalidade(Cliente.ModalidadeCliente.HORISTA);
                } 

        return "O plano do cliente '" + cliente.getNome() + "' foi alterado para '" + cliente.getModalidade() + "'.";

    }


    /**
     * Estaciona um veículo no estacionamento, associando-o a uma vaga disponível e registrando o uso da vaga.
     * Também oferece serviços adicionais ao veículo, como manobrista, lavagem ou polimento.
     *
     * @param estacionamento O estacionamento onde o veículo será estacionado.
     * @throws VeiculoNaoExisteException Se o veículo não for encontrado no estacionamento.
     * @throws VagaOcupadaException      Se não houver vagas disponíveis ou se a vaga estiver ocupada.
    */
    public static void estacionarVeiculo(Estacionamento estacionamento) throws VeiculoNaoExisteException, VagaOcupadaException {
        if (estacionamento == null) {
            System.out.println("Adicione um veículo primeiro.");
        } else {
            String placaVeiculo = leitura("Digite a placa do veículo que você deseja estacionar");
            Veiculo veiculo = estacionamento.encontrarVeiculo(placaVeiculo);
    
            if (veiculo == null) {
                System.out.println("Veículo não encontrado no estacionamento.");
                return;
            }
    
            List<Vaga> vagasGeradas = estacionamento.getVagas();
    
            Vaga vagaDisponivel = null;
            for (Vaga vaga : vagasGeradas) {
                if (vaga.disponivel()) {
                    vagaDisponivel = vaga;
                    break;
                }
            }
    
            if (vagaDisponivel == null) {
                System.out.println("Não há vagas disponíveis. Estacionamento lotado.");
            } else {
                try {
                    estacionamento.estacionar(veiculo, vagaDisponivel, LocalDateTime.parse(leitura("Digite o horário de entrada deste veículo")));
                    vagaDisponivel.estacionar();
    
                    veiculo.setVaga(vagaDisponivel);
                    UsoDeVaga uso = new UsoDeVaga(vagaDisponivel);
                    uso.setCliente(veiculo.getCliente());
                    servicoAdicional(estacionamento, veiculo);
                    
                } catch (VagaOcupadaException e) {
                    System.out.println("Vaga ocupada. Escolha outra vaga.");
                }
            }
        }
    }

    /**
     * Oferece serviços adicionais a um veículo estacionado, como manobrista, lavagem ou polimento.
     *
     * @param estacionamento O estacionamento onde o veículo está estacionado.
     * @param veiculo        O veículo ao qual os serviços adicionais serão aplicados.
     * @throws FileNotFoundException Em caso de problema ao ler o arquivo de serviços adicionais.
     */
    public static void servicoAdicional(Estacionamento estacionamento, Veiculo veiculo) {
        String nomeArq = "menuServicos.txt";
        int opcaoServico = -1;

        limparTela();

        try {
            opcaoServico = menu(nomeArq);

            if (opcaoServico == 0) {
                System.out.println("Nenhum serviço adicionado.");
            } else {
                switch (opcaoServico) {
                    case 1 -> System.out.println(estacionamento.contratarServico(veiculo.getPlaca(), Servico.MANOBRISTA));
                    case 2 -> System.out.println(estacionamento.contratarServico(veiculo.getPlaca(), Servico.LAVAGEM));
                    case 3 -> System.out.println(estacionamento.contratarServico(veiculo.getPlaca(), Servico.POLIMENTO));
                    default -> System.out.println("Nenhum serviço adicionado.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
        } catch (Exception e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Erro: Arquivo não encontrado.");
            } else if (e.getMessage() != null && e.getMessage().isEmpty()) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um valor válido.");
            } else {
                System.out.println("Erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Submenu para uso das funcionalidades do estacionamento: ações de incluir clientes e veículos, mostrar relatório do estacionamento, procurar cliente ou veículo e sair (cancelar).
     *
     * @param estacionamento Estacionamento a ser modificado. Se null, retorna null sem realizar procedimentos.
     * @return Retorna o estacionamento modificado, em caso de necessidade de armazená-lo ou usá-lo em outro ponto do sistema. Caso o menu seja iniciado com um estacionamento não criado (nulo), ignora os procedimentos e retorna nulo.
     * @throws FileNotFoundException Se ocorrer um problema ao tentar ler o arquivo do menu.
     * @throws ClienteJaExistenteException Se um cliente já existente for adicionado novamente.
     * @throws ClienteNaoExisteException Se um cliente não existente for referenciado.
     * @throws VeiculoJaExistenteException Se um veículo já existente for adicionado novamente.
     * @throws VeiculoNaoExisteException Se um veículo não existente for referenciado.
     * @throws VagaOcupadaException Se uma vaga já ocupada for utilizada para estacionar um veículo.
     * @throws UsoDeVagaFinalizadoException Se o uso de uma vaga for finalizado antes de sair.
     */
    public static Estacionamento menuEstacionamento(Estacionamento estacionamento) throws FileNotFoundException, ClienteJaExistenteException, ClienteNaoExisteException, VeiculoJaExistenteException, VeiculoNaoExisteException, VagaOcupadaException, UsoDeVagaFinalizadoException {
            if (estacionamento == null)
            return null;

        String nomeArq = "menuEstacionamento.txt";
        int opcao = -1;

        while (opcao != 0) {
            limparTela();
            opcao = menu(nomeArq);
            switch (opcao) {
                case 1 -> {
                    System.out.println("\nAdicionar clientes ao estacionamento");
                    acrescentarClientes(estacionamento);
                }
                case 2 -> {
                    System.out.println("\nAcrescentando um veículo a um cliente");
                    acrescentarVeiculo(estacionamento);
                }

                case 3 -> {
                    System.out.println("\nEstacionar ou retirar veículo da vaga");
                    menuVeiculo(estacionamento);
                }

                case 4 -> {
                    System.out.println(estacionamento.dataToText());
                }

                case 5 -> {
                    System.out.println(alterarModalidadeCliente(estacionamento.encontrarCliente(leitura("Digite o id do cliente no qual você deseja alterar a modalidade"))));
                }

                case 6 -> {
                    int mes = Integer.parseInt(leitura("Digite o número do mês desejado"));
                    System.out.println(estacionamento.top5Clientes(mes));
                }
                case 7 -> {
                    System.out.println(estacionamento.formatarClienteEncontrado(leitura("Digite o ID do cliente que deseja buscar")));
                }
                case 8 -> {
                    System.out.println(estacionamento.formatarVeiculoEncontrado(leitura("Digite a placa do veículo que deseja buscar")));
                }
                case 9 -> {
                    System.out.println(estacionamento.valorMedioPorUso());
                }
                case 10 -> {
                    int mes = Integer.parseInt(leitura("Digite o número do mês desejado"));
                    System.out.println(estacionamento.arrecadacaoNoMes(mes));
                }
                case 11 -> {
                    System.out.println(estacionamento.relatorioArrecadacaoCliente(leitura("Digite o ID do cliente no qual você deseja saber a arrecadação total")));
                }

                case 12 -> {
                    System.out.println(estacionamento.calcularArrecadacaoMediaHoristas(Integer.parseInt(leitura("Digite o mês desejado"))));
                }

                case 13 -> {
                    System.out.println(estacionamento.calcularMediaUsosMensalistas());
                }

                case 14 -> {
                    System.out.println(estacionamento.arrecadacaoTotalEstacionamento());
                }

                case 0 -> {
                    break;
                }
            }
            pausa();
        }
        return estacionamento;
    }

    public static Estacionamento menuVeiculo(Estacionamento estacionamento)
        throws FileNotFoundException, ClienteJaExistenteException, ClienteNaoExisteException, VeiculoJaExistenteException, VeiculoNaoExisteException, VagaOcupadaException, UsoDeVagaFinalizadoException {
        if (estacionamento == null)
            return null;

        String nomeArq = "menuVeiculo.txt";
        int opcao = -1;

        while (opcao != 0) {
            try {
                limparTela();
                opcao = menu(nomeArq);

                switch (opcao) {
                    case 1 -> {
                        System.out.println("\nEstacionar veículo em uma vaga");
                        estacionarVeiculo(estacionamento);
                    }
                    case 2 -> {
                        System.out.println("\nRetirar veículo da vaga");
                        retirarVeiculo(estacionamento);
                    }
                    case 0 -> {
                        break;
                    }
                    default -> System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Por favor, insira um número.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }

            pausa();
        }

        return estacionamento;
        }

    public static void main(String[] args) throws ClienteJaExistenteException, ClienteNaoExisteException, VeiculoJaExistenteException, VeiculoNaoExisteException, VagaOcupadaException, UsoDeVagaFinalizadoException, IOException {

        teclado = new Scanner(System.in);
        String nomeArq = "menuPrincipal.txt";
        int opcao = -1;
    
        EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO("estacionamento.txt");
        Estacionamento estacionamentoAtual = null;
    
        while(opcao != 0){
            limparTela();
            opcao = menu(nomeArq);
            switch(opcao){
                case 1 -> {
                    estacionamentoAtual = new Estacionamento(leitura("Digite o nome do estacionamento"), Integer.parseInt(leitura("Número de fileiras")), Integer.parseInt(leitura("Vagas por fila")));

                    try {
                        
                        estacionamentoDAO.abrirEscrita();
                        menuEstacionamento(estacionamentoAtual);
                        estacionamentoDAO.add(estacionamentoAtual);
                        estacionamentoDAO.fechar();
                    } catch (IOException e) {

                       System.out.println("Erro ao abrir escrita.");
                    }
                }
    
                case 2 -> {
                    String nomeEstacionamentoConsulta = leitura("Digite o nome do estacionamento que deseja consultar");
                
                    Estacionamento[] estacionamentos = estacionamentoDAO.getAll();
                
                    for (Estacionamento estacionamento : estacionamentos) {
                        if (estacionamento.getNome().equalsIgnoreCase(nomeEstacionamentoConsulta)) {
                            estacionamentoAtual = estacionamento;
                            System.out.println("Estacionamento encontrado:");
                            estacionamentoAtual.dataToText();
                
                            String opcaoAtualizar = leitura("Deseja atualizar este estacionamento? (S para Sim, N para Não)");
                            if (opcaoAtualizar.equalsIgnoreCase("S")) {
                                try {
                                    estacionamentoDAO.abrirEscrita();
                                    estacionamentoDAO.add(estacionamentoAtual);
                                    System.out.println("Estacionamento atualizado com sucesso.");
                                } catch (IOException e) {
                                    System.out.println("Erro ao atualizar estacionamento: " + e.getMessage());
                                } finally {
                                    estacionamentoDAO.fechar();
                                }
                            }
                
                            // Exibe o menu do estacionamento
                            menuEstacionamento(estacionamentoAtual);
                
                            break;
                        }
                    }
                
                    if (estacionamentoAtual == null) {
                        System.out.println("Estacionamento não encontrado.");
                    }
                }

                case 3 -> {
                    estacionamentoDAO.listarEstacionamentosDecrescente();
                    pausa();
                    break;
                }

                case 4 -> {
                    estacionamentoDAO.addAll(Populador.gerarEstacionamentos());
                }
                
            }
        }
    
        teclado.close();
    }
    
}