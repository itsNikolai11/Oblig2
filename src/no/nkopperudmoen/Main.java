package no.nkopperudmoen;

public class Main {

    public static void main(String[] args) {
        String[] s1 = {}, s2 = {"A"}, s3 = {null, "A", null, "B", null};
        DobbeltLenketListe<String> l1 = new DobbeltLenketListe<>(s1);
        DobbeltLenketListe<String> l2 = new DobbeltLenketListe<>(s2);
        DobbeltLenketListe<String> l3 = new DobbeltLenketListe<>(s3);
        /*System.out.println(l1.toString() + " " + l2.toString()
                + " " + l3.toString() + " " + l1.omvendtString() + " "
                + l2.omvendtString() + " " + l3.omvendtString());*/
        System.out.println(l1.toString());
        System.out.println(l2.toString());
        System.out.println(l3.toString());
        l3.leggInn("C");
        System.out.println(l3.toString());

        System.out.println(l1.omvendtString());
        System.out.println(l2.omvendtString());
        System.out.println(l3.omvendtString());
        Character[] c = {'A','B','C','D','E','F','G','H','I','J',};
        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);
        System.out.println(liste.indeksTil('A'));
        System.out.println(liste.indeksTil('C'));
        System.out.println(liste.indeksTil('K'));
        System.out.println(liste.inneholder('A'));
        System.out.println(liste.inneholder('G'));
        System.out.println(liste.inneholder('K'));


/*
        System.out.println(liste.subliste(3,8)); // [D, E, F, G, H]
        System.out.println(liste.subliste(5,5)); // []
        System.out.println(liste.subliste(8,liste.antall())); // [I, J]*/


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

