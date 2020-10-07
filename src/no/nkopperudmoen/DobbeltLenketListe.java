package no.nkopperudmoen;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        this.hode = null;

    }

    public DobbeltLenketListe(T[] a) {
        if (a == null) {
            throw new NullPointerException("Tabellen a er null!");
        }
        if (a.length == 0) {
            hode = null;
        }

        Node<T> current = null;
        Node<T> prev = null;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                if (this.hode == null) {
                    hode = new Node<>(a[i], null, null);
                    if (i == a.length - 1) {
                        hale = hode;
                        antall++;
                        break;
                    }
                    prev = hode;
                    antall++;

                } else {
                    current = new Node<>(a[i], prev, null);
                    prev.neste = current;
                    prev = current;
                    antall++;
                }
            }
        }
        if (current != null) {
            hale = current;
        }

    }

    public Liste<T> subliste(int fra, int til) {
        fraTilKontroll(fra, til);
        DobbeltLenketListe<T> sublist = new DobbeltLenketListe<>();
        if (tom()) {
            return sublist;
        }
        Node<T> current = finnNode(fra);
        int i = fra;
        while (i < til) {
            sublist.leggInn(current.verdi);
            current = current.neste;
            i++;
        }
        return sublist;
    }

    private void fraTilKontroll(int fra, int til) {
        if (this.antall < til || fra < 0) {
            throw new IndexOutOfBoundsException("");
        }
        if (til - fra < 0) {
            throw new IllegalArgumentException("");
        }


    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return this.hode == null;
    }

    @Override
    public boolean leggInn(T verdi) {
        if (verdi == null) {
            throw new NullPointerException("Kan ikke legge til null-verdier!");
        }

        Node<T> newNode = new Node<>(verdi, null, null);
        if (tom()) {
            hode = newNode;
            hale = newNode;
            antall++;
            endringer++;
            return true;
        }
        Node<T> last = hode;
        while (last.neste != null) {
            last = last.neste;
        }
        last.neste = newNode;
        newNode.forrige = last;
        hale = newNode;
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        indeksKontroll(indeks, true);
        if (verdi == null) {
            throw new NullPointerException("Verdi kan ikke være null!");
        }
        if (tom()) {
            hode = new Node<>(verdi, null, hale);
            hale = new Node<>(verdi, hode, null);
            antall++;
            endringer++;
            return;
        }
        int i = 0;
        Node<T> temp = hode;
        while (i < indeks) {
            if (temp.neste != null) {
                temp = temp.neste;
            }
            i++;
        }

        Node<T> newNode = new Node<>(verdi, temp.forrige, temp);
        if (i == 0) {

            hode = newNode;
            hode.neste = temp;
            temp.forrige = hode;
            hode.forrige = null;
            antall++;
            endringer++;
            return;
        }
        if (i == antall) {
            hale = newNode;
            hale.neste = null;
            hale.forrige = temp;
            temp.neste = hale;
            antall++;
            endringer++;
            return;
        }
        temp.forrige.neste = newNode;
        temp.forrige = newNode;
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks) {
        return finnNode(indeks).verdi;
    }

    private Node<T> finnNode(int indeks) throws IndexOutOfBoundsException {
        indeksKontroll(indeks, false);
        if (tom()) {
            throw new IndexOutOfBoundsException("");
        }
        Node<T> siste;
        if (indeks >= antall) {
            throw new IndexOutOfBoundsException("");
        }
        if (indeks < antall / 2) {
            siste = hode;
            int i = 0;
            while (i < indeks) {
                siste = siste.neste;
                i++;
            }
        } else {
            siste = hale;
            int i = antall - 1;
            while (i > indeks) {
                siste = siste.forrige;
                i--;
            }
        }


        return siste;
    }

    @Override
    public int indeksTil(T verdi) {
        if (verdi == null || tom()) {
            return -1;
        }
        Node<T> current = hode;
        int indeks = 0;
        while (!current.verdi.equals(verdi)) {
            current = current.neste;
            indeks++;
            if (indeks == antall) {
                return -1;
            }
        }
        return indeks;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        if (nyverdi == null) {
            throw new NullPointerException("Verdien kan ikke være null!");
        }
        indeksKontroll(indeks, true);
        Node<T> oppdatert = finnNode(indeks);
        T gammelVerdi = oppdatert.verdi;
        oppdatert.verdi = nyverdi;

        endringer++;
        return gammelVerdi;

    }

    @Override
    public boolean fjern(T verdi) {
        if (verdi == null) {
            return false;
        }
        if (tom()) {
            return false;
        }
        Node<T> curr = hode;
        while (!curr.verdi.equals(verdi)) {
            if (curr.neste == null) {
                return false;
            }
            curr = curr.neste;
        }
        if (curr.neste == null) {
            if (curr.forrige == null) {
                hode = null;
                hale = null;
                endringer++;
                antall--;
                return true;
            }
            curr.forrige.neste = null;
            hale = curr.forrige;
            endringer++;
            antall--;
            return true;
        }
        if (curr.forrige == null) {
            curr.neste.forrige = null;
            hode = curr.neste;
            endringer++;
            antall--;
            return true;
        }
        curr.neste.forrige = curr.forrige;
        curr.forrige.neste = curr.neste;
        endringer++;
        antall--;
        return true;

        //as
/*
        if (curr.neste == null && curr.forrige == null) {
            hode = null;
            hale = null;
            endringer++;
            antall--;
            return true;
        }
        if (curr.neste == null) {
            curr.forrige.neste = null;
            hale = curr.forrige;
            endringer++;
            antall--;
            return true;
        }
        if (curr.forrige == null) {
            curr.neste.forrige = null;
            hode = curr.neste;
            endringer++;
            antall--;
            return true;
        }
        curr.forrige.neste = curr.neste;
        curr.neste.forrige = curr.forrige;
        antall--;
        endringer++;
        return true;*/
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);
        if (tom()) {
            return null;
        }
        Node<T> curr = hode;
        int i = 0;

        if (indeks > antall / 2) {
            i = antall - 1;
            curr = hale;
            while (i > indeks) {
                if (curr.forrige != null) {
                    curr = curr.forrige;
                }
                i--;
            }
        } else {
            while (i < indeks) {
                if (curr.neste != null) {
                    curr = curr.neste;
                }
                i++;
            }
        }

        if (curr.forrige == null) {
            //Hode
            if (curr.neste == null) {
                hode = null;
                hale = null;
                endringer++;
                antall--;
                return curr.verdi;
            }
            hode = curr.neste;
            curr.neste.forrige = null;
            endringer++;
            antall--;
            return curr.verdi;
        }
        if (curr.neste == null) {
            //Hale
            curr.forrige.neste = null;
            hale = curr.forrige;
            endringer++;
            antall--;
            return curr.verdi;
        }
        curr.forrige.neste = curr.neste;
        curr.neste.forrige = curr.forrige;
        endringer++;
        antall--;
        return curr.verdi;
    }


    @Override
    public void nullstill() {
        this.hode = null;
        this.hale = null;
        antall = 0;
        endringer++;
    }

    @Override
    public String toString() {
        if (tom()) {
            return "[]";
        }
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node<T> siste = hode;
        while (siste.neste != null) {
            s.append(siste.verdi).append(", ");
            siste = siste.neste;
        }
        s.append(siste.verdi);
        s.append("]");

        return s.toString();
    }

    public String omvendtString() {
        if (tom()) {
            return "[]";
        }
        StringBuilder s = new StringBuilder();
        s.append("[");
        Node<T> siste = hale;
        while (siste.forrige != null) {
            s.append(siste.verdi).append(", ");
            siste = siste.forrige;
        }
        s.append(siste.verdi);
        s.append("]");
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException();
            }

            fjernOK = true;
            T verdi = denne.verdi;
            if (denne.neste != null) {
                denne = denne.neste;
                return verdi;
            } else {
                denne = null;
            }
            return verdi;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


