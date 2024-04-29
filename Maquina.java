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

  public Maquina(int id, int tipo) {
    this.id = id;
    this.tipo = tipo;
    this.ligada = false;
    this.emFuncionamento = false;
    produtos = new ArrayList<>();
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
  public MaquinaProducao(int id, int tipo) {
    super(id,tipo);
    produtos_produzidos = 0;
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
            Maquina.produtos.add(new Produtos(this.tipo));
            System.out.println("\nLOG MAQUINA:\nTIPO: " + this.tipo + "\nID: " + this.id + "\nMENSAGEM: Produto de tipo: " + this.tipo + " produzido com sucesso\n");
            produtos_produzidos++;
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
  public MaquinaEmbalagem(int id, int tipo) {
    super(id,tipo);
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
    if (emFuncionamento) {
        // Acessando a lista produtos através de uma instância específica de Maquina
        if (Maquina.produtos != null && Maquina.produtos.size() > 0) {
          int i = 0;
          Produtos produto_atual = Maquina.produtos.get(i);
          while (produto_atual.tipo != tipo || i < Maquina.produtos.size()){
            produto_atual = Maquina.produtos.get(i);
            if (produto_atual.tipo != tipo){
              i++;
            }else{
              break;
            }
          }
            if (!produto_atual.embalado && produto_atual.inspecionado) {
                produto_atual.embalado = true;
                System.out.println("\nLOG MAQUINA:\nTIPO: " + this.tipo + "\nID: " + this.id + "\nMENSAGEM: Produto de tipo: " + this.tipo + " embalado e enviado com sucesso!\n");
                produtos_embalados++;
                Maquina.produtos.remove(0);
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
  public MaquinaInspecao(int id, int tipo) {
    super(id,tipo);
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
    if (emFuncionamento) {
        // Acessando a lista produtos através de uma instância específica de Maquina
        if (Maquina.produtos != null && Maquina.produtos.size() > 0) {
            int i = 0;
            Produtos produto_atual = Maquina.produtos.get(i);
              while (produto_atual.tipo != tipo || i < Maquina.produtos.size()){
                produto_atual = Maquina.produtos.get(i);
                if (produto_atual.tipo != tipo){
                  i++;
                }else{
                  break;
                }
              }
            if (!produto_atual.inspecionado && !produto_atual.temProblema) {
                produto_atual.inspecionado = true;
                System.out.println("\nLOG MAQUINA:\nTIPO: " + this.tipo + "\nID: " + this.id + "\nMENSAGEM: Produto de tipo: " + this.tipo + " foi inspecionado, e não apresenta problemas!\n");
                produtos_inspecionados++;
              } else if (produto_atual.temProblema) {
                System.out.println("\nLOG MAQUINA:\nTIPO: " + this.tipo + "\nID: " + this.id + "\nMENSAGEM: Produto de tipo: " + this.tipo + " foi inspecionado, e apresenta problemas!\nproduto descartado!\n");
                Maquina.produtos.remove(0);
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


