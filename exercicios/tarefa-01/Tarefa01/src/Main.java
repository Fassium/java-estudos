public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Maria Silva", "12345678900");

        Produto teclado = new Produto("Teclado", 89.90);
        Produto mouse = new Produto("Mouse", 45.50);

        Pedido pedido1 = new Pedido(cliente);
        pedido1.adicionarItem(teclado, 2);
        pedido1.adicionarItem(mouse, 1);

        Pedido pedido2 = new Pedido(cliente);
        pedido2.adicionarItem(mouse, 3);

        System.out.println("Pedido " + pedido1.getNumero() + ": " + pedido1.getSubtotal());
        System.out.println("Pedido " + pedido2.getNumero() + ": " + pedido2.getSubtotal());
    }
}
