package Polinomi;

import java.util.TreeSet;

/**
 * Pomocna klasa koja pronalazi delioce odredjenog koeficijenta
 */
public class Delioci {
    /**
     * Najpre se odredjuje granica, odnosno koliko brojeva treba proveriti</br>
     * U skup se dodaju brojevi u parovima, odnosno ukoliko broj bez ostatka deli koeficijent, dodaju se i taj broj i njihov kolicnik</br>
     * Primer za 35 se u parovima dodaju 1,35 i 5,7
     * @param koeficijent broj
     * @return jedinstvene vrednosti, brojeva koji bez ostatka dele poslati parametar
     */
    public static TreeSet<Double> DeliociBroja(double koeficijent){
        int granica = (int)(Math.sqrt(Math.abs(koeficijent)))+1;
        TreeSet<Double> deliociSet = new TreeSet<Double>();
        deliociSet.add((double) 1);
        deliociSet.add((double) -1);
        deliociSet.add(koeficijent);
        deliociSet.add(-koeficijent);
        for (int i = 2; i <= granica; i++) {
            if (koeficijent%i == 0) {
                deliociSet.add((double) i);
                deliociSet.add((double) -i);
                deliociSet.add(koeficijent/i);
                deliociSet.add(-koeficijent/i);
            }
        }
        return deliociSet;
    }
}
