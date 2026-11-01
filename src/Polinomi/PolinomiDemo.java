package Polinomi;

public class PolinomiDemo {
    public static void main(String[] args) {
        Polinom p1 = new Polinom(6,new double[]{-30,-4,47,6,-18,-2,1});
        Double[] nule = p1.pronadjiNule();
        for(Double nula:nule){
            System.out.println(nula);
        }
//        try {
//        }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//        System.out.println(Polinom.pomnozi(Polinom.pomnozi(p1,p1),2));
    }
}
