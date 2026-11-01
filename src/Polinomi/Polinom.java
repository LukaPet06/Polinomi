package Polinomi;

import java.util.ArrayList;
import java.util.Arrays;

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
    public static Polinom[] hornerovaSema(final Polinom polinom,final double potencijalnaNula){
        Polinom kolicnik = new Polinom(polinom.n-1);
        Polinom ostatak = new Polinom(0);
        kolicnik.koeficijenti[kolicnik.n] = polinom.koeficijenti[polinom.n];
        for (int i = kolicnik.n; i >=1 ; i--) {
            kolicnik.koeficijenti[i-1] = kolicnik.koeficijenti[i]*potencijalnaNula + polinom.koeficijenti[i];
        }
        ostatak.koeficijenti[0] = kolicnik.koeficijenti[0]*potencijalnaNula+polinom.koeficijenti[0];

        Polinom[] rezultat = new Polinom[2];
        rezultat[0] = kolicnik;
        rezultat[1] = ostatak;
        return rezultat;

    }
    public Double[] pronadjiNule(){
        if (this.n == 0){
            if (this.koeficijenti[0] == 0){
                throw new RuntimeException("Polinom Stepena 0 i koef. 0 ima beskonacno nula");
            }
            else {
                throw new RuntimeException("Polinom stepena 0 nema nule");
            }
        }
        Polinom polinom = this;
        ArrayList<Double> nuleList = new ArrayList<Double>();//Polinom moze imati najvise n nula

        double[] deliociSlobodanClan = Delioci.DeliociBroja(polinom.koeficijenti[0]); //p
        double[] deliociNajvisiStepen = Delioci.DeliociBroja(polinom.koeficijenti[polinom.n]); //q

        boolean doneFlag = false;
        //p/q potencijalna nula
        for (int i = 0; i < deliociSlobodanClan.length; i++) {
            if (doneFlag){
                break;
            }
            for (int j = 0; j < deliociNajvisiStepen.length; j++) {
                Polinom[] rezultat = Polinom.hornerovaSema(polinom, deliociSlobodanClan[i] /deliociNajvisiStepen[j]);
                if (rezultat[1].koeficijenti[0] == 0){
                    nuleList.add(deliociSlobodanClan[i] /deliociNajvisiStepen[j]);
                    polinom = rezultat[0];
                    if (polinom.n ==0){
                        doneFlag = true;
                        break;
                    }
                    deliociSlobodanClan = Delioci.DeliociBroja(polinom.koeficijenti[0]);
                    deliociNajvisiStepen = Delioci.DeliociBroja(polinom.koeficijenti[polinom.n]);
                    i = 0;
                    j = 0;
                    break;
                }
            }
        }
        Double[] nule = new Double[nuleList.size()];
        for (int i = 0; i < nule.length; i++) {
            nule[i] = nuleList.get(i);
        }

        return nule;
    }
    private void ispraviStepen(){
        for (int i = n; i >=0; i--) {
            if(!(koeficijenti[i] == 0)){
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
