import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Conversao {
    private static long proximoId = 1;

    private final long id;
    private final Moeda moedaOrigem;
    private final Moeda moedaDestino;
    private final BigDecimal valorOrigem;
    private final BigDecimal valorConvertido;
    private final BigDecimal taxaEfetiva;
    private final LocalDateTime dataHora;

    public Conversao(Moeda moedaOrigem, Moeda moedaDestino, BigDecimal valorOrigem,
                     BigDecimal valorConvertido, BigDecimal taxaEfetiva, LocalDateTime dataHora) {
        if (moedaOrigem == null) {
            throw new IllegalArgumentException("A moeda de origem da conversão é obrigatória");
        }
        if (moedaDestino == null) {
            throw new IllegalArgumentException("A moeda de destino da conversão é obrigatória");
        }
        if (moedaOrigem.getId() == moedaDestino.getId()) {
            throw new IllegalArgumentException("A moeda de origem e a de destino devem ser diferentes");
        }
        if (valorOrigem == null || valorOrigem.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor de origem deve ser maior que zero");
        }
        if (valorConvertido == null || valorConvertido.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor convertido não pode ser negativo");
        }
        if (taxaEfetiva == null || taxaEfetiva.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A taxa efetiva deve ser maior que zero");
        }
        if (dataHora == null) {
            throw new IllegalArgumentException("A data e hora da conversão são obrigatórias");
        }

        this.id = proximoId++;
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valorOrigem = valorOrigem;
        this.valorConvertido = valorConvertido;
        this.taxaEfetiva = taxaEfetiva;
        this.dataHora = dataHora;
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

    public BigDecimal getValorOrigem() {
        return valorOrigem;
    }

    public BigDecimal getValorConvertido() {
        return valorConvertido;
    }

    public BigDecimal getTaxaEfetiva() {
        return taxaEfetiva;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
