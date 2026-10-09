import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
    private final UUID id;
    private int numero;
    private Cliente cliente;
    private List<ItemPedido> itens;

    public Pedido(int numero, Cliente cliente) {
        this.id = UUID.randomUUID();
        this.numero = numero;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        ItemPedido item = new ItemPedido(this, produto, quantidade);
        itens.add(item);
    }

    public double getSubtotal() {
        double soma = 0;
        for (ItemPedido item : itens) {
            soma += item.getTotal();
        }
        return soma;
    }
}
