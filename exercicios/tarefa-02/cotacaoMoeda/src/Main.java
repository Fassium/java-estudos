import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Main {
    static void main(String[] args) {
        Moeda real = new Moeda("BRL", "Real brasileiro", 2);
        Moeda dolar = new Moeda("USD", "Dólar americano", 2);
        Moeda euro = new Moeda("EUR", "Euro", 2);
        Moeda iene = new Moeda("JPY", "Iene japonês", 0);

        LocalDateTime agora = LocalDateTime.now();

        CotacaoRepositorio repositorio = new CotacaoRepositorio();
        repositorio.salvar(new Cotacao(dolar, real, new BigDecimal("5.40"), agora.minusDays(1)));
        repositorio.salvar(new Cotacao(euro, real, new BigDecimal("5.90"), agora.minusDays(1)));
        repositorio.salvar(new Cotacao(real, iene, new BigDecimal("27.50"), agora.minusDays(1)));

        ConversorMoeda conversor = new ConversorMoeda(repositorio, real);

        imprimir(conversor.converter(new BigDecimal("100"), dolar, real, agora));
        imprimir(conversor.converter(new BigDecimal("540"), real, dolar, agora));
        imprimir(conversor.converter(new BigDecimal("100"), dolar, euro, agora));
        imprimir(conversor.converter(new BigDecimal("100"), dolar, iene, agora));
    }

    private static void imprimir(Conversao conversao) {
        System.out.println(conversao.getValorOrigem() + " " + conversao.getMoedaOrigem().getCodigoIso4217()
                + " = " + conversao.getValorConvertido() + " " + conversao.getMoedaDestino().getCodigoIso4217()
                + " (taxa " + conversao.getTaxaEfetiva().setScale(6, RoundingMode.HALF_EVEN) + ")");
    }
}
