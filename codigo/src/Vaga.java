public class Vaga {

    private String id;
    private boolean disponivel;

    public Vaga(String id) {
        this.id = id;
        this.disponivel = true;
    }

    public boolean estacionar() {
        if (disponivel) {
            this.disponivel = false;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean sair() {
        if (!disponivel) {
            this.disponivel = true;
            return true;
        } else {
            return false;
        }
    }
    

    public boolean disponivel() {
        return disponivel;
    }

    public String getId() {
        return id;
    }
}
