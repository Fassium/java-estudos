import java.util.UUID;

public class Cliente {
    private final UUID id;
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.cpf = cpf;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
