import java.util.UUID;

public class Produto {
    private final UUID id;
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.preco = preco;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}
