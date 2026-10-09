void main(String[] args) {

    Produto produto1 = new Produto("Teclado", 89.90);
    Produto produto2 = new Produto("Mause", 45.50);

    System.out.println("ID: " + produto1.getId());
    System.out.println("Nome: " + produto1.getNome());
    System.out.println("Preço: " + produto1.getPreco());
    System.out.println();
    System.out.println("ID: " + produto2.getId());
    System.out.println("Nome: " + produto2.getNome());
    System.out.println("Preço: " + produto2.getPreco());

}