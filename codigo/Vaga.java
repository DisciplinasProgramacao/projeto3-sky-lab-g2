public class Vaga {

    private String id;
    private boolean disponivel;

    public Vaga(String id) {
        this.id = id;
        this.disponivel = true;
    }

    public boolean estacionar() {
        if (disponivel) {
            disponivel = false;
            return true; 
        } else {
            System.out.println("Vaga " + id + " já está ocupada.");
            return false; 
        }
    }

    public boolean sair() {
        if (!disponivel) {
            disponivel = true; 
            return true; 
        } else {
            System.out.println("Vaga " + id + " já está disponível.");
            return false; 
        }
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public String getId() {
        return id;
    }
}
