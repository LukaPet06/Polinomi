package Polinomi;

import java.util.TreeSet;

public class PolinomiDemo {
    public static void main(String[] args) {
        System.out.println("ISPIS EXCEPTION GRESAKA:\n");
        try {
            Polinom stepenManjiOd0 = new Polinom(-1);
        }
        catch (Exception e){
            System.out.println(e.getMessage());//Stepen polinoma ne sme biti manji od 0
        }
        try {
            Polinom prazanNizKoef = new Polinom(new double[]{});
        }
        catch (Exception e){
            System.out.println(e.getMessage());//Niz koeficijenata ne sme biti prazan ukoliko je to jedini izvor podataka za kreaciju polinoma
        }
        try {
            Polinom kombinacija = new Polinom(-1,new double[]{});
        }
        catch (Exception e){
            System.out.println(e.getMessage());//Stepen polinoma ne sme biti manji od 0
        }
        try {
            Polinom kombinacijaSaPraznimKoef = new Polinom(0,new double[]{});
            System.out.println("\nKombinacija konstruktora, stepen 0, prazan niz: \n"+kombinacijaSaPraznimKoef+"\n");
            //Kombinacija konstruktora, stepen 0, prazan niz:
            //P(0):0,00
            //Nule nisu ispitane, iskoristite funkciju pronadjiNule()
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }



        System.out.println("\n\nPRIMERI POLINOMA I OSNOVNIH FUNKCIJA");

        try {
            Polinom primer = new Polinom(3,new double[]{2,-7,6,1});
            System.out.println("\nPrimer polinoma:\n"+ primer+"\n");
            //Primer polinoma:
            //P(3):1,00x^3 + 6,00x^2 + -7,00x^1 + 2,00
            //Nule nisu ispitane, iskoristite funkciju pronadjiNule()
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            Polinom p1 = new Polinom(5,new double[]{1,2,1});
            Polinom p2 = new Polinom(3,new double[]{1,1});
            System.out.println("\np1+p2="+ Polinom.saberi(p1,p2));
            System.out.println("\np1-p2="+ Polinom.oduzmi(p1,p2));
            System.out.println("\np1*p2="+ Polinom.pomnozi(p1,p2));
            //p1+p2=P(2):1,00x^2 + 3,00x^1 + 2,00
            //Nule nisu ispitane, iskoristite funkciju pronadjiNule()
            //
            //p1-p2=P(2):1,00x^2 + 1,00x^1
            //Nule nisu ispitane, iskoristite funkciju pronadjiNule()
            //
            //p1*p2=P(3):1,00x^3 + 3,00x^2 + 3,00x^1 + 1,00
            //Nule nisu ispitane, iskoristite funkciju pronadjiNule()
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }



        System.out.println("\n\nPRIMERI HORNEROVA SEMA");
        try {
            Polinom p1 = new Polinom(6,new double[]{-30,-4,47,6,-18,-2,1});
            var rezulat = Polinom.hornerovaSema(p1,5);
            System.out.println("\nKolicnik pri deljenju p1 i (x-5): \n"+rezulat[0]);
            System.out.println("Ostatak pri deljenju p1 i (x-5): \n"+rezulat[1]);
            //Kolicnik pri deljenju p1 i (x-5):
            //P(5):1,00x^5 + 3,00x^4 + -3,00x^3 + -9,00x^2 + 2,00x^1 + 6,00
            //Nule nisu ispitane, iskoristite funkciju pronadjiNule()
            //Ostatak pri deljenju p1 i (x-5):
            //P(0):0,00
            //Nule nisu ispitane, iskoristite funkciju pronadjiNule()

            rezulat = Polinom.hornerovaSema(p1,-13);
            System.out.println("\nKolicnik pri deljenju p1 i (x+13): \n"+rezulat[0]);
            System.out.println("Ostatak pri deljenju p1 i (x+13): \n"+rezulat[1]);
            //Kolicnik pri deljenju p1 i (x+13):
            //P(5):1,00x^5 + -15,00x^4 + 177,00x^3 + -2295,00x^2 + 29882,00x^1 + -388470,00
            //Nule nisu ispitane, iskoristite funkciju pronadjiNule()
            //Ostatak pri deljenju p1 i (x+13):
            //P(0):5050080,00
            //Nule nisu ispitane, iskoristite funkciju pronadjiNule()
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("\n\nPRIMERI PRONALAZENJE NULA");
        try {
            Polinom p1 = new Polinom(2,new double[]{1,2,1});
            p1.pronadjiNule();
            System.out.println(p1);
            //P(2):1,00x^2 + 2,00x^1 + 1,00
            //Nule polinoma: -1.0, -1.0,

            Polinom p2 = new Polinom(0,new double[]{1});
            p2.pronadjiNule();
            System.out.println(p2);
            //P(0):1,00
            //Ovaj polinom nema realne nule

            Polinom p3 = new Polinom(0,new double[]{0});
            p3.pronadjiNule();
            System.out.println(p3);
            //P(0):0,00
            //Ovaj polinom ima beskonacno mnogo nula

            Polinom p4 = new Polinom(6,new double[]{-30,-4,47,6,-18,-2,1});
            p4.pronadjiNule();
            System.out.println(p4);
            //P(6):1,00x^6 + -2,00x^5 + -18,00x^4 + 6,00x^3 + 47,00x^2 + -4,00x^1 + -30,00
            //Nule polinoma: 5.0, -3.0, 1.0, -1.0
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
