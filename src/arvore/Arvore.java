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

    public Item pesquisarTelefone(String telefone) {
        Item item = null;
        return (pesquisarTelefone(this.raiz, item, telefone));
    }

    private Item pesquisarTelefone(NoArv arv, Item item, String telefone) {
        if (arv != null) {
            item = pesquisarTelefone(arv.getEsq(), item, telefone);
            if (arv.getInfo().getTelefone().equals(telefone)) {
                item = arv.getInfo();
            }
            item = pesquisarTelefone(arv.getDir(), item, telefone);
        }
        return item;
    }

    public Item[] pesquisarEndereco(String endereco) {
        int[] n = new int[1];
        n[0] = 0;
        Item[] vet = new Item[this.quantNos];
        return (pesquisarEndereco(this.raiz, vet, n, endereco));
    }

    private Item[] pesquisarEndereco(NoArv arv, Item[] vet, int[] n, String endereco) {
        if (arv != null) {
            vet = pesquisarEndereco(arv.getEsq(), vet, n, endereco);
            if (arv.getInfo().getEndereco().equals(endereco)) {
                vet[n[0]] = arv.getInfo();
                n[0]++;
            }
            vet = pesquisarEndereco(arv.getDir(), vet, n, endereco);
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
    public Item[] camPosFixado() {
        int[] n = new int[1];
        n[0] = 0;
        Item[] vet = new Item[this.quantNos];
        return (fazCamPosFixado(this.raiz, vet, n));
    }

    private Item[] fazCamPosFixado(NoArv arv, Item[] vet, int[] n) {
        if (arv != null) {
            vet = fazCamPosFixado(arv.getEsq(), vet, n);
            vet = fazCamPosFixado(arv.getDir(), vet, n);
            vet[n[0]] = arv.getInfo();
            n[0]++;
        }
        return vet;
    }

    public boolean removerClienteTelefone(String telefone) {
        Item item = pesquisarTelefone(telefone);
        if (item != null) {
            this.raiz = removerCliente(item.getPedido_id(), this.raiz);
            this.quantNos--;
            return true;
        } else {
            return false;
        }
    }

    public NoArv removerCliente(int chave, NoArv arv) {
        if (chave < arv.getInfo().getPedido_id()) {
            arv.setEsq(removerCliente(chave, arv.getEsq()));
        } else {
            if (chave > arv.getInfo().getPedido_id()) {
                arv.setDir(removerCliente(chave, arv.getDir()));
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
        }
        return arv;
    }

    public Item alterarDadosCliente(String telefone, String[] dados) {
        Item item = null;
        Item pesquisaTelefone = alterarDadosCliente(this.raiz, item, telefone);
        
        if (pesquisaTelefone != null) {
            if (dados != null) {
                pesquisaTelefone.setNome(dados[0]);
                pesquisaTelefone.setEndereco(dados[1]);
                pesquisaTelefone.setPedido(dados[2]);
                pesquisaTelefone.setTelefone(dados[3]);
            }
            return pesquisaTelefone;
        }
        return null;
    }

    private Item alterarDadosCliente(NoArv arv, Item item, String telefone) {
        if (arv != null) {
            item = pesquisarTelefone(arv.getEsq(), item, telefone);
            if (arv.getInfo().getTelefone().equals(telefone)) {
                item = arv.getInfo();
            }
            item = pesquisarTelefone(arv.getDir(), item, telefone);
        }
        return item;
    }

}
