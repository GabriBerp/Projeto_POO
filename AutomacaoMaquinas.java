import java.util.ArrayList;

class AutomacaoMaquinas {
    private ArrayList<Maquina> maquinas;
    private boolean inicializada;
    public int tipo;
    public int id;
    private int produtosProduzidos;
    private int produtosEmbalados;
    private int produtosInspecionados;
    private int produtosEntregues;
    public int quant_prod;
    public int quant_insp;
    public int quant_emba;
    public int quant_entr;

    public int getProdutosProduzidos() {
        return produtosProduzidos;
    }

    public int getProdutosEmbalados() {
        return produtosEmbalados;
    }

    public int getProdutosInspecionados() {
        return produtosInspecionados;
    }

    public int getProdutosEntregues() {
        return produtosEntregues;
    }

    public void atualizarProdutosProduzidos(int quantidade) {
        produtosProduzidos += quantidade;
    }

    public void atualizarProdutosEmbalados(int quantidade) {
        produtosEmbalados += quantidade;
    }

    public void atualizarProdutosInspecionados(int quantidade) {
        produtosInspecionados += quantidade;
    }

    public void atualizarProdutosEntregues(int quantidade) {
        produtosEntregues += quantidade;
    }

    public AutomacaoMaquinas(int id) {
        this.maquinas = new ArrayList<Maquina>();
        this.inicializada = false;
        this.id = id;

        this.produtosProduzidos = 0;
        this.produtosInspecionados = 0;
        this.produtosEmbalados = 0;
        this.produtosEntregues = 0;
    }

    public boolean foiInicializada() {
        return this.inicializada;
    }

    public void inicializar() {
        this.inicializada = true;
    }

    public ArrayList<Maquina> PegarMaquinas() {
        return this.maquinas;
    }

    public void adicionarMaquina(Maquina maquina) {
        maquinas.add(maquina);
        System.out.println("\nMáquina com ID: " + maquina.id + ", adicionada.\n");
    }

    public void ligarTodasMaquinas() {
        for (Maquina maquina : maquinas) {
            maquina.ligar();
        }
        System.out.println("\nTodas as máquinas foram ligadas.");
    }

    public void desligarTodasMaquinas() {
        for (Maquina maquina : maquinas) {
            maquina.desligar();
        }
        System.out.println("\nTodas as máquinas foram desligadas.");
    }
}
