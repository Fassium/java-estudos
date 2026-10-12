import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;

public class Cotacao {
    private static long proximoId = 1;

    private final long id;
    private final Moeda moedaOrigem;
    private final Moeda moedaDestino;
    private final BigDecimal taxaCambio;
    private final LocalDateTime vigenciaInicio;

    public Cotacao(Moeda moedaOrigem, Moeda moedaDestino, BigDecimal taxaCambio, LocalDateTime vigenciaInicio) {
        if (moedaOrigem == null) {
            throw new IllegalArgumentException("A moeda de origem da cotação é obrigatória");
        }
        if (moedaDestino == null) {
            throw new IllegalArgumentException("A moeda de destino da cotação é obrigatória");
        }
        if (moedaOrigem.getId() == moedaDestino.getId()) {
            throw new IllegalArgumentException("A moeda de origem e a de destino devem ser diferentes");
        }
        if (taxaCambio == null || taxaCambio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A taxa de câmbio deve ser maior que zero");
        }
        if (vigenciaInicio == null) {
            throw new IllegalArgumentException("O início da vigência da cotação é obrigatório");
        }

        this.id = proximoId++;
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.taxaCambio = taxaCambio;
        this.vigenciaInicio = vigenciaInicio;
    }

    public long getId() {
        return id;
    }

    public Moeda getMoedaOrigem() {
        return moedaOrigem;
    }

    public Moeda getMoedaDestino() {
        return moedaDestino;
    }

    public BigDecimal getTaxaCambio() {
        return taxaCambio;
    }

    public LocalDateTime getVigenciaInicio() {
        return vigenciaInicio;
    }

    public BigDecimal aplicar(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O valor a converter é obrigatório");
        }

        return valor.multiply(taxaCambio);
    }

    public BigDecimal aplicarInversa(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("O valor a converter é obrigatório");
        }

        return valor.divide(taxaCambio, MathContext.DECIMAL128);
    }
}
