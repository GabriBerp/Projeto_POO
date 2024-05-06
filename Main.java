import java.util.ArrayList;
import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    Fabrica fabrica = new Fabrica();
    int numFabricas = Integer.parseInt(JOptionPane.showInputDialog("Quantas fábricas serão controladas?"));

    for (int a = 0; a < numFabricas; a++) {
      Object[] possibilities = { "Padrão", "Tecidos", "Alimentos", "Veículos", "Moveis", "Eletronicos", "Brinquedos" };
      String opFabrica = (String) JOptionPane.showInputDialog(null, "Qual o tipo da fabrica " + (a + 1) + "?",
          "Tipo de Fábrica", JOptionPane.PLAIN_MESSAGE, null, possibilities, "Padrão");
      switch (opFabrica) {
        case "Padrão":
          fabrica.addAutomatizador(1);
          break;
        case "Tecidos":
          fabrica.addAutomatizador(2);
          break;
        case "Alimentos":
          fabrica.addAutomatizador(3);
          break;
        case "Veículos":
          fabrica.addAutomatizador(4);
          break;
        case "Moveis":
          fabrica.addAutomatizador(5);
          break;
        case "Eletronicos":
          fabrica.addAutomatizador(6);
          break;
        case "Brinquedos":
          fabrica.addAutomatizador(7);
          break;
        default:
          break;
      }
    }

    AutomacaoMaquinas automacao = fabrica.PegarAutomacao(0);
    ArrayList<Maquina> maquinas = automacao.PegarMaquinas();
    int qnt_producao = 0;
    int qnt_embalagem = 0;
    int qnt_inspecao = 0;
    int qnt_entrega = 0;

    int op = 0;
    int id = 0;
    int fab_id = 0;
    int opcao = 0;
    int option = 0;

    JFrame frame = new JFrame("Menu de Fabricas");

    do {
      option = Integer.parseInt(JOptionPane.showInputDialog(frame, "== Menu de Fabricas ==\nId da fabrica selecionada: "
          + fab_id + "\n1- Iniciar\n2- Trocar Fabrica\n3- Listar Fabricas\n4- Encerrar\nEscolha uma opção (1, 2, 3 ou 4):"));

      switch (option) {
        case 1:
            automacao = fabrica.PegarAutomacao(fab_id);
            if (!automacao.foiInicializada()) {
                qnt_producao = 0;
                qnt_embalagem = 0;
                qnt_inspecao = 0;
                qnt_entrega = 0;
                // Adiciona X maquinas a um ArrayList
                String numMaquinasInput = JOptionPane.showInputDialog("== Iniciando Fabrica " + fab_id + " ==\n\nQuantas máquinas vão ser automatizadas? ");
                int numMaquinas = Integer.parseInt(numMaquinasInput);
                System.out.println(numMaquinas + " maquinas selecionadas.");
                // Loop para criar as máquinas
                for (int i = 1; i <= numMaquinas; i++) {
                    String tipoInput = JOptionPane.showInputDialog("Tipo de máquina para a máquina " + i + ":\n1. Produção\n2. Embalagem\n3. Inspeção\n4. Entrega\nEscolha o tipo (1, 2, 3 ou 4): ");
                    int tipo = Integer.parseInt(tipoInput);
                    switch (tipo) {
                    case 1:
                        automacao.adicionarMaquina(new MaquinaProducao(i,automacao.tipo,automacao));
                        qnt_producao++;
                        tipo = 0;
                        break;
                    case 2:
                        automacao.adicionarMaquina(new MaquinaEmbalagem(i,automacao.tipo,automacao));
                        qnt_embalagem++;
                        tipo = 0;
                        break;
                    case 3:
                        automacao.adicionarMaquina(new MaquinaInspecao(i,automacao.tipo,automacao));
                        qnt_inspecao++;
                        tipo = 0;
                        break;
                    case 4:
                        automacao.adicionarMaquina(new MaquinaEntrega(i,automacao.tipo,automacao));
                        qnt_entrega++;
                        tipo = 0;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Tipo inválido. Por favor, escolha novamente.");
                        i--; // Para repetir a iteração para que o usuário escolha novamente
                        tipo = 0;
                        break;
                    }
                }
                // Exibindo informações sobre as máquinas criadas
                StringBuilder maquinasInfo = new StringBuilder();
                maquinasInfo.append("\n" + numMaquinas + " Máquinas criadas:\n");
                for (Maquina maquina : maquinas) {
                    if (maquina instanceof MaquinaProducao) {
                    maquinasInfo.append("Máquina de Produção, ID: " + maquina.id + "\n");
                    } else if (maquina instanceof MaquinaEmbalagem) {
                    maquinasInfo.append("Máquina de Embalagem, ID: " + maquina.id + "\n");
                    } else if (maquina instanceof MaquinaInspecao) {
                    maquinasInfo.append("Máquina de Inspeção, ID: " + maquina.id + "\n");
                    } else if (maquina instanceof MaquinaEntrega) {
                      maquinasInfo.append("Máquina de Entrega, ID: " + maquina.id + "\n");
                      }
                }
                maquinasInfo.append("\nQuantidade de máquinas por tipo:\nProdução: " + qnt_producao + "\nEmbalagem: "
                    + qnt_embalagem + "\nInspeção: " + qnt_inspecao + "\nEntrega: " + qnt_entrega);
                JOptionPane.showMessageDialog(null, maquinasInfo.toString());
                automacao.inicializar();
            }
            do {
              maquinas = automacao.PegarMaquinas();
              switch (automacao.tipo) {
                  case 1:
                  op = Integer.parseInt(JOptionPane.showInputDialog("\n\nMenu de Automação de Máquinas:\n===\nFabrica Padrão\nID: " + fab_id + "\n===\n1. Ligar Máquinas\n2. Desligar Máquinas\n3. Listar Máquinas\n4. Automatizar Máquinas\n5. Parar Automatização\n6. Voltar\nEscolha uma opção (1, 2, 3, 4, 5 ou 6): "));
                  break;
                  case 2:
                  op = Integer.parseInt(JOptionPane.showInputDialog("\n\nMenu de Automação de Máquinas:\n===\nFabrica de Tecidos\nID: " + fab_id + "\n===\n1. Ligar Máquinas\n2. Desligar Máquinas\n3. Listar Máquinas\n4. Automatizar Máquinas\n5. Parar Automatização\n6. Voltar\nEscolha uma opção (1, 2, 3, 4, 5 ou 6): "));
                  break;
                  case 3:
                  op = Integer.parseInt(JOptionPane.showInputDialog("\n\nMenu de Automação de Máquinas:\n===\nFabrica de Alimentos\nID: " + fab_id + "\n===\n1. Ligar Máquinas\n2. Desligar Máquinas\n3. Listar Máquinas\n4. Automatizar Máquinas\n5. Parar Automatização\n6. Voltar\nEscolha uma opção (1, 2, 3, 4, 5 ou 6): "));
                  break;
                  case 4:
                  op = Integer.parseInt(JOptionPane.showInputDialog("\n\nMenu de Automação de Máquinas:\n===\nFabrica de Veiculos\nID: " + fab_id + "\n===\n1. Ligar Máquinas\n2. Desligar Máquinas\n3. Listar Máquinas\n4. Automatizar Máquinas\n5. Parar Automatização\n6. Voltar\nEscolha uma opção (1, 2, 3, 4, 5 ou 6): "));
                  break;
                  case 5:
                  op = Integer.parseInt(JOptionPane.showInputDialog("\n\nMenu de Automação de Máquinas:\n===\nFabrica de Moveis\nID: " + fab_id + "\n===\n1. Ligar Máquinas\n2. Desligar Máquinas\n3. Listar Máquinas\n4. Automatizar Máquinas\n5. Parar Automatização\n6. Voltar\nEscolha uma opção (1, 2, 3, 4, 5 ou 6): "));
                  break;
                  case 6:
                  op = Integer.parseInt(JOptionPane.showInputDialog("\n\nMenu de Automação de Máquinas:\n===\nFabrica de Eletronicos\nID: " + fab_id + "\n===\n1. Ligar Máquinas\n2. Desligar Máquinas\n3. Listar Máquinas\n4. Automatizar Máquinas\n5. Parar Automatização\n6. Voltar\nEscolha uma opção (1, 2, 3, 4, 5 ou 6): "));
                  break;
                  case 7:
                  op = Integer.parseInt(JOptionPane.showInputDialog("\n\nMenu de Automação de Máquinas:\n===\nFabrica de Brinquedos\nID: " + fab_id + "\n===\n1. Ligar Máquinas\n2. Desligar Máquinas\n3. Listar Máquinas\n4. Automatizar Máquinas\n5. Parar Automatização\n6. Voltar\nEscolha uma opção (1, 2, 3, 4, 5 ou 6): "));
                  break;
              }
              switch (op) {
                case 1:
                    opcao = Integer.parseInt(JOptionPane.showInputDialog("Deseja ligar uma maquina especifica, ou todas? (1- Especifica, 2- Todas)"));
                    if (opcao == 1) {
                        id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da máquina que deseja ligar: "));
                        for (Maquina maquina : maquinas) {
                        if (maquina.id == id) {
                            maquina.ligar();
                            break;
                        }
                        }
                    } else if (opcao == 2) {
                        automacao.ligarTodasMaquinas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Opção inválida.");
                    }
                    break;
                case 2:
                    opcao = Integer.parseInt(JOptionPane.showInputDialog("Deseja desligar uma maquina especifica, ou todas? (1- Especifica, 2- Todas)"));
                    if (opcao == 1) {
                        id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da máquina que deseja desligar:"));
                        for (Maquina maquina : maquinas) {
                        if (maquina.id == id) {
                            maquina.desligar();
                            break;
                        }
                        }
                    } else if (opcao == 2) {
                        automacao.desligarTodasMaquinas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Opção inválida.");
                    }
                    break;
                case 3:
                    opcao = Integer.parseInt(JOptionPane.showInputDialog("Deseja listar uma maquina especifica, ou todas? (1- Especifica, 2- Todas)"));
                    if (opcao == 1) {
                        id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da máquina que deseja listar:"));
                        for (Maquina maquina : maquinas) {
                        if (maquina.id == id) {
                            String message = "";
                            if (maquina instanceof MaquinaProducao) {
                            message += "\n\nMáquina de Produção " + maquina.id;
                            } else if (maquina instanceof MaquinaEmbalagem) {
                            message += "\n\nMáquina de Embalagem " + maquina.id;
                            } else if (maquina instanceof MaquinaInspecao) {
                            message += "\n\nMáquina de Inspeção " + maquina.id;
                            } else if (maquina instanceof MaquinaEntrega) {
                              message += "Máquina de Entrega " + maquina.id + "\n";
                            }
                            if (maquina.ligada) {
                            message += "\nLigada: Sim";
                            } else {
                            message += "\nLigada: Não";
                            }
                            if (maquina.emFuncionamento) {
                            message += "\nEm Funcionamento: Sim\n";
                            } else {
                            message += "\nEm Funcionamento: Não\n";
                            }
                            JOptionPane.showMessageDialog(null, message);
                            break;
                        }
                        }
                        break;
                    } else if (opcao == 2) {
                        String message = "";
                        for (Maquina maquina : maquinas) {
                        if (maquina instanceof MaquinaProducao) {
                            message += "Máquina de Produção " + maquina.id + "\n";
                        } else if (maquina instanceof MaquinaEmbalagem) {
                            message += "Máquina de Embalagem " + maquina.id + "\n";
                        } else if (maquina instanceof MaquinaInspecao) {
                            message += "Máquina de Inspeção " + maquina.id + "\n";
                        } else if (maquina instanceof MaquinaEntrega) {
                          message += "Máquina de Entrega " + maquina.id + "\n";
                        }
                        if (maquina.ligada) {
                            message += "Ligada: Sim\n";
                        } else {
                            message += "Ligada: Não\n";
                        }
                        if (maquina.emFuncionamento) {
                            message += "Em Funcionamento: Sim\n\n";
                        } else {
                            message += "Em Funcionamento: Não\n\n";
                        }
                        }
                        JOptionPane.showMessageDialog(null, message);
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Opção inválida.");
                    }
                    break;
                case 4:
                    opcao = Integer.parseInt(JOptionPane.showInputDialog("Deseja automatizar uma maquina especifica, ou todas? (1- Especifica, 2- Todas)"));
                    if (opcao == 1) {
                      id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da máquina que deseja automatizar:"));
                      for (Maquina maquina : maquinas) {
                        if ((maquina.id == id) && (!maquina.emFuncionamento)) {
                          if (maquina instanceof MaquinaProducao) {
                            ((MaquinaProducao) maquina).produzir();
                          } else if (maquina instanceof MaquinaEmbalagem) {
                            ((MaquinaEmbalagem) maquina).embalar();
                          } else if (maquina instanceof MaquinaInspecao) {
                            ((MaquinaInspecao) maquina).inspecionar();
                          } else if (maquina instanceof MaquinaEntrega) {
                            ((MaquinaEntrega) maquina).entregar();
                          }
                          break;
                        } else if ((maquina.id == id) && (maquina.emFuncionamento)) {
                          JOptionPane.showMessageDialog(null, "A Máquina já está funcionando.\n");
                        }
                      }
                      break;
                    } else if (opcao == 2) {
                      for (Maquina maquina : maquinas) {
                        if (!maquina.emFuncionamento) {
                          if (maquina instanceof MaquinaProducao) {
                            ((MaquinaProducao) maquina).produzir();
                          } else if (maquina instanceof MaquinaEmbalagem) {
                            ((MaquinaEmbalagem) maquina).embalar();
                          } else if (maquina instanceof MaquinaInspecao) {
                            ((MaquinaInspecao) maquina).inspecionar();
                          } else if (maquina instanceof MaquinaEntrega) {
                            ((MaquinaEntrega) maquina).entregar();
                          }
                        }
                      }
                      break;
                    } else {
                      JOptionPane.showMessageDialog(null, "Opção inválida.");
                    }
                    break;
                case 5:
                    opcao = Integer.parseInt(JOptionPane.showInputDialog("Deseja parar uma maquina especifica, ou todas? (1- Especifica, 2- Todas)"));
                    if (opcao == 1) {
                      id = Integer.parseInt(JOptionPane.showInputDialog("Digite o id da máquina que deseja parar:"));
                      for (Maquina maquina : maquinas) {
                        if (maquina.id == id) {
                          maquina.paraAutomatizar();
                          break;
                        }
                      }
                    } else if (opcao == 2) {
                      for (Maquina maquina : maquinas) {
                        maquina.paraAutomatizar();
                      }
                    } else {
                      JOptionPane.showMessageDialog(null, "Opção inválida.");
                    }
                    break;
              }
            }while(op!=6);
            break;
        case 2:
            if (fabrica.getTotalFabricas() > 1) {
              String idInput = JOptionPane.showInputDialog("Digite o ID da Fabrica que quer selecionar: ");
              opcao = Integer.parseInt(idInput);
              if ((opcao >= 0) && (opcao <= fabrica.getTotalFabricas() - 1)) {
                fab_id = opcao;
                JOptionPane.showMessageDialog(null, "Selecionou fabrica de ID: " + opcao);
              } else {
                JOptionPane.showMessageDialog(null, "ID inválido.");
              }
            } else {
              JOptionPane.showMessageDialog(null, "Você precisa ter mais de 1 fabrica para isso.");
            }
            break;
        case 3:
            int i = 0;
            StringBuilder fabricasInfo = new StringBuilder();
            for (AutomacaoMaquinas fabricas : fabrica.PegarAutomacoes()) {
              fabricasInfo.append("-> Fabrica ").append(i + 1).append(":\n> ID: ").append(i).append("\n");
              switch (fabricas.tipo) {
                case 1:
                  fabricasInfo.append("> Tipo: Padrão\n");
                  break;
                case 2:
                  fabricasInfo.append("> Tipo: Tecidos\n");
                  break;
                case 3:
                  fabricasInfo.append("> Tipo: Alimentos\n");
                  break;
                case 4:
                  fabricasInfo.append("> Tipo: Veiculos\n");
                  break;
              }
              ArrayList<Maquina> maquinas_p = fabricas.PegarMaquinas();
              if (maquinas_p.size() > 0) {
                fabricasInfo.append("-> Maquinas:\n");
                for (Maquina maquina : maquinas_p) {
                  if (maquina instanceof MaquinaProducao) {
                    fabricasInfo.append("  Máquina de Produção ").append(maquina.id).append("\n");
                  } else if (maquina instanceof MaquinaEmbalagem) {
                    fabricasInfo.append("  Máquina de Embalagem ").append(maquina.id).append("\n");
                  } else if (maquina instanceof MaquinaInspecao) {
                    fabricasInfo.append("  Máquina de Inspeção ").append(maquina.id).append("\n");
                  } else if (maquina instanceof MaquinaEntrega) {
                    fabricasInfo.append("  Máquina de Entrega ").append(maquina.id).append("\n");
                  }
                  fabricasInfo.append(maquina.ligada ? "  Ligada: Sim\n" : "  Ligada: Não\n");
                  fabricasInfo.append(maquina.emFuncionamento ? "  Em Funcionamento: Sim\n\n" : "  Em Funcionamento: Não\n\n");
                }
              } else {
                fabricasInfo.append("--> A Fabrica não foi inicializada.\n\n");
              }
              fabricasInfo.append("\n");
              i++;
            }
            JOptionPane.showMessageDialog(null, fabricasInfo.toString());
            break;
        }
    } while (option != 4);

    JOptionPane.showMessageDialog(null, "Encerrando o programa...");

  }
}
