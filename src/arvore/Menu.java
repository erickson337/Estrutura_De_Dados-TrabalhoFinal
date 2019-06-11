package arvore;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author erick
 */
public class Menu {

    public static void main(String[] args) {
        menu();

    }

    public static void menu() {
        int opcao, opcao2;
        Arvore arvore = new Arvore();
        Item[] lista_arvore = new Item[10];
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma das opções abaixo:\n1) Inserir pedido.\n2) Alterar pedido."
                    + "\n3) Excluir pedido.\n4) Consultar todos os pedidos.\n5) Consultas específicas.\n6) Sair."));

            switch (opcao) {
                case 1:
                    arvore.inserir(inserir(arvore.getQuantNos() + 1));
                    break;
                case 2:
                    String telefone = JOptionPane.showInputDialog(null, "Digite o numero do telefone: ");
                    String nome = JOptionPane.showInputDialog(null, "Digite um novo nome: ");
                    String endereco = JOptionPane.showInputDialog(null, "Digite um novo endereco: ");
                    String novoTelefone = JOptionPane.showInputDialog(null, "Digite um novo número de telefone: ");
                    String pedido = JOptionPane.showInputDialog(null, "Digite um novo pedido: ");
                    String dados[] = { nome, novoTelefone, endereco, pedido };
                    if (arvore.alterarDadosCliente(telefone, dados)) {
                        JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Alteração não efetuada, cliente não encontrado", "Não encontrado", JOptionPane.ERROR_MESSAGE );
                    }
                    break;
                case 3:
                    if (arvore.eVazia()) {
                        JOptionPane.showMessageDialog(null, "Erro ao realizar a consulta, insira algo na Árvore!", "Árvore vazia", JOptionPane.ERROR_MESSAGE);
                    } else {
                        telefone = JOptionPane.showInputDialog(null, "Digite o telefone para remover o cliente");
                        if (arvore.removerClienteTelefone(telefone)) {
                            JOptionPane.showMessageDialog(null, "Cliente excluído sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Remoção não efetuada, cliente não encontrado", "Não encontrado", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case 4:
                    if (arvore.eVazia()) {
                        JOptionPane.showMessageDialog(null, "Erro ao realizar a consulta, insira algo na árvore!", "Árvore vazia", JOptionPane.ERROR_MESSAGE);
                    } else {
                        lista_arvore = arvore.CamCentral();
                        String msg = "";
                        for (int i = 0; i < arvore.getQuantNos(); i++) {
                            msg += lista_arvore[i] + "\n\n";
                        }
                        JOptionPane.showMessageDialog(null, msg, "Exibir a Árvore", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                case 5:
                    opcao2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma das pesquisa abaixo:\n1) Pesquisar por telefone.\n2) Pesquisar por endereço."
                            + "\n3) Voltar ao menu."));

                    switch (opcao2) {
                        case 1:
                            pesquisarTelefone(arvore);
                            break;
                        case 2:
                            pesquisarEndereco(arvore);
                            break;
                        case 3:
                            menu();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Insira uma opção válida!", "Opção inválida", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema!\n\nSistema desenvolvido por:\n"
                            + "Adolfo Gotler\nAnderson Oliveira\nErickson Ferreira\nMateus Pitangui\nMarcelo Farias\n", "Encerrando o sistema", JOptionPane.PLAIN_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Insira uma opção válida!", "Opção inválida", JOptionPane.ERROR_MESSAGE);
            }
        } while (opcao != 6);
    }

    public static Item inserir(int pedido_id) {
        String nome = "", endereco = "", pedido = "", telefone = "";
        int pedido_escolha = 0;
        String acais[] = {"Açai com complementos", "Açai sem complementos"};

        nome = JOptionPane.showInputDialog(null, "Informe seu nome: ");
        endereco = JOptionPane.showInputDialog(null, "Informe seu endereço: ");

        while (pedido_escolha != 1 && pedido_escolha != 2) {
            pedido_escolha = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o tipo de açai que deseja:\n1) " + acais[0] + "\n2) " + acais[1]));
            if (pedido_escolha == 1) {
                pedido = acais[0];
            } else if (pedido_escolha == 2) {
                pedido = acais[1];
            } else {
                JOptionPane.showMessageDialog(null, "Insira uma opção válida!", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
            }
        }

        telefone = JOptionPane.showInputDialog(null, "Informe seu telefone de contato: ");
        Item item = new Item(nome, endereco, pedido, telefone, pedido_id);
        JOptionPane.showMessageDialog(null, "Pedido efetuado com sucesso!", "Pedido cadastrado", JOptionPane.INFORMATION_MESSAGE);

        return item;
    }

    public static void pesquisarTelefone(Arvore arvore) {
        String telefone = JOptionPane.showInputDialog(null, "Digite o Numero de telefone do usuario: ");
        Item item = arvore.pesquisarTelefone(telefone);
        String info = "";
        if (arvore.eVazia()) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar a consulta, insira algo na árvore!", "ÿrvore vazia", JOptionPane.ERROR_MESSAGE);
        } else {
            if (item == null) {
                JOptionPane.showMessageDialog(null, "Nao encontrado");
            } else {
                JOptionPane.showMessageDialog(null, "Informacoes\n" + item);
            }
        }
    }

    public static void pesquisarEndereco(Arvore arvore) {
        String endereco = JOptionPane.showInputDialog(null, "Digite o Numero de Endereco do usuario: ");
        Item[] lista_arvore = arvore.pesquisarEndereco(endereco);
        String info = "";
        if (arvore.eVazia()) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar a consulta, insira algo na árvore!", "Árvore vazia", JOptionPane.ERROR_MESSAGE);
        } else {
            for (Item item : lista_arvore) {
                if (item != null) {
                    info += item + "\n\n";
                }
            }
            if (info.equals("")) {
                JOptionPane.showMessageDialog(null, "Nao encontrado");
            } else {
                JOptionPane.showMessageDialog(null, "Informacoes\n" + info);
            }
        }
    }
}
