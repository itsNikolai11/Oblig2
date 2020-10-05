package no.nkopperudmoen;

public class Main {

    public static void main(String[] args) {
        Liste<String> liste = new DobbeltLenketListe<>();
        System.out.println(liste.antall() + " " + liste.tom());
        Liste<Integer> ints = new DobbeltLenketListe<>(new Integer[]{1, null});
        System.out.println(ints.antall());
        Liste<Integer> ints1 = new DobbeltLenketListe<>(new Integer[]{null, 1, null});
        System.out.println(ints1.antall());
    }


    /**
     * Oppgave 1
     */
    int antall() {
        return 0;
    }

    boolean tom() {
        return false;
    }

}

