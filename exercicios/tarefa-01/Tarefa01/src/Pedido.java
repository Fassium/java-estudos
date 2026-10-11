import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
    private static int ultimoNumero = 0;
    private final UUID id;
    private int numero;
    private Cliente cliente;
    private List<ItemPedido> itens;

    public Pedido(Cliente cliente) {
        this.id = UUID.randomUUID();
        this.numero = ++ultimoNumero;
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

    public double getDesconto(int porcentagemDesconto) {

        if (porcentagemDesconto <= 0 || porcentagemDesconto > 100) {
            throw new IllegalArgumentException("A porcentagem deve estar entre 1 e 100");
        }

        double subTotal = getSubtotal();
        double valorDesconto = 0;

        if (subTotal > 200) {
            valorDesconto = subTotal * porcentagemDesconto / 100;
        }
        return valorDesconto;
    }

    public double getTotal(int porcentagemDesconto) {
        return getSubtotal() - getDesconto(porcentagemDesconto);
    }

    public void imprimir(int porcentagemDesconto) {
        String linha = "=".repeat(35);
        String traco = "-".repeat(35);

        System.out.printf("%s%n", linha);
        System.out.printf("PEDIDO %d%n", this.getNumero());
        System.out.printf("Cliente: %s%n", this.getCliente().getNome());
        System.out.printf("%s%n", traco);
        System.out.printf("%-12s %3s %8s %9s%n", "Item", "Qtd", "Unit.", "Total");

        for (ItemPedido item : itens) {
            System.out.printf("%-12s %3d %8.2f %9.2f%n",
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getProduto().getPreco(),
                    item.getTotal());
        }

        System.out.printf("%s%n", traco);
        System.out.printf("%-25s %9.2f%n", "Subtotal:", this.getSubtotal());
        System.out.printf("%-25s %9.2f%n", "Desconto:", this.getDesconto(porcentagemDesconto));
        System.out.printf("%-25s %9.2f%n", "TOTAL:", this.getTotal(porcentagemDesconto));
        System.out.printf("%s%n", linha);
    }

}
