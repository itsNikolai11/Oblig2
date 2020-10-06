package no.nkopperudmoen;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;


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
        throw new UnsupportedOperationException();
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
        //TODO Loop igjennom til indeksen er funnet.
        //TODO sett noden før sin .next til den nye noden. Sett noden som kommer etter sin previous til den nye noden.
        int i = 0;
        Node<T> temp = hode;
        while (i < indeks) {
            if (temp.neste != null) {
                temp = temp.neste;
            }
            i++;
        }
        Node<T> newNode = new Node<>(verdi, temp, temp.neste);
        temp.neste.forrige = newNode;
        temp.neste = newNode;
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
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
            //TODO let fra hodet og fremover
        } else {
            siste = hale;
            int i = antall - 1;
            while (i > indeks) {
                siste = siste.forrige;
                i--;
            }
            //TODO let fra hale og bakover
        }


        return siste;
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        if(nyverdi == null){
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
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
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


