package Polinomi;

import java.security.KeyPair;
import java.util.Arrays;
import java.util.Map;

public class Polinom {
    private int n;
    private double[] koeficijenti;


    public Polinom(int n) {
        if(n<0){
            throw new RuntimeException("Stepen polinoma ne sme biti manji od 0");
        }
        this.n = n;
        this.koeficijenti = new double[n+1];
        for (int i = 0; i <= n; i++) {
            this.koeficijenti[i] = 0;
        }
    }

    public Polinom(double[] koef) {
        this.n = koef.length-1;
        this.koeficijenti = new double[n+1];
        for (int i = 0; i <= n; i++) {
            this.koeficijenti[i] = koef[i];
        }
        ispraviStepen();
    }

    public Polinom(int n, double[] koef ) {
        if(n<0){
            throw new RuntimeException("Stepen polinoma ne sme biti manji od 0");
        }
        this.n = n;
        this.koeficijenti = new double[n+1];
        for (int i = 0; i <= n; i++) {
            if(i<koef.length){
                this.koeficijenti[i] = koef[i];
            }
            else{
                this.koeficijenti[i] = 0;
            }
        }
        ispraviStepen();
    }

    public static Polinom saberi(final Polinom levi,final Polinom desni){
        int zbirN = Math.max(levi.n, desni.n);
        double[] koef = new double[zbirN+1];
        for (int i = 0; i <= levi.n; i++) {
            koef[i]+=levi.koeficijenti[i];
        }
        for (int i = 0; i <= desni.n; i++) {
            koef[i]+=desni.koeficijenti[i];
        }

        return new Polinom(zbirN,koef);
    }
    public static Polinom oduzmi(final Polinom levi,final Polinom desni){
        return saberi(levi,pomnozi(desni,-1));
    }
    public static Polinom pomnozi(final Polinom levi,final Polinom desni) {
        int proizvodN = levi.n+desni.n;
        double[] koef = new double[proizvodN+1];
        for (int i = 0; i <= levi.n; i++) {
            for (int j = 0; j <= desni.n; j++) {
                koef[i+j] += levi.koeficijenti[i]*desni.koeficijenti[j];
            }
        }
        return new Polinom(proizvodN,koef);
    }
    public static Polinom pomnozi(final Polinom levi,final double konstanta){
        for (int i = 0; i <levi.n ; i++) {
            levi.koeficijenti[i] *= konstanta;
        }
        return levi;
    }

    private void ispraviStepen(){
        for (int i = n; i >=0; i--) {
            if (koeficijenti[i] == 0) continue;
            else{
                n = i;
                return;
            }
        }
    }
    @Override
    public String toString() {
        String opis = "P("+n+"):";
        for(int i = n;i>=0;i--){
            if(koeficijenti[i] == 0) continue;
            opis+= String.format("%.2f",koeficijenti[i])+"x^"+i;
            if(i>0){
                opis+=" + ";
            }
        }
        return opis;
    }



}
