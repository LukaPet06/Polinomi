package Polinomi;

public class PolinomiDemo {
    public static void main(String[] args) {
        Polinom p1 = new Polinom(new double[]{1,-1});

        System.out.println(Polinom.pomnozi(Polinom.pomnozi(p1,p1),2));
    }
}
