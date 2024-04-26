import java.lang.Thread;

// Classe base para todas as máquinas
class Maquina extends Thread {
  protected int id;
  protected boolean ligada;
  protected boolean emFuncionamento;

  public Maquina(int id) {
    this.id = id;
    this.ligada = false;
    this.emFuncionamento = false;
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
    }else{
      System.out.println("A Máquina não está trabalhando.");
    }
  }
}

// Máquina de produção
class MaquinaProducao extends Maquina {
  public MaquinaProducao(int id) {
    super(id);
  }

  public void produzir() {
    if (ligada) {
      this.emFuncionamento = true;
      System.out.println("\nMáquina de produção com ID: " + id + ", está produzindo.");
    } else {
      System.out.println("\nA máquina de produção com ID: " + id + ", está desligada. Não é possível produzir.");
    }
  }
}

// Máquina de embalagem
class MaquinaEmbalagem extends Maquina {
  public MaquinaEmbalagem(int id) {
    super(id);
  }

  public void embalar() {
    if(ligada) {
      this.emFuncionamento = true;
      System.out.println("\nMáquina de embalagem com ID: " + id + ", está embalando.");
    } else {
      System.out.println("\nA máquina de embalagem com ID: " + id + ", está desligada. Não é possível embalar.");
    }
  }
}

// Máquina de inspeção
class MaquinaInspecao extends Maquina {
  public MaquinaInspecao(int id) {
    super(id);
  }

  public void inspecionar() {
    if(ligada) {
      this.emFuncionamento = true;
      System.out.println("\nMáquina de inspeção com ID: " + id + ", está inspecionando.");
    }else{
      System.out.println("\nA máquina de inspeção com ID: " + id + ", está desligada. Não é possível inspecionar.");
    }
  }
}