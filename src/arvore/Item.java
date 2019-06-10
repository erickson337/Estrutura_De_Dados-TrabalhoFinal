package arvore;

/**
 *
 * @author erick
 */
public class Item {
    private String nome, endereco, pedido, telefone; 
    private int pedido_id;

    public Item() {
    }

    public Item(String nome, String endereco, String pedido, String telefone, int pedido_id) {
        this.nome = nome;
        this.endereco = endereco;
        this.pedido = pedido;
        this.telefone = telefone;
        this.pedido_id = pedido_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Pedido número: " + pedido_id + "\nNome: " + nome + "\nEndereço: " + endereco + "\nPedido: " + pedido + "\nTelefone: " + telefone;
    }
   
}
