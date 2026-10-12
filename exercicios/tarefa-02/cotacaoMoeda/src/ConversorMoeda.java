import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;

public class ConversorMoeda {
    private final CotacaoRepositorio cotacaoRepositorio;
    private final Moeda moedaPivo;

    public ConversorMoeda(CotacaoRepositorio cotacaoRepositorio, Moeda moedaPivo) {
        if (cotacaoRepositorio == null) {
            throw new IllegalArgumentException("O repositório de cotações é obrigatório");
        }
        if (moedaPivo == null) {
            throw new IllegalArgumentException("A moeda pivô é obrigatória");
        }
        this.cotacaoRepositorio = cotacaoRepositorio;
        this.moedaPivo = moedaPivo;
    }

    public Conversao converter(BigDecimal valor, Moeda moedaOrigem, Moeda moedaDestino, LocalDateTime momento) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor a converter deve ser maior que zero");
        }
        if (moedaOrigem == null || moedaDestino == null) {
            throw new IllegalArgumentException("As moedas de origem e de destino são obrigatórias");
        }
        if (moedaOrigem.getId() == moedaDestino.getId()) {
            throw new IllegalArgumentException("A moeda de origem e a de destino devem ser diferentes");
        }
        if (momento == null) {
            throw new IllegalArgumentException("O momento da conversão é obrigatório");
        }

        BigDecimal valorBruto;
        Optional<BigDecimal> porPar = converterPorPar(valor, moedaOrigem, moedaDestino, momento);
        if (porPar.isPresent()) {
            valorBruto = porPar.get();
        } else {
            valorBruto = converterViaPivo(valor, moedaOrigem, moedaDestino, momento);
        }

        BigDecimal valorConvertido = valorBruto.setScale(moedaDestino.getCasasDecimais(), RoundingMode.HALF_EVEN);
        BigDecimal taxaEfetiva = valorBruto.divide(valor, MathContext.DECIMAL128);

        return new Conversao(moedaOrigem, moedaDestino, valor, valorConvertido, taxaEfetiva, momento);
    }

    private Optional<BigDecimal> converterPorPar(BigDecimal valor, Moeda origem, Moeda destino, LocalDateTime momento) {
        Optional<Cotacao> direta = cotacaoRepositorio.buscarVigente(origem, destino, momento);
        if (direta.isPresent()) {
            return Optional.of(direta.get().aplicar(valor));
        }

        Optional<Cotacao> inversa = cotacaoRepositorio.buscarVigente(destino, origem, momento);
        if (inversa.isPresent()) {
            return Optional.of(inversa.get().aplicarInversa(valor));
        }

        return Optional.empty();
    }

    private BigDecimal converterViaPivo(BigDecimal valor, Moeda origem, Moeda destino, LocalDateTime momento) {
        Optional<BigDecimal> primeiraPerna = converterPorPar(valor, origem, moedaPivo, momento);
        if (primeiraPerna.isEmpty()) {
            throw cotacaoNaoEncontrada(origem, destino);
        }

        Optional<BigDecimal> segundaPerna = converterPorPar(primeiraPerna.get(), moedaPivo, destino, momento);
        if (segundaPerna.isEmpty()) {
            throw cotacaoNaoEncontrada(origem, destino);
        }

        return segundaPerna.get();
    }

    private IllegalArgumentException cotacaoNaoEncontrada(Moeda origem, Moeda destino) {
        return new IllegalArgumentException("Não há cotação de " + origem.getCodigoIso4217()
                + " para " + destino.getCodigoIso4217());
    }
}
