import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Cria dados fictícios para que o sistema possa ser testado.
 */
public class Populador {

    public static List<Estacionamento> gerarEstacionamentos() throws ClienteJaExistenteException {

        List<Estacionamento> estacionamentos = new ArrayList<>();

        {
            Estacionamento estacionamento = new Estacionamento("Estacionamento 1", 2, 4);

            Cliente cliente1 = new Cliente("João da Silva", "ID1");
            cliente1.setModalidade(Cliente.ModalidadeCliente.HORISTA);
            estacionamento.addCliente(cliente1);

            Cliente cliente2 = new Cliente("Milena Soares", "ID2");
            cliente2.setModalidade(Cliente.ModalidadeCliente.HORISTA);
            estacionamento.addCliente(cliente2);

            Veiculo veiculo1 = new Veiculo("ABC123");
            veiculo1.setCliente(cliente1);
            Veiculo veiculo2 = new Veiculo("DEF456");
            veiculo2.setCliente(cliente2);

            List<Vaga> vagasGeradas = estacionamento.getVagas();
    
            Vaga vagaDisponivel = null;
            for (Vaga vaga : vagasGeradas) {
                if (vaga.disponivel()) {
                    vagaDisponivel = vaga;
                    break;
                }
            }

            veiculo1.estacionar(vagaDisponivel, LocalDateTime.of(2023, 1, 1, 10, 0, 0));
            veiculo1.adicionarServicoContratado(Servico.LAVAGEM);
            veiculo1.setCusto(veiculo1.sair(LocalDateTime.of(2023, 1, 1, 12, 0, 0)));

            veiculo1.estacionar(vagaDisponivel, LocalDateTime.of(2023, 2, 1, 7, 0, 0));
            veiculo1.adicionarServicoContratado(Servico.POLIMENTO);
            veiculo1.setCusto(veiculo1.getCusto()+veiculo1.sair(LocalDateTime.of(2023, 2, 1, 18, 0, 0)));

            veiculo2.estacionar(vagaDisponivel, LocalDateTime.of(2023, 1, 1, 10, 0, 0));
            veiculo2.adicionarServicoContratado(Servico.MANOBRISTA);
            veiculo2.setCusto(veiculo2.sair(LocalDateTime.of(2023, 1, 1, 12, 0, 0)));

            cliente1.addVeiculo(veiculo1);
            cliente2.addVeiculo(veiculo2);

            estacionamentos.add(estacionamento);
        }

        {
            Estacionamento estacionamento2 = new Estacionamento("TestPark", 1, 1);
        
            Cliente cliente3 = new Cliente("Paula de Freitas", "ID3");
            cliente3.setModalidade(Cliente.ModalidadeCliente.HORISTA);
            estacionamento2.addCliente(cliente3);
        
            Veiculo veiculo3 = new Veiculo("DEF000");
            veiculo3.setCliente(cliente3);
        
            List<Vaga> vagasGeradas = estacionamento2.getVagas();
        
            Vaga vagaDisponivel = vagasGeradas.stream().filter(Vaga::disponivel).findFirst().orElse(null);
        
            veiculo3.estacionar(vagaDisponivel, LocalDateTime.of(2023, 1, 1, 10, 0, 0));
            veiculo3.setCusto(veiculo3.sair(LocalDateTime.of(2023, 1, 1, 11, 0, 0)));

            veiculo3.estacionar(vagaDisponivel, LocalDateTime.of(2023, 1, 1, 8, 0, 0));
            veiculo3.adicionarServicoContratado(Servico.POLIMENTO);
            veiculo3.setCusto(veiculo3.getCusto()+veiculo3.sair(LocalDateTime.of(2023, 1, 1, 13, 0, 0)));
        
            cliente3.addVeiculo(veiculo3);
        
            estacionamentos.add(estacionamento2);
        }

        {
            Estacionamento estacionamento3 = new Estacionamento("Estacionamento 2", 3, 5);
        
            Cliente cliente4 = new Cliente("Maria Oliveira", "ID4");
            cliente4.setModalidade(Cliente.ModalidadeCliente.MENSALISTA);
            estacionamento3.addCliente(cliente4);
        
            Cliente cliente5 = new Cliente("Carlos Santos", "ID5");
            cliente5.setModalidade(Cliente.ModalidadeCliente.DE_TURNO);
            cliente5.setTurnoEscolhido(Cliente.Turno.MANHA);
            estacionamento3.addCliente(cliente5);
        
            List<Vaga> vagasGeradas = estacionamento3.getVagas();
        
            Vaga vagaDisponivel = vagasGeradas.stream().filter(Vaga::disponivel).findFirst().orElse(null);
        
            Veiculo veiculo1 = new Veiculo("XYZ789");
            veiculo1.setCliente(cliente4);
        
            Veiculo veiculo2 = new Veiculo("GHI987");
            veiculo2.setCliente(cliente5);
        
            veiculo1.estacionar(vagaDisponivel, LocalDateTime.of(2023, 3, 1, 8, 0, 0));
            veiculo1.adicionarServicoContratado(Servico.POLIMENTO);
            veiculo1.setCusto(veiculo1.sair(LocalDateTime.of(2023, 3, 1, 18, 0, 0)));
        
            veiculo2.estacionar(vagaDisponivel, LocalDateTime.of(2023, 3, 1, 13, 0, 0));
            veiculo2.adicionarServicoContratado(Servico.MANOBRISTA);
            veiculo2.setCusto(veiculo2.sair(LocalDateTime.of(2023, 3, 1, 15, 0, 0)));
        
            cliente4.addVeiculo(veiculo1);
            cliente5.addVeiculo(veiculo2);
        
            estacionamentos.add(estacionamento3);
        }
        
        {
            Estacionamento estacionamento = new Estacionamento("Estacionamento 3", 1, 2);
        
            Cliente cliente6 = new Cliente("Ana Silva", "ID6");
            cliente6.setModalidade(Cliente.ModalidadeCliente.HORISTA);
            estacionamento.addCliente(cliente6);
        
            Veiculo veiculo6 = new Veiculo("JKL456");
            veiculo6.setCliente(cliente6);
        
            List<Vaga> vagasGeradas = estacionamento.getVagas();
        
            Vaga vagaDisponivel = vagasGeradas.stream().filter(Vaga::disponivel).findFirst().orElse(null);
        
            veiculo6.estacionar(vagaDisponivel, LocalDateTime.of(2023, 5, 1, 9, 0, 0));
            veiculo6.adicionarServicoContratado(Servico.LAVAGEM);
            veiculo6.setCusto(veiculo6.sair(LocalDateTime.of(2023, 5, 1, 15, 0, 0)));
        
            cliente6.addVeiculo(veiculo6);
        
            estacionamentos.add(estacionamento);
        }

        {
            Estacionamento estacionamento = new Estacionamento("EstacioPark", 3, 5);

            Cliente cliente1 = new Cliente("Fernanda Oliveira", "ID101");
            cliente1.setModalidade(Cliente.ModalidadeCliente.MENSALISTA);
            estacionamento.addCliente(cliente1);

            Cliente cliente2 = new Cliente("Rodrigo Santos", "ID102");
            cliente2.setModalidade(Cliente.ModalidadeCliente.DE_TURNO);
            cliente2.setTurnoEscolhido(Cliente.Turno.TARDE);
            estacionamento.addCliente(cliente2);

            Veiculo veiculo1 = new Veiculo("ABC987");
            veiculo1.setCliente(cliente1);

            Veiculo veiculo2 = new Veiculo("XYZ456");
            veiculo2.setCliente(cliente2);

            List<Vaga> vagasGeradas = estacionamento.getVagas();

            Vaga vagaDisponivel1 = vagasGeradas.stream().filter(Vaga::disponivel).findFirst().orElse(null);
            Vaga vagaDisponivel2 = vagasGeradas.stream().filter(Vaga::disponivel).findFirst().orElse(null);

            veiculo1.estacionar(vagaDisponivel1, LocalDateTime.of(2023, 4, 1, 8, 0, 0));
            veiculo1.adicionarServicoContratado(Servico.LAVAGEM);
            veiculo1.setCusto(veiculo1.sair(LocalDateTime.of(2023, 4, 1, 18, 0, 0)));

            veiculo2.estacionar(vagaDisponivel2, LocalDateTime.of(2023, 4, 1, 12, 0, 0));
            veiculo2.adicionarServicoContratado(Servico.MANOBRISTA);
            veiculo2.setCusto(veiculo2.sair(LocalDateTime.of(2023, 4, 1, 14, 0, 0)));

            cliente1.addVeiculo(veiculo1);
            cliente2.addVeiculo(veiculo2);

            estacionamentos.add(estacionamento);
        }

        {
            Estacionamento estacionamento = new Estacionamento("GoodPark", 2, 3);

            Cliente cliente1 = new Cliente("Isabela Lima", "ID201");
            cliente1.setModalidade(Cliente.ModalidadeCliente.HORISTA);
            estacionamento.addCliente(cliente1);

            Cliente cliente2 = new Cliente("Rafaela Silva", "ID202");
            cliente2.setModalidade(Cliente.ModalidadeCliente.MENSALISTA);
            estacionamento.addCliente(cliente2);

            Veiculo veiculo1 = new Veiculo("DEF654");
            veiculo1.setCliente(cliente1);

            Veiculo veiculo2 = new Veiculo("GHI321");
            veiculo2.setCliente(cliente2);

            List<Vaga> vagasGeradas = estacionamento.getVagas();

            Vaga vagaDisponivel1 = vagasGeradas.stream().filter(Vaga::disponivel).findFirst().orElse(null);
            Vaga vagaDisponivel2 = vagasGeradas.stream().filter(Vaga::disponivel).findFirst().orElse(null);

            veiculo1.estacionar(vagaDisponivel1, LocalDateTime.of(2023, 5, 1, 9, 0, 0));
            veiculo1.adicionarServicoContratado(Servico.LAVAGEM);
            veiculo1.setCusto(veiculo1.sair(LocalDateTime.of(2023, 5, 1, 15, 0, 0)));

            veiculo2.estacionar(vagaDisponivel2, LocalDateTime.of(2023, 5, 1, 12, 0, 0));
            veiculo2.adicionarServicoContratado(Servico.MANOBRISTA);
            veiculo2.setCusto(veiculo2.sair(LocalDateTime.of(2023, 5, 1, 14, 0, 0)));

            cliente1.addVeiculo(veiculo1);
            cliente2.addVeiculo(veiculo2);

            estacionamentos.add(estacionamento);
        } 

        {
            Estacionamento estacionamento = new Estacionamento("Estacionamento XYZ", 3, 2);

            Cliente cliente1 = new Cliente("João Horista", "ID1");
            cliente1.setModalidade(Cliente.ModalidadeCliente.HORISTA);
            estacionamento.addCliente(cliente1);
    
            Cliente cliente2 = new Cliente("Maria Horista", "ID2");
            cliente2.setModalidade(Cliente.ModalidadeCliente.HORISTA);
            estacionamento.addCliente(cliente2);
    
            Cliente cliente3 = new Cliente("Carlos Horista", "ID3");
            cliente3.setModalidade(Cliente.ModalidadeCliente.HORISTA);
            estacionamento.addCliente(cliente3);
    
            Cliente cliente4 = new Cliente("Ana Mensalista", "ID4");
            cliente4.setModalidade(Cliente.ModalidadeCliente.MENSALISTA);
            estacionamento.addCliente(cliente4);
    
            Cliente cliente5 = new Cliente("Paulo Turno Manhã", "ID5");
            cliente5.setModalidade(Cliente.ModalidadeCliente.DE_TURNO);
            cliente5.setTurnoEscolhido(Cliente.Turno.MANHA);
            estacionamento.addCliente(cliente5);
    
            Cliente cliente6 = new Cliente("Fernanda Turno Tarde", "ID6");
            cliente6.setModalidade(Cliente.ModalidadeCliente.DE_TURNO);
            cliente6.setTurnoEscolhido(Cliente.Turno.TARDE);
            estacionamento.addCliente(cliente6);
    
            List<Vaga> vagasGeradas = estacionamento.getVagas();
    
            for (int i = 0; i < 6; i++) {
                Veiculo veiculo = new Veiculo("ABC" + (i + 123));
                veiculo.setCliente(estacionamento.getClientes().get(i));
    
                Vaga vagaDisponivel = vagasGeradas.stream().filter(Vaga::disponivel).findFirst().orElse(null);
    
                veiculo.estacionar(vagaDisponivel, LocalDateTime.of(2023, 6, 1, 8, 0, 0));
                veiculo.adicionarServicoContratado(Servico.LAVAGEM);
                veiculo.setCusto(veiculo.sair(LocalDateTime.of(2023, 6, 1, 18, 0, 0)));
    
                estacionamento.getClientes().get(i).addVeiculo(veiculo);
            }
            estacionamentos.add(estacionamento);
        }

        return estacionamentos;

    }

}
