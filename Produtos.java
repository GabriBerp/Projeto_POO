import java.util.Random;

public class Produtos {
    public int tipo;
    public boolean embalado;
    public boolean inspecionado;
    public boolean temProblema;

    private boolean gerarProblema() {
        // Gera um número aleatório entre 0 e 3
        // Se o número for 0, o produto terá um problema (25% de chance)
        Random random = new Random();
        int chance = random.nextInt(4);
        return chance == 0;
    }

    public Produtos(int tipo) {
        this.tipo = tipo;
        this.embalado = false;
        this.inspecionado = false;
        this.temProblema = gerarProblema();
    }
}
