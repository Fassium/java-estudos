# Tarefa 01: Como o Java funciona + ambiente

## O que foi estudado
- **JDK ⊃ JRE ⊃ JVM**: o JDK traz o compilador (`javac`), a JRE traz a JVM e as bibliotecas.
- Fluxo: `.java` → `javac` → `.class` (bytecode) → JVM (interpreta + JIT) → CPU.
- Bytecode inspecionado com `javap -c`. Diferença para o Delphi: o `.exe` tem código de máquina nativo; o `.class` tem instruções de uma CPU virtual.
- Ambiente: JDK 25 (Temurin) e IntelliJ funcionando; erros de compilação lidos na IDE.

## Projeto: mini-pedido de venda
Em vez do cartão de visita, foi construído um domínio completo (pasta `Tarefa01/src`):

```
Cliente 1──N Pedido 1──N ItemPedido N──1 Produto
```

- `Produto`, `Cliente`, `Pedido`, `ItemPedido`: todos com `id` `UUID` gerado na própria classe (`final`).
- `Pedido`: número automático via variável de classe (`static`), lista de itens (`ArrayList`), `adicionarItem`, `getSubtotal`, `getDesconto` (10% quando o subtotal passa de 200, valida a porcentagem com `IllegalArgumentException`), `getTotal` e `imprimir` (nota formatada com `printf`).

## Conceitos praticados
classe, construtor, `this`, getters, `final`, `static`, `List`/`ArrayList`, `for` de coleção, `if`, divisão inteira vs `double`, `throw new`, `printf` (`%s`, `%d`, `%.2f`, `%n`).

## Armadilhas encontradas
- `(100 - p) / 100` com `int` dá `0` (divisão inteira): multiplicar antes ou usar `double`.
- `double` não é o ideal para dinheiro; o correto é `BigDecimal` (tarefa futura).
- `printf` usa o idioma do sistema: no Windows em português sai vírgula decimal.

## Como rodar
Abrir `Tarefa01` no IntelliJ e executar a classe `Main`.
