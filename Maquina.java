
import java.util.ArrayList;
import java.lang.Thread;


// Classe base para todas as máquinas
class Maquina extends Thread {
  protected int id;
  protected int tipo;
  protected boolean ligada;
  protected boolean emFuncionamento;
  protected Thread thread;
  protected static ArrayList<Produtos> produtos;
  protected static ArrayList<Produtos> produtos_entregando;
  protected AutomacaoMaquinas fabrica_pai;

  public Maquina(int id, int tipo, AutomacaoMaquinas pai) {
    this.id = id;
    this.tipo = tipo;
    this.ligada = false;
    this.emFuncionamento = false;
    this.fabrica_pai = pai;
    produtos = new ArrayList<>();
    produtos_entregando = new ArrayList<>();
  }

  public void iniciarThread(){};

  public void pararThread() {
    if (thread != null && thread.isAlive()) {
        thread.interrupt();
    }
  }

  public void ligar() {
    this.ligada = true;
    System.out.println("Máquina de ID: " + id + ", ligada.");
  }

  public void desligar() {
    this.ligada = false;
    if (this.emFuncionamento = true){
      this.emFuncionamento = false;
    }
    System.out.println("Máquina de ID: " + id + ", desligada.");
  }

  public void paraAutomatizar(){
    if (this.emFuncionamento == true){
      System.out.println("Máquina de ID: " + id + ", parou de trabalhar.");
      this.emFuncionamento = false;
      pararThread();
    }else{
      System.out.println("A Máquina não está trabalhando.");
    }
  }
}

// Máquina de produção
class MaquinaProducao extends Maquina {
  int produtos_produzidos;
  int produtos_produzidos_total;
  int limite;
  private static ArrayList<MaquinaProducao> todasAsMaquinasProducao = new ArrayList<>();

  public MaquinaProducao(int id, int tipo, AutomacaoMaquinas pai) {
    super(id,tipo,pai);
    produtos_produzidos = 0;
    produtos_produzidos_total = 0;
    todasAsMaquinasProducao.add(this);
    switch (tipo) {
      case 1: // Padrão
          limite = 100;
          break;
      case 2: // Tecidos
          limite = 50;
          break;
      case 3: // Alimentos
          limite = 200;
          break;
      case 4: // Veículos
          limite = 30;
          break;
      case 5: // Móveis
          limite = 80;
          break;
      case 6: // Eletrônicos
          limite = 150;
          break;
      case 7: // Brinquedos
          limite = 120;
          break;
    }
  }

  public void produzir() {
    if (ligada) {
      this.emFuncionamento = true;
      System.out.println("\nMáquina de produção com ID: " + id + ", está produzindo.");
      iniciarThread();
    } else {
      System.out.println("\nA máquina de produção com ID: " + id + ", está desligada. Não é possível produzir.");
    }
  }

  public void produzirProduto() {
    if (emFuncionamento) {
        // Acessando a lista produtos através de uma instância específica de Maquina
        if (Maquina.produtos != null) {
            if (produtos_produzidos < limite){
              Maquina.produtos.add(new Produtos(this.tipo));
              System.out.println("\nLOG MAQUINA:\nTIPO DO PRODUTO: " + this.tipo + "\nID FABRICA: " + this.fabrica_pai.id + "\nID: " + this.id + "\nMENSAGEM: Produto de tipo: " + this.tipo + " produzido com sucesso.\n");
              fabrica_pai.atualizarProdutosProduzidos(1);
              produtos_produzidos++;
            }else{
              System.out.println("\nLOG MAQUINA:\nTIPO DO PRODUTO: " + this.tipo + "\nID FABRICA: " + this.fabrica_pai.id + "\nID: " + this.id + "\nMENSAGEM: Fila de Produção cheia!\nnão é possivel adicionar mais produtos.\n");
            }
        }
    }
  }

  public static ArrayList<MaquinaProducao> getTodasAsMaquinasProducao() {
    return todasAsMaquinasProducao;
  }

  @Override
  public void iniciarThread(){
    if (thread == null || !thread.isAlive()) {
      thread = new Thread(new Runnable() {
        public void run() {
          // Lógica de execução da máquina
          while (ligada && emFuncionamento) {
            try {
              switch(tipo){
                case 1: Thread.sleep(20000); break; // Padrão
                case 2: Thread.sleep(25000); break; // Tecidos
                case 3: Thread.sleep(30000); break; // Alimentos
                case 4: Thread.sleep(40000); break; // Veículos
                case 5: Thread.sleep(35000); break; // Móveis
                case 6: Thread.sleep(45000); break; // Eletrônicos
                case 7: Thread.sleep(25000); break; // Brinquedos
              }
              produzirProduto();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      });
      thread.start();
    }
  }
}

// Máquina de embalagem
class MaquinaEmbalagem extends Maquina {
  int produtos_embalados;
  public MaquinaEmbalagem(int id, int tipo, AutomacaoMaquinas pai) {
    super(id,tipo,pai);
    produtos_embalados = 0;
  }

  public void embalar() {
    if(ligada) {
      this.emFuncionamento = true;
      System.out.println("\nMáquina de embalagem com ID: " + id + ", está embalando.");
      iniciarThread();
    } else {
      System.out.println("\nA máquina de embalagem com ID: " + id + ", está desligada. Não é possível embalar.");
    }
  }

  public void embalarProduto() {
    if (emFuncionamento && Maquina.produtos != null) {
        for (int i = 0; i < Maquina.produtos.size(); i++) {
            Produtos produto_atual = Maquina.produtos.get(i);
            if (produto_atual.tipo == tipo && !produto_atual.embalado && produto_atual.inspecionado) {
                produto_atual.embalado = true;
                System.out.println("\nLOG MAQUINA:\nTIPO DO PRODUTO: " + this.tipo + "\nID FABRICA: " + this.fabrica_pai.id + "\nID: " + this.id + "\nMENSAGEM: Produto de tipo: " + this.tipo + " embalado e enviado com sucesso!\n");
                fabrica_pai.atualizarProdutosEmbalados(1);
                Maquina.produtos_entregando.add(produto_atual);
                Maquina.produtos.remove(i);
                break; // Importante para sair do loop após embalar um produto
            }
        }
    }
  }

  @Override
  public void iniciarThread(){
    if (thread == null || !thread.isAlive()) {
      thread = new Thread(new Runnable() {
        public void run() {
          // Lógica de execução da máquina
          while (ligada && emFuncionamento) {
            try {
              switch(tipo){
                case 1: Thread.sleep(10000); break; // Padrão
                case 2: Thread.sleep(15000); break; // Tecidos
                case 3: Thread.sleep(12000); break; // Alimentos
                case 4: Thread.sleep(20000); break; // Veículos
                case 5: Thread.sleep(18000); break; // Móveis
                case 6: Thread.sleep(25000); break; // Eletrônicos
                case 7: Thread.sleep(12000); break; // Brinquedos
              }
              embalarProduto();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      });
      thread.start();
    }
  }
}

// Máquina de inspeção
class MaquinaInspecao extends Maquina {
  int produtos_inspecionados;
  public MaquinaInspecao(int id, int tipo, AutomacaoMaquinas pai) {
    super(id,tipo,pai);
    produtos_inspecionados = 0;
  }

  public void inspecionar() {
    if(ligada) {
      this.emFuncionamento = true;
      System.out.println("\nMáquina de inspeção com ID: " + id + ", está inspecionando.");
      iniciarThread();
    }else{
      System.out.println("\nA máquina de inspeção com ID: " + id + ", está desligada. Não é possível inspecionar.");
    }
  }

  public void inspecionarProduto() {
    if (emFuncionamento && Maquina.produtos != null) {
        for (int i = 0; i < Maquina.produtos.size(); i++) {
            Produtos produto_atual = Maquina.produtos.get(i);
            if (produto_atual.tipo == tipo && !produto_atual.inspecionado && !produto_atual.temProblema) {
                produto_atual.inspecionado = true;
                System.out.println("\nLOG MAQUINA:\nTIPO DO PRODUTO: " + this.tipo + "\nID FABRICA: " + this.fabrica_pai.id + "\nID: " + this.id + "\nMENSAGEM: Produto de tipo: " + this.tipo + " foi inspecionado, e não apresenta problemas!\n");
                fabrica_pai.atualizarProdutosInspecionados(1);
                break;
            } else if (produto_atual.tipo == tipo && produto_atual.temProblema) {
                System.out.println("\nLOG MAQUINA:\nTIPO DO PRODUTO: " + this.tipo + "\nID FABRICA: " + this.fabrica_pai.id + "\nID: " + this.id + "\nMENSAGEM: Produto de tipo: " + this.tipo + " foi inspecionado, e apresenta problemas!\nproduto descartado!\n");
                Maquina.produtos.remove(i);
                break;
            }
            for (MaquinaProducao maquinaProducao : MaquinaProducao.getTodasAsMaquinasProducao()) {
              if (maquinaProducao.tipo == tipo && ((maquinaProducao.produtos_produzidos == maquinaProducao.limite) && (maquinaProducao.produtos_produzidos > 0))) {
                  maquinaProducao.produtos_produzidos--;
              }
          }
        }
    }
  }

  @Override
  public void iniciarThread(){
    if (thread == null || !thread.isAlive()) {
      thread = new Thread(new Runnable() {
        public void run() {
          // Lógica de execução da máquina
          while (ligada && emFuncionamento) {
            try {
              switch(tipo){
                case 1: Thread.sleep(8000); break; // Padrão
                case 2: Thread.sleep(10000); break; // Tecidos
                case 3: Thread.sleep(7000); break; // Alimentos
                case 4: Thread.sleep(15000); break; // Veículos
                case 5: Thread.sleep(12000); break; // Móveis
                case 6: Thread.sleep(20000); break; // Eletrônicos
                case 7: Thread.sleep(10000); break; // Brinquedos
              }
              inspecionarProduto();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      });
      thread.start();
    }
  }
}

// Máquina de Entregas
class MaquinaEntrega extends Maquina {
  int produtos_entregados;
  public MaquinaEntrega(int id, int tipo, AutomacaoMaquinas pai) {
    super(id,tipo,pai);
    produtos_entregados = 0;
  }

  public void entregar() {
    if(ligada) {
      this.emFuncionamento = true;
      System.out.println("\nMáquina de entrega com ID: " + id + ", está entregando.");
      iniciarThread();
    }else{
      System.out.println("\nA máquina de entrega com ID: " + id + ", está desligada. Não é possível entregar.");
    }
  }

  public void entregarProduto() {
    if (emFuncionamento && Maquina.produtos_entregando != null) {
      for (int i = 0; i < Maquina.produtos_entregando.size(); i++) {
          Produtos produto_atual = Maquina.produtos_entregando.get(i);
          if (produto_atual.tipo == tipo) {
              System.out.println("\nLOG MAQUINA:\nTIPO DO PRODUTO: " + this.tipo + "\nID FABRICA: " + this.fabrica_pai.id + "\nID: " + this.id + "\nMENSAGEM: Produto de tipo: " + this.tipo + " foi entregue com sucesso!");
              fabrica_pai.atualizarProdutosEntregues(1);
              Maquina.produtos_entregando.remove(i);
          }
      }
      System.out.println("");
    }
  }

  public void iniciarThread(){
    if (thread == null || !thread.isAlive()) {
      thread = new Thread(new Runnable() {
        public void run() {
          // Lógica de execução da máquina
          while (ligada && emFuncionamento) {
            try {
              Thread.sleep(10000);
              entregarProduto();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      });
      thread.start();
    }
  }
}
