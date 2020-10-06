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
        Character[] c = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',};
        DobbeltLenketListe<Integer> liste = new DobbeltLenketListe<>();
        liste.leggInn(0, 4);  // ny verdi i tom liste
        System.out.println(liste.toString());
        liste.leggInn(0, 2);  // ny verdi legges forrest
        System.out.println(liste.toString());
        liste.leggInn(2, 6);  // ny verdi legges bakerst
        System.out.println(liste.toString());
        liste.leggInn(1, 3);  // ny verdi nest forrest
        System.out.println(liste.toString());
        liste.leggInn(3, 5);  // ny verdi nest bakerst
        System.out.println(liste.toString());
        liste.leggInn(0, 1);  // ny verdi forrest
        System.out.println(liste.toString());
        liste.leggInn(6, 7);  // ny verdi legges bakerst
        DobbeltLenketListe<Integer> tom = new DobbeltLenketListe<>();
      /*  System.out.println(liste.indeksTil('A'));
        System.out.println(liste.indeksTil('C'));
        System.out.println(liste.indeksTil('K'));
        System.out.println(liste.inneholder('A'));
        System.out.println(liste.inneholder('G'));
        System.out.println(liste.inneholder('K'));
        liste.leggInn(0, 'N');
        liste.leggInn(3,'L');
        liste.leggInn(liste.antall(), 'K');
        System.out.println(liste.toString());
*/


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

