package no.nkopperudmoen;

public class Main {

    public static void main(String[] args) {
        Liste<String> liste = new DobbeltLenketListe<>();
        System.out.println(liste.antall() + " " + liste.tom());
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

