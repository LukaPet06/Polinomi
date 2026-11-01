package Polinomi;

import java.util.ArrayList;

public class Delioci {
    public static double[] DeliociBroja(double koeficijent){
        int granica = (int)(Math.sqrt(Math.abs(koeficijent)))+1;
        ArrayList<Double> deliociLista = new ArrayList<Double>();
        deliociLista.add((double) 1);
        deliociLista.add((double) -1);

        for (int i = 2; i <= granica; i++) {
            if (koeficijent%i == 0) {
                deliociLista.add((double) i);
                deliociLista.add((double) -i);
                deliociLista.add(koeficijent/i);
                deliociLista.add(-koeficijent/i);
            }
        }

        if(koeficijent!=1 && koeficijent!=-1){
            deliociLista.add(koeficijent);
            deliociLista.add(-koeficijent);
        }
        double[] delioci = new double[deliociLista.size()];
        for (int i = 0; i < delioci.length; i++) {
            delioci[i] = deliociLista.get(i);
        }
        return delioci;
    }
}
