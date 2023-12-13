    public enum Turno {
        MANHA(8, 12),
        TARDE(12, 18),
        NOITE(18, 23);

        private final int inicio;
        private final int fim;

        Turno(int inicio, int fim) {
            this.inicio = inicio;
            this.fim = fim;
        }

        public int getInicio() {
            return inicio;
        }

        public int getFim() {
            return fim;
        }
    }