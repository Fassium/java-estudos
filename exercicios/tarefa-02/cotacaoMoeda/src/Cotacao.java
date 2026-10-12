import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Cotacao {
    private static long proximoId = 1;

    private final long id;
    private final Moeda moeda;
    private BigDecimal valorCambio;
    private final LocalDateTime vigencia;

    public Cotacao(Moeda moeda, BigDecimal valorCambio, LocalDateTime vigencia) {

        if (moeda == null) {
            throw new IllegalArgumentException("A moeda da cotação é obrigatória");
        }
        if (valorCambio == null || valorCambio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do câmbio deve ser maior que zero");
        }
        if (vigencia == null) {
            throw new IllegalArgumentException("A vigência da cotação é obrigatória");
        }

        this.id = proximoId++;
        this.moeda = moeda;
        this.valorCambio = valorCambio;
        this.vigencia = vigencia;
    }

    public long getId() {
        return id;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    public BigDecimal getValorCambio() {
        return valorCambio;
    }

    public LocalDateTime getVigencia() {
        return vigencia;
    }
}
