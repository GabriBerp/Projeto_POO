import java.util.ArrayList;
import java.lang.Thread;

class AutomacaoMaquinas extends Thread {
  private ArrayList<Maquina> maquinas;
  private boolean inicializada;
  public int tipo;
  public int id;
  private int produtosProduzidos;
  private int produtosEmbalados;
  private int produtosInspecionados;
  private int produtosEntregues;
  protected Thread thread;
  private Monitoramento monitor;

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

  public synchronized void atualizarProdutosProduzidos(int quantidade) {
    produtosProduzidos += quantidade;
  }

  public synchronized void atualizarProdutosEmbalados(int quantidade) {
      produtosEmbalados += quantidade;
  }

  public synchronized void atualizarProdutosInspecionados(int quantidade) {
      produtosInspecionados += quantidade;
  }

  public synchronized void atualizarProdutosEntregues(int quantidade) {
      produtosEntregues += quantidade;
  }

  Thread dataUpdateThread = new Thread(() -> {
    while (true) {
        int production = getProdutosProduzidos();
        int packaging = getProdutosEmbalados();
        int inspection = getProdutosInspecionados();
        int delivery = getProdutosEntregues();

        // Atualize os dados na janela de visualização em tempo real
        monitor.updateData(production, packaging, inspection, delivery);

        // Espere um pouco antes de atualizar novamente
        try {
            Thread.sleep(1000); // Espere 1 segundo (1000 milissegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
});

  public AutomacaoMaquinas(int id) {
    this.maquinas = new ArrayList<Maquina>();
    this.inicializada = false;
    this.id = id;
    this.monitor = new Monitoramento();
    iniciarThread();
  }

  public int iniciarMonitoramento() {
    for (Maquina maquina : maquinas) {
        if (maquina.emFuncionamento) {
            monitor.setVisible(true); // Mostra a janela de monitoramento
            monitor.updateData(getProdutosProduzidos(), getProdutosEmbalados(), getProdutosInspecionados(), getProdutosEntregues());
            return 1;
        }
    }
    monitor.setVisible(false); // Esconde a janela de monitoramento se nenhuma máquina estiver em funcionamento
    return 0;
}

  public void iniciarThread(){
    if (thread == null || !thread.isAlive()) {
      thread = new Thread(new Runnable() {
        public void run() {
          // Lógica de execução da máquina
          while (true) {
              iniciarMonitoramento();
          }
        }
      });
      thread.start();
    }
  }

  public boolean foiInicializada(){
    return this.inicializada;
  }

  public void inicializar(){
    this.inicializada = true;
  }

  public ArrayList<Maquina> PegarMaquinas(){
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
