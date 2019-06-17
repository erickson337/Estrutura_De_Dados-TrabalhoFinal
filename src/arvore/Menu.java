package arvore;

import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author erick
 */
public class Menu {

    public static void main(String[] args) {
        menu();

    }

    public static void menu() {
        int opcao = -1, opcao2;
        Arvore arvore = new Arvore();
        String telefone;
        do {
            try {
                opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "Escolha uma das opções abaixo:\n1) Inserir pedido.\n2) Alterar pedido."
                        + "\n3) Excluir pedido.\n4) Consultar todos os pedidos.\n5) Consultas específicas.\n6) Sair."));

                switch (opcao) {
                    case 1:
                        arvore.inserir(inserir());
                        break;
                    case 2:
                        if (arvore.eVazia()){
                            JOptionPane.showMessageDialog(null, "Erro ao realizar a alteração, insira algo na Árvore!", "Árvore vazia", JOptionPane.ERROR_MESSAGE);
                        } else {
                            telefone = JOptionPane.showInputDialog(null, "Digite o numero do telefone: ");
                            Item cliente_pesquisa = arvore.alterarDadosCliente(telefone, null);
                            if (cliente_pesquisa != null) {
                                String dados_alterados[] = alterarDados(cliente_pesquisa);
                                arvore.alterarDadosCliente(telefone, dados_alterados);
                                JOptionPane.showMessageDialog(null, "Pedido alterado com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Alteração não efetuada, cliente não encontrado", "Não encontrado", JOptionPane.ERROR_MESSAGE );
                            }
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
                            JOptionPane.showMessageDialog(null, todosDados(arvore), "Exibir a Árvore", JOptionPane.INFORMATION_MESSAGE);
                        }
                        break;
                    case 5:
                        if (arvore.eVazia()) {
                            JOptionPane.showMessageDialog(null, "Erro ao realizar a consulta, insira algo na árvore!", "Árvore vazia", JOptionPane.ERROR_MESSAGE);
                        } else {
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
                        }
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, "Obrigado por utilizar nosso sistema!\n\nSistema desenvolvido por:\n"
                                + "Adolfo Gotler\nAnderson Oliveira\nErickson Ferreira\nMateus Pitangui\nMarcelo Faria\n", "Encerrando o sistema", JOptionPane.PLAIN_MESSAGE);
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Insira uma opção válida!", "Opção inválida", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Insira apenas números!", "Opção inválida", JOptionPane.ERROR_MESSAGE );
            }
        } while (opcao != 6);
    }

    public static String todosDados (Arvore arvore) {
        Item [] lista_arvore = arvore.CamPreFixado();
        String msg = "";
        for (int i = 0; i < arvore.getQuantNos(); i++) {
            msg += lista_arvore[i] + "\n\n";
        }
        return msg;
    }
    
    public static String[] cadastro() {
        String nome, endereco, pedido = "", telefone;
        int pedido_escolha = 0;
        String acais[] = {"Açai com complementos", "Açai sem complementos"};
        String regex = "^[0-9]{9}$";
        try{
            nome = JOptionPane.showInputDialog(null, "Informe nome do cliente: ");
            endereco = JOptionPane.showInputDialog(null, "Informe o endereço do cliente: ");
            while (pedido_escolha != 1 && pedido_escolha != 2) {
                try{
                    pedido_escolha = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o tipo de açai que deseja:\n1) " + acais[0] + "\n2) " + acais[1]));
                    if (pedido_escolha == 1) {
                        pedido = acais[0];
                    } else if (pedido_escolha == 2) {
                        pedido = acais[1];
                    } else {
                        JOptionPane.showMessageDialog(null, "Insira uma opção válida!", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Insira apenas números!", "Valor inválido!", JOptionPane.ERROR_MESSAGE);
                }
            }
            telefone = JOptionPane.showInputDialog(null, "Informe seu telefone de contato (9 dígitos): ");
            
            while (!telefone.matches(regex)) {
                telefone = JOptionPane.showInputDialog(null, "Informe um telefone de contato válido: ");
            }

            String dados [] = { nome, endereco, pedido, telefone };
            return dados;
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar, verifique todos os campos e tente novamente!", "Erro de cadastro!", JOptionPane.ERROR_MESSAGE);
            menu();
        }
        return null;
    }
    
    public static String[] alterarDados(Item item) {
        String nome, endereco, pedido = "", telefone;
        int pedido_escolha = 0, alterar_pedido = 1;
        String acais[] = {"Açai com complementos", "Açai sem complementos"};
        String regex = "^[0-9]{9}$";
        
        try {
            nome = JOptionPane.showInputDialog(null, "Deseja alterar o nome ?", item.getNome());            
            endereco = JOptionPane.showInputDialog(null, "Deseja alterar o endereço? ", item.getEndereco());

            alterar_pedido = JOptionPane.showConfirmDialog(null, "Deseja alterar o pedido ?");
            if (alterar_pedido == 0) {
                while (pedido_escolha != 1 && pedido_escolha != 2) {
                    try {
                        pedido_escolha = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o tipo de açai que deseja:\n1) " + acais[0] + "\n2) " + acais[1]));
                        if (pedido_escolha == 1) {
                            pedido = acais[0];
                        } else if (pedido_escolha == 2) {
                            pedido = acais[1];
                        } else {
                            JOptionPane.showMessageDialog(null, "Insira uma opção válida!", "Opção inválida!", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Insira apenas números!", "Valor inválido!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            telefone = JOptionPane.showInputDialog(null, "Alterar telefone de contato? ", item.getTelefone());

            while (!telefone.matches(regex)) {
                telefone = JOptionPane.showInputDialog(null, "Informe um telefone de contato válido: ");
            }

            String dados [] = { nome, endereco, pedido, telefone };
            return dados;
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar a alteração, verifique todos os campos e tente novamente!", "Erro na alteração", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    
    public static Item inserir() {
        Arvore arvore = new Arvore();
        int pedido_id = (int) ((Math.random() * 100))  +  (int)((Math.random() * 100) % arvore.getQuantNos());
        String dados_cadastrados[] = cadastro();
        Item item = new Item(dados_cadastrados[0], dados_cadastrados[1], dados_cadastrados[2], dados_cadastrados[3], pedido_id);
        JOptionPane.showMessageDialog(null, "Pedido efetuado com sucesso!", "Pedido cadastrado", JOptionPane.INFORMATION_MESSAGE);

        return item;
    }

    public static void pesquisarTelefone(Arvore arvore) {
        String telefone = JOptionPane.showInputDialog(null, "Digite o número de telefone do usuário: ");
        Item item = arvore.pesquisarTelefone(telefone);
        String info = "";
        if (arvore.eVazia()) {
            JOptionPane.showMessageDialog(null, "Erro ao realizar a consulta, insira algo na árvore!", "Árvore vazia", JOptionPane.ERROR_MESSAGE);
        } else {
            if (item == null) {
                JOptionPane.showMessageDialog(null, "Nao encontrado");
            } else {
                JOptionPane.showMessageDialog(null, "Informacoes\n" + item);
            }
        }
    }

    public static void pesquisarEndereco(Arvore arvore) {
        String endereco = JOptionPane.showInputDialog(null, "Digite o número de Endereco do cliente: ");
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
