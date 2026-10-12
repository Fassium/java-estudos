public class Moeda {
    private static int proximoId = 1;

    private final int id;
    private final String codigoIso4217;
    private final String nome;
    private final int casasDecimais;

    public Moeda(String codigoIso4217, String nome, int casasDecimais) {

        if (codigoIso4217 == null || !codigoIso4217.matches("[A-Z]{3}")) {
            throw new IllegalArgumentException("O código ISO 4217 deve ter exatamente 3 letras maiúsculas (ex.: USD)");
        }

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da moeda é obrigatório");
        }

        if (casasDecimais < 0) {
            throw new IllegalArgumentException("As casas decimais não podem ser negativas");
        }

        this.id = proximoId++;
        this.codigoIso4217 = codigoIso4217;
        this.nome = nome;
        this.casasDecimais = casasDecimais;
    }

    public int getId() {
        return id;
    }

    public String getCodigoIso4217() {
        return codigoIso4217;
    }

    public String getNome() {
        return nome;
    }

    public int getCasasDecimais() {
        return casasDecimais;
    }
}
