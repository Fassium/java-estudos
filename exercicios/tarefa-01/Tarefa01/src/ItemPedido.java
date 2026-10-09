import java.util.UUID;

public class ItemPedido {
    private final UUID id;
    private Pedido pedido;
    private Produto produto;
    private int quantidade;

    public ItemPedido(Pedido pedido, Produto produto, int quantidade) {
        this.id = UUID.randomUUID();
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public UUID getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getTotal() {
        return produto.getPreco() * quantidade;
    }
}
