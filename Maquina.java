import java.lang.Thread;

// Classe base para todas as máquinas
class Maquina extends Thread {
  protected int id;
  protected int tipo;
  protected boolean ligada;
  protected boolean emFuncionamento;
  protected Thread thread;

  public Maquina(int id, int tipo) {
    this.id = id;
    this.tipo = tipo;
    this.ligada = false;
    this.emFuncionamento = false;
  }

  public void iniciarThread(){
    if (thread == null || !thread.isAlive()) {
      thread = new Thread(new Runnable() {
        public void run() {
          // Lógica de execução da máquina
          while (ligada && emFuncionamento) {
            
          }
        }
      });
      thread.start();
    }
  }

  public void pararThread(){
    if (thread != null && thread.isAlive()) {
      emFuncionamento = false;
      try {
        thread.join(); // Aguarda a thread terminar
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  } 

  public void ligar() {
    this.ligada = true;
    System.out.println("Máquina de ID: " + id + ", ligada.");
  }

  public void desligar() {
    this.ligada = false;
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

  public MaquinaProducao(int id, int tipo) {
    super(id,tipo);
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
                case 0: Thread.sleep(20000); break; // Padrão
                case 1: Thread.sleep(25000); break; // Tecidos
                case 2: Thread.sleep(30000); break; // Alimentos
                case 3: Thread.sleep(40000); break; // Veículos
                case 4: Thread.sleep(35000); break; // Móveis
                case 5: Thread.sleep(45000); break; // Eletrônicos
                case 6: Thread.sleep(25000); break; // Brinquedos
              }
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      });
      thread.start();
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

  public void produzirProduto(){
    if (emFuncionamento) {

    }
  }
}

// Máquina de embalagem
class MaquinaEmbalagem extends Maquina {

  public MaquinaEmbalagem(int id, int tipo) {
    super(id,tipo);
  }

  @Override
  public void iniciarThread(){
    if (thread == null || !thread.isAlive()) {
      thread = new Thread(new Runnable() {
        public void run() {
          // Lógica de execução da máquina
          while (ligada && emFuncionamento) {
            
          }
        }
      });
      thread.start();
    }
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
}

// Máquina de inspeção
class MaquinaInspecao extends Maquina {
  public MaquinaInspecao(int id, int tipo) {
    super(id,tipo);
  }
  @Override
  public void iniciarThread(){
    if (thread == null || !thread.isAlive()) {
      thread = new Thread(new Runnable() {
        public void run() {
          // Lógica de execução da máquina
          while (ligada && emFuncionamento) {
            
          }
        }
      });
      thread.start();
    }
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
}
