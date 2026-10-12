public class Main {
    static final double SALARIO_MINIMO = 1518.00;

    static void main(String[] args) {
        // 1 e 2: tipos e var
        var idade = 31;
        var salario = 4500.50;
        var inicial = 'T';
        var empregado = true;
        var populacao = 215_000_000L;

        System.out.println(idade);
        System.out.println(salario);
        System.out.println(inicial);
        System.out.println(empregado);
        System.out.println(populacao);

        // 3: operadores
        int a = 17, b = 5;
        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(a % b);

        a += 3;
        System.out.println(a);

        // 4: ternário
        String tipo = a % 2 == 0 ? "par" : "ímpar";
        System.out.println(tipo);

        // 5: cálculo
        double qtdSalarios = salario / SALARIO_MINIMO;
        double salarioAnual = salario * 12;
        System.out.printf("%.2f salários mínimos%n", qtdSalarios);
        System.out.printf("Salário anual: %.2f%n", salarioAnual);

        // desafio: trocar a e b
        int temp = a;
        a = b;
        b = temp;
        System.out.println(a + " " + b);

        double media;
        media = (10 + 7.0) / 2;

        System.out.println(media);

        double preco = 19.99;
        int inteiro = (int) preco;


    }
}
