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

    public boolean disponivel(boolean estado) {
        if(estado) {
            return disponivel = true; 
        } else {
            return disponivel = false;
        }
    }

    public String getId() {
        return id;
    }
}
