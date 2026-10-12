import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CotacaoRepositorio {
    private final List<Cotacao> cotacoes = new ArrayList<>();

    public void salvar(Cotacao cotacao) {
        if (cotacao == null) {
            throw new IllegalArgumentException("A cotação a salvar é obrigatória");
        }

        for (Cotacao existente : cotacoes) {
            boolean mesmoInicio = existente.getVigenciaInicio().isEqual(cotacao.getVigenciaInicio());
            if (ehDoPar(existente, cotacao.getMoedaOrigem(), cotacao.getMoedaDestino()) && mesmoInicio) {
                throw new IllegalArgumentException("Já existe cotação de "
                        + cotacao.getMoedaOrigem().getCodigoIso4217() + " para "
                        + cotacao.getMoedaDestino().getCodigoIso4217() + " com esse início de vigência");
            }
        }

        cotacoes.add(cotacao);
    }

    public Optional<Cotacao> buscarVigente(Moeda moedaOrigem, Moeda moedaDestino, LocalDateTime momento) {
        if (moedaOrigem == null || moedaDestino == null) {
            throw new IllegalArgumentException("As moedas de origem e de destino são obrigatórias");
        }
        if (momento == null) {
            throw new IllegalArgumentException("O momento da busca é obrigatório");
        }

        Cotacao vigente = null;

        for (Cotacao cotacao : cotacoes) {
            boolean jaComecou = !cotacao.getVigenciaInicio().isAfter(momento);
            boolean maisRecente = vigente == null
                    || cotacao.getVigenciaInicio().isAfter(vigente.getVigenciaInicio());

            if (ehDoPar(cotacao, moedaOrigem, moedaDestino) && jaComecou && maisRecente) {
                vigente = cotacao;
            }
        }

        return Optional.ofNullable(vigente);
    }

    private boolean ehDoPar(Cotacao cotacao, Moeda moedaOrigem, Moeda moedaDestino) {
        return cotacao.getMoedaOrigem().getId() == moedaOrigem.getId()
                && cotacao.getMoedaDestino().getId() == moedaDestino.getId();
    }
}
