import java.io.*;
import java.time.*;
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
     * @param nomeArquivo Nome do arquivo texto com as opções (uma por linha)
     * @return Opção do usuário (int)
     * @throws FileNotFoundException em caso de arquivo não encontrado.
     */
    public static int menu(String nomeArquivo) throws FileNotFoundException {
        limparTela();
        File arqMenu = new File(nomeArquivo);
        Scanner leitor = new Scanner(arqMenu, "UTF-8");
        System.out.println(leitor.nextLine());
        System.out.println("==========================");
        int contador = 1;
        while(leitor.hasNextLine()){
            System.out.println(contador + " - " + leitor.nextLine());
            contador++;
        }
        System.out.println("0 - Sair");
        System.out.print("\nSua opção: ");
        int opcao = Integer.parseInt(teclado.nextLine());
        leitor.close();
        return opcao;
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
            int quantos = Integer.parseInt(leitura("Quantidade de clientes para cadastrar:"));
            Cliente[] clientes = new Cliente[quantos]; 

            for(int i=0; i<quantos; i++) {
                clientes[i] = new Cliente(leitura("Digite o nome do seu cliente"), leitura("Digite o ID do seu cliente:"));
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
     * @param comida A comida a ser modificada.
     * @throws ClienteNaoExisteException
     * @throws VeiculoJaExistenteException
     */
    public static void acrescentarVeiculo(Estacionamento estacionamento) throws ClienteNaoExisteException, VeiculoJaExistenteException{

        if(estacionamento==null){
            System.out.println("Crie um estacionamento primeiro.");
        } else {
            Cliente cliente = estacionamento.encontrarCliente(leitura("Digite o id do cliente dono do(s) veículo(s) a ser cadastrado: "));

            if (cliente != null) {
                int quantos = Integer.parseInt(leitura("Quantidade de veículos para cadastrar:"));
                Veiculo[] veiculos = new Veiculo[quantos]; 

                    for(int i=0; i<quantos; i++) {
                    veiculos[i] = new Veiculo(leitura("Digite a placa do veículo"));
                    try {
                        estacionamento.addVeiculo(veiculos[i], cliente.getId());
                    } catch (VeiculoJaExistenteException e) {
                        System.out.println("Este veículo já existe, deseja adicionar outro? Responda S/N."); 

                        if (teclado.nextLine().toLowerCase().equals("s")){
                            veiculos[i] = new Veiculo(leitura("Digite a placa do veículo"));
                            estacionamento.addVeiculo(veiculos[i], cliente.getId());
                        } else {
                            pausa();
                        }
                    }
                }
            }
        }
    }

    /**
     * Submenu para inclusão de comidas: ações de incluir pizza ou sanduíche, mostrar relatório do veiculo, fechar veiculo e sair (cancelar).
     * @param veiculo veiculo já criado, vazio, para inclusão de comidas.
     * @return Retorna o veiculo modificado, em caso de necessidade de armazená-lo ou usá-lo em outro ponto do sistema. 
     * Caso o menu seja iniciado com um veiculo não criado (nulo), ignora os procedimentos e retorna nulo.
     * @throws ClienteJaExistenteException
     * @throws VeiculoJaExistenteException
     * @throws ClienteNaoExisteException
     */
    public static Veiculo menuEstacionamento(Estacionamento estacionamento) throws FileNotFoundException, ClienteJaExistenteException, ClienteNaoExisteException, VeiculoJaExistenteException{
        if (estacionamento==null)
            return null;

        String nomeArq = "menuEstacionamento.txt";
        int opcao = -1;
        Veiculo veiculo = null;

        while(opcao!=0){
            limparTela();
            opcao = menu(nomeArq);
            switch(opcao){
                case 1 -> {                                                           
                    System.out.println("\nAdicionar clientes ao estacionamento:");
                    acrescentarClientes(estacionamento);
                } 
                case 2-> {
                    System.out.println("\nConsultar um cliente:");
                    acrescentarVeiculo(estacionamento);
                } 

                case 3-> {
                    System.out.println("\nAcrescentando um veículo a um cliente:");
                    acrescentarVeiculo(estacionamento);
                } 

                case 4 ->{
                    System.out.println(estacionamento.dataToText());
                }
                case 0 ->{
                    break;
                }
            }
            pausa();
        }
        return veiculo;
    }



    public static void main(String[] args) throws FileNotFoundException, ClienteJaExistenteException, ClienteNaoExisteException, VeiculoJaExistenteException {
        
            teclado = new Scanner(System.in);
            String nomeArq = "menuPrincipal.txt";
            int opcao = -1;
            while(opcao!=0){
                limparTela();
                opcao = menu(nomeArq);
                switch(opcao){
                    case 1-> {
                    Estacionamento estacionamento = new Estacionamento(leitura("Digite o nome do estacionamento"), Integer.parseInt(leitura("Número de fileiras")), Integer.parseInt(leitura("Vagas por fila")));
                    menuEstacionamento(estacionamento);
                    }
                    
                }
            }

            teclado.close();
        }
    
    }

