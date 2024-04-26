import java.util.ArrayList;

class AutomacaoMaquinas {
  private ArrayList<Maquina> maquinas;
  private boolean inicializada;
  public int tipo;

  public AutomacaoMaquinas() {
    this.maquinas = new ArrayList<Maquina>();
    this.inicializada = false;
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
