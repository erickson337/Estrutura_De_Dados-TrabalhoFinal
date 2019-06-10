package arvore;

/**
 *
 * @author erick
 */
public class Arvore {

    private NoArv raiz;
    private int quantNos;

    public Arvore() {
        this.quantNos = 0;
        this.raiz = null;
    }

    public boolean eVazia() {
        return (this.raiz == null);
    }

    public NoArv getRaiz() {
        return this.raiz;
    }

    public int getQuantNos() {
        return this.quantNos;
    }

    //inserir um novo nó na arvore. Sempre insere em um atributo que seja igual a null
    public boolean inserir(Item elem) {
        if (pesquisar(elem.getPedido_id())) {
            return false;
        } else {
            this.raiz = inserir(elem, this.raiz);
            this.quantNos++;
            return true;
        }
    }

    public NoArv inserir(Item elem, NoArv no) {
        if (no == null) {
            NoArv novo = new NoArv(elem);
            return novo;
        } else {
            if (elem.getPedido_id() < no.getInfo().getPedido_id()) {
                no.setEsq(inserir(elem, no.getEsq()));
                return no;
            } else {
                no.setDir(inserir(elem, no.getDir()));
                return no;
            }
        }
    }

    //Pesquisa se um determinado valor está na árvore
    public boolean pesquisar(int chave) {
        if (pesquisar(chave, this.raiz) != null) {
            return true;
        } else {
            return false;
        }
    }

    private NoArv pesquisar(int chave, NoArv no) {
        if (no != null) {
            if (chave < no.getInfo().getPedido_id()) {
                no = pesquisar(chave, no.getEsq());

            } else {
                if (chave > no.getInfo().getPedido_id()) {
                    no = pesquisar(chave, no.getDir());
                }
            }
        }
        return no;
    }

    private NoArv Arrumar(NoArv arv, NoArv maior) {
        if (maior.getDir() != null) {
            maior.setDir(Arrumar(arv, maior.getDir()));
        } else {
            arv.setInfo(maior.getInfo());
            maior = maior.getEsq();
        }
        return maior;
    }

    //caminhamento central
    public Item[] CamCentral() {
        int[] n = new int[1];
        n[0] = 0;
        Item[] vet = new Item[this.quantNos];
        return (FazCamCentral(this.raiz, vet, n));
    }

    private Item[] FazCamCentral(NoArv arv, Item[] vet, int[] n) {
        if (arv != null) {
            vet = FazCamCentral(arv.getEsq(), vet, n);
            vet[n[0]] = arv.getInfo();
            n[0]++;
            vet = FazCamCentral(arv.getDir(), vet, n);
        }
        return vet;
    }

    public Item pesquisaTelefone(String telefone) {
        int[] n = new int[1];
        // n[0] = 0;
        Item item = null;
        return (pesquisaTelefone(this.raiz, item, telefone));
    }

    private Item pesquisaTelefone(NoArv arv, Item item, String telefone) {
        if (arv != null) {
            item = pesquisaTelefone(arv.getEsq(), item, telefone);
            if (arv.getInfo().getTelefone().equals(telefone)) {
                item = arv.getInfo();
            }
            item = pesquisaTelefone(arv.getDir(), item, telefone);
        }
        return item;
    }

    public Item[] pesquisaEndereco(String endereco) {
        int[] n = new int[1];
        n[0] = 0;
        Item[] vet = new Item[this.quantNos];
        return (pesquisaEndereco(this.raiz, vet, n, endereco));
    }

    private Item[] pesquisaEndereco(NoArv arv, Item[] vet, int[] n, String endereco) {
        if (arv != null) {
            vet = pesquisaEndereco(arv.getEsq(), vet, n, endereco);
            if (arv.getInfo().getEndereco().equals(endereco)) {
                vet[n[0]] = arv.getInfo();
                n[0]++;
            }
            vet = pesquisaEndereco(arv.getDir(), vet, n, endereco);
        }
        return vet;
    }

    //caminhamento pré-fixado
    public Item[] CamPreFixado() {
        int[] n = new int[1];
        n[0] = 0;
        Item[] vet = new Item[this.quantNos];
        return (FazCamPreFixado(this.raiz, vet, n));
    }

    private Item[] FazCamPreFixado(NoArv arv, Item[] vet, int[] n) {
        if (arv != null) {
            vet[n[0]] = arv.getInfo();
            n[0]++;
            vet = FazCamPreFixado(arv.getEsq(), vet, n);
            vet = FazCamPreFixado(arv.getDir(), vet, n);
        }
        return vet;
    }

    //caminhamento pós-fixado
    public Item[] CamPosFixado() {
        int[] n = new int[1];
        n[0] = 0;
        Item[] vet = new Item[this.quantNos];
        return (FazCamPosFixado(this.raiz, vet, n));
    }

    private Item[] FazCamPosFixado(NoArv arv, Item[] vet, int[] n) {
        if (arv != null) {
            vet = FazCamPosFixado(arv.getEsq(), vet, n);
            vet = FazCamPosFixado(arv.getDir(), vet, n);
            vet[n[0]] = arv.getInfo();
            n[0]++;
        }
        return vet;
    }

    private NoArv pesquisarRemover(int chave, NoArv no, String telefone) {
        if (no != null) {
            if (chave < no.getInfo().getPedido_id()) {
                if (no.getInfo().getTelefone().equals(telefone)) {
                    return no;
                }
                no = pesquisar(chave, no.getEsq());
            } else {
                if (chave > no.getInfo().getPedido_id()) {
                    no = pesquisar(chave, no.getDir());
                }
            }
        }
        return no;
    }

    public boolean remover(String telefone) {
        int pedido_id = this.raiz.getInfo().getPedido_id();
        if (pesquisarRemover(pedido_id, this.raiz, telefone) != null) {
            this.raiz = remover(pedido_id, this.raiz);
            this.quantNos--;
            return true;
        } else {
            return false;
        }
    }

    private NoArv remover(int pedido_id, NoArv arv) {
        if (pedido_id < arv.getInfo().getPedido_id()) {
            arv.setEsq(remover(pedido_id, arv.getEsq()));
        } else {
            if (arv.getDir() == null) {
                return arv.getEsq();
            } else {
                if (arv.getEsq() == null) {
                    return arv.getDir();
                } else {
                    arv.setEsq(Arrumar(arv, arv.getEsq()));
                }
            }
        }
        return arv;
    }
    
    public boolean alterarDadosCliente(String telefone, String [] dados) {
        int[] n = new int[1];
        n[0] = 0;
        Item[] vet = new Item[this.quantNos];
        return alterarDadosCliente(this.raiz, vet, n, telefone, dados);
    }
    
    private boolean alterarDadosCliente(NoArv arv, Item[] vet, int[] n, String telefone, String [] dados) {
        if (arv != null) {
            alterarDadosCliente(arv.getEsq(), vet, n, telefone, dados);
            if (arv.getInfo().getTelefone().equals(telefone)) {
                arv.getInfo().setNome(dados[0]);
                arv.getInfo().setTelefone(dados[1]);
                arv.getInfo().setEndereco(dados[2]);
                arv.getInfo().setPedido(dados[3]);
                return true;
            }
            alterarDadosCliente(arv.getDir(), vet, n, telefone, dados);
        }
       return false;
    }

}
