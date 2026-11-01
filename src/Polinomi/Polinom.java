package Polinomi;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Klasa za rad sa polinomima
 * Polja polinoma su:</br>
 *  * n - stepen polinoma</br>
 *  * koeficijenti - niz double vrednosti koji predstavljaju koeficijente clanova, koeficijenti[0] pripada clanu x^0, koeficijenti[n] pripada clanu x^n</br>
 *  * nule - Array lista Double vrednosti, na inicijalizaciji je prazna, popunjava se funkcijom pronadjiNule i sadrzi Nule jednog objekta Polinom</br>
 *  * provereneNule - bool vrednost, pomocno polje, sluzi da se izbegne ponovno pretrazivanje nula ili slicno</br>
 *  </br>
 * Metode za rad sa polinomima su:</br>
 *  *saberi - staticka, sabira dva polinoma</br>
 *  *oduzmi - staticka, sabira jedan polinom sa drugim polinomom, gde je drugi pomnozen sa -1</br>
 *  *pomnozi - mnozi dva polinoma ili polinom sa konstantom</br>
 *  *hornerovaSema - deli polinom pomocu hornerove seme</br>
 *  *pronadjiNule - pronalazi sve realne nule polinoma</br>
 *  *ispraviStepen - ispravlja stepen lose formatiranog polinoma</br>
 */
public class Polinom {
    private int n;
    final private double[] koeficijenti;
    final private ArrayList<Double> nule = new ArrayList<>();
    private boolean provereneNule = false;

    /**
     * Inicijalizuje Polinom stepena n tako da mu svi koeficijenti budu 0
     * @param n stepen polinoma
     * @exception RuntimeException baca exception kada je stepen manji od 0
     * @author Luka Petrović
     * 
     */
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
    /**
     * Inicijalizuje Polinom pomocu niza koeficijenata, stepen se postavlja na broj elemenata tog niza, a zatim smanjuje ako je format bio los
     * @param koef niz koeficijenata
     * @exception RuntimeException baca exception ukoliko je poslat prazan niz koeficijenata
     * @author Luka Petrović
     *
     */
    public Polinom(double[] koef) {
        if (koef.length == 0){
            throw new RuntimeException("Niz koeficijenata ne sme biti prazan ukoliko je to jedini izvor podataka za kreaciju polinoma");
        }
        this.n = koef.length-1;
        this.koeficijenti = new double[n+1];
        for (int i = 0; i <= n; i++) {
            this.koeficijenti[i] = koef[i];
        }
        ispraviStepen();
    }

    /**
     * Inicijalizuje polinom i pomocu stepena n i pomocu niza koeficijenata</br>
     * Broj elemenata u nizu koeficijenata nije bitan dokle god je stepen n veci ili jednak 0
     * </br>
     * Ukoliko je stepen manji od broja elemenata, visak se ignorise
     * @param n stepen
     * @param koef niz koeficijenata
     * @exception RuntimeException baca exception kada je stepen manji od 0
     * @author Luka Petrović
     * 
     */
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

    /**
     * Staticki metod za sabiranje dva polinoma, odredjuje veci stepen na osnovu dva parametra,
     * i kreira niz koeficijenata duzine tog veceg stepena,
     * zatim se na indekse sabiraju vrednosti koeficijenata parametara kroz dve odvojene for petlje
     *
     * @param levi sabirak
     * @param desni sabirak
     * @return novi objekat polinoma sastavljen od novog stepena i novih koeficijenata
     * @author Luka Petrović
     * 
     */
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

    /**
     * Staticki metod za oduzimanje implementira dve funkcije,
     * funkciju pomnozi koja desni parametar mnozi konstantom -1, a zatim sabira tako izmenjen polinom sa levim parametrom
     * @param levi umanjenik
     * @param desni umanjilac
     * @return razlika
     * @author Luka Petrović
     * 
     */
    public static Polinom oduzmi(final Polinom levi,final Polinom desni){
        return saberi(levi,pomnozi(desni,-1));
    }

    /**
     * Staticki metod za mnozenje dva polinoma,
     * odredjuje novi stepen koji je jednak zbiru stepena levog i desnog parametra i rezervise dovoljno mesta u nizu</br>
     * Vrsi se mnozenje svaki sa svakim elementom, gde su i,j njihovi indeksi, taj proizvod se zatim dodaje na i+j mesto u rezervisanom nizu
     * @param levi cinilac
     * @param desni cinilac
     * @return proizvod
     * @author Luka Petrović
     * 
     */
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

    /**
     * Staticki metod za mnozenje polinoma konstantom, overloadovana verzija pomnozi metoda,
     * mnozi svaki element niza koeficijenata konstantom iz parametra
     * @param levi polinom
     * @param konstanta uneta konstanta
     * @return rezultat
     * @author Luka Petrović
     *
     */
    public static Polinom pomnozi(final Polinom levi,final double konstanta){
        Polinom rezultat = new Polinom(levi.n,levi.koeficijenti);
        for (int i = 0; i <=levi.n ; i++) {
            rezultat.koeficijenti[i] *= konstanta;
        }
        return rezultat;
    }

    /**
     *Staticki metod koji deli polinom pomocu hornerove seme,
     * najpre se kreiraju dva Polinoma, kolicnik, stepena n-1 i ostatak, stepena 0, gde je n stepen polinoma koji delimo.</br>
     * Hornerova sema nalaze da koeficijent uz clan najviseg stepena pocetnog polinoma prepisemo u koeficijent najviseg stepena polinoma kolicnik</br>
     * Dalje iterativno idemo unazad kroz kolicnik od najviseg stepena do stepena 1.
     * U niz koeficijenata polinoma kolicnik na i-1 mesto postavljamo proizvod parametra potencijalneNula i kolicnik.koeficijenti[i]
     * i uz to saberemo vrednost i-tog koeficijenta polinoma koji delimo.
     *
     * @param polinom polinom koji zelimo da podelimo
     * @param potencijalnaNula double konstanta iz bezuovog stava (x-a), potencijalna nula je a
     * @return Polinom[2], gde je prvi element kolicnik, a drugi ostatak pri deljenju
     * @author Luka Petrović
     * 
     */

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

    /**
     * Metod koji za objekat Polinoma pronalazi realne nule</br></br>
     *
     * Za pocetak ukoliko su nule vec proverene, proces se ne ponavlja jer je "skup"</br>
     * Promenljiva polinom inicijalizovana je vrednoscu objekta, takodje uvodimo pocetnu promenljivu zavrsenaProvera</br>
     *
     * Algoritam se ponavlja sve dok se ne provere sve moguce potencijalne nule. </br>
     * Realne potencijalne nule se mogu predstaviti pomocu razlomka p/q, </br>
     * gde je p delilac koeficijenta uz slobodni clan, a q delilac koeficijenta uz najvisi stepen,</br>
     * a sve takve delioce cuvamo u TreeSet strukturama, koje cuvaju samo unique sortirane vrednosti</br>
     * </br>
     * Zatim sledi ugnjezdena petlja koja ispituje sve moguce kombinacije elemenata iz deliociSlobodanClan i deliociNajvisiStepen kroz hornerovu semu.</br>
     * Ukoliko neka provera vrati rezultat u kojoj je ostatak 0, znaci da smo pronasli nulu, nula se dodaje u atribut objekta,</br>
     * a na polinom se stavlja nova vrednost, odnosno kolicnik</br>
     * Algoritam se ponavlja, ponovo se traze delioci za novi polinom, pa opet svi idu kroz hornerovu semu itd..</br></br>
     * Kada se prodje kroz sve potencijalne nule, a nula se ne pronadje ili kada polinom koji delimo bude stepena 0, funkcija se prekida
     *
     *
     * </br></br></br>
     * Trenutni propust je da se nule mogu pronaci samo za celobrojne koeficijente
     * U suprtnom pisace da polinom nema realne nule
     *
     * @author Luka Petrović
     */
    public void pronadjiNule(){
        if(this.provereneNule || this.n == 0){
            provereneNule = true;
            return;
        }
        else{
            provereneNule = true;
        }
        Polinom polinom = this;
        boolean zavrsenaProvera = false;

        while (!zavrsenaProvera){
            boolean pronadjenaNula = false;
            TreeSet<Double> deliociSlobodanClan = Delioci.DeliociBroja(polinom.koeficijenti[0]);
            TreeSet<Double> deliociNajvisiStepen = Delioci.DeliociBroja(polinom.koeficijenti[polinom.n]);
            for (Double p:deliociSlobodanClan) {
                if (zavrsenaProvera||pronadjenaNula){
                    break;
                }

                for (Double q:deliociNajvisiStepen) {
                    Polinom[] rezultat = Polinom.hornerovaSema(polinom, p /q);
                    if (rezultat[1].koeficijenti[0] == 0){
                        this.nule.add(p /q);
                        pronadjenaNula = true;
                        polinom = rezultat[0];
                        if (polinom.n ==0){
                            zavrsenaProvera = true;

                        }
                        break;
                    }
                }

            }
            if(!pronadjenaNula){
                zavrsenaProvera = true;

            }
        }

    }

    /**
     * Metod koji ispravlja stepen polinoma na poslednju vrednost koja nije nula
     * Ukoliko je polinom stepena 6 a koeficijenti su mu 1,2,0,0,0,0,0
     * Nule ce se izignorisati a polinom ce postati stepena 1 kao sto i jeste 1,2
     * @author Luka Petrović
     * 
     */
    private void ispraviStepen(){
        for (int i = n; i >=0; i--) {
            if(!(koeficijenti[i] == 0)){
                n = i;
                return;
            }
        }
    }

    /**
     *
     * Vraca opis polinoma u dva reda u formatu:</br>
     *      * P(n): .2fx^n + .2fx^n-1 + ...+ .2fx^1 + .2f</br>
     *      * Nule: nula1, nula2, nula3, ...</br>
     *       gde je n stepen polinoma, .2f koeficijent clana
     *       Nula moze i ne mora biti
     *      </br
     *      *Ukoliko je koeficijent 0 taj clan se ne ispisuje, osim ako je polinom stepena 0
     * @return opis polinoma
     * @author Luka Petrović
     *
     */
    @Override
    public String toString() {
        String opis = "P("+n+"):";
        for(int i = n;i>0;i--){
            if(koeficijenti[i] == 0) continue;
            if (koeficijenti[i] != 1) {
                opis += String.format("%.2f",Math.abs(koeficijenti[i]));
            }
            opis+= "x";
            if(i>1){
                opis+="^"+i;
                if (koeficijenti[i-1]>=0){
                    opis += " + ";
                }
                else {
                    opis += " - ";

                }

            }
        }
        if(koeficijenti.length >1 && koeficijenti[0]!=0 ){
            if (koeficijenti[0]>=0){
                opis += " + ";
            }
            else {
                opis += " - ";

            }
            opis+=String.format("%.2f",Math.abs(koeficijenti[0]));
        }
        else if (koeficijenti.length == 1){
            if (koeficijenti[0] < 0) {
                opis += " - ";
            }
            opis+=String.format("%.2f",Math.abs(koeficijenti[0]));
        }

        if (!this.provereneNule){
            opis+="\nNule nisu ispitane, iskoristite funkciju pronadjiNule()";
        }
        else if(n==0 && koeficijenti[0] == 0){
            opis+="\nOvaj polinom ima beskonacno mnogo nula";
        }
        else if(nule.isEmpty()){
            opis+="\nOvaj polinom nema realne nule";
        }
        else{
            opis+="\nNule polinoma: ";
            for (int i = 0; i < nule.size(); i++)
            {
                opis += nule.get(i);
                if (i<nule.size()-1) {
                    opis += ", ";
                }
                else {
                    opis += "\n";
                }
            }
        }
        return opis;
    }



}
