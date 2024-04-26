import java.util.ArrayList;

class Fabrica {
  private ArrayList<AutomacaoMaquinas> fabricas = new ArrayList<AutomacaoMaquinas>();

  public AutomacaoMaquinas PegarAutomacao(int id){
    return this.fabricas.get(id);
  }

  public ArrayList<AutomacaoMaquinas> PegarAutomacoes(){
    return this.fabricas;
  }

  public int getTotalFabricas(){
    return this.fabricas.size();
  }

  public void addAutomatizador(int tipo_fabrica){
    AutomacaoMaquinas novo = new AutomacaoMaquinas();
    novo.tipo = tipo_fabrica;
    this.fabricas.add(novo);
    System.out.println("Fabrica com ID: " + (this.fabricas.size()-1) + ", adicionado!");
  }
}