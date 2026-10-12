public class Exercicio1 {
    static void main(String[] args) {
        double valor1 = 8;
        double valor2 = 7;
        double valor3 = 10;
        double total = valor1 + valor2 + valor3;
        double media = retornarMedia(total, 3);
        System.out.printf("A média é %.2f%n", media);
    }

    static double retornarMedia(double valor, int divisor) {
        return valor / divisor;
    }
}
