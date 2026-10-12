public class Moeda {
    private static int proximoId = 1;

    private final int id;
    private final String codigo;
    private final String nome;
    private final int casasDecimais;

    public Moeda(String codigo, String nome, int casasDecimais) {

        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("O código da moeda é obrigatório");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da moeda é obrigatório");
        }

        if (casasDecimais < 0) {
            throw new IllegalArgumentException("As casas decimais não podem ser negativas");
        }

        this.id = proximoId++;
        this.codigo = codigo;
        this.nome = nome;
        this.casasDecimais = casasDecimais;
    }

    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getCasasDecimais() {
        return casasDecimais;
    }
}
