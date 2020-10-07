#### **Bidragsytere**

Nikolai Kopperudmoen, s344169
Har jobbet med oppgavene alene. 

##**Oppgavebeskrivelse**
Alle oppgavene består testene, og alle warnings er fjernet. Oppgave 6 feiler test for effektivitet 
av og til, men sjeldent når den testen kjøres alene. 

####**Oppgave 1**
Hvis hode == null, er listen tom.
Verdiene legges inn med en for-loop, og null-verdier blir ignorert.
Verdiene som legges inn i listen lagres i variabelene current og prev.  Disse variablene
brukes for å sette forrige og neste i nodene. 

####**Oppgave 2**
toString() - begynner på hodet og looper igjennom hver node sin next, og legger til verdien til StringBuilder.
omvendtString() - looper gjennom hver node nedover fra halen og legger til verdiene. 
leggInn() metoden looper gjennom node sin neste helt til neste er null, og legger så til en ny node med nyeste verdi
som neste.
####**Oppgave 3**
Ser gjennom listen med en while-loop. Er indeks større enn antall/2, teller den fra hale. Hvis indeks er i siste halvpart
av listen er det bortkastede ressurser å gå gjennom hele første halvdel. 
subliste-metoden finner først den første noden ved hjelp av finnNode-metoden. Deretter 
bruker den en loop som legger til neste node så lenge int i er mindre enn til-verdien som tas inn.

####**Oppgave 4**

indeksTil-metoden starter med int i = 0 og variabelen current er satt til hode-noden på listen. Så lenge verdiene ikke er
 like settes current til current.neste. 
Hvis indeks == antall har den loopet gjennom hele listen uten å finne verdien, og returnerer -1.
metoden inneholder() bruker indeksTil-metoden. Dersom indeksTil returnerer -1, returnerer inneholder() false.

####**Oppgave 5**

leggInn(int indeks, T verdi) sjekker at verdi ikke er null, at indeks er gyldig, og at listen ikke er tom.
Hvis listen er tom skal det kastes IndexOutOfBoundsException dersom index er høyere enn 0. 
Noden som var på indeksen før flyttes til høyre. Pekere før og etter oppdateres. 
 
####**Oppgave 6**

fjern(verdi) leter fra hode til den finner noden med verdi, og returnerer false hvis verdien ikke blir funnet. 
pekerne til nodene før og etter verdien som fjernes blir oppdatert.

fjern(indeks) sjekker at indeks er gyldig. Looper forfra dersom indeks er mindre enn antall/2, eller bakfra for å spare
ressurser.

####**Oppgave 8**
next() sjekker at det faktisk finnes en neste-verdi i listen og returnerer den.
denne-verdien settes til neste node. 
 
Metoden Iterator<T> iterator() returnerer en new DobbeltLenketListeIterator.

Iterator<T> iterator(int indeks) - metoden oppretter en ny instans ved hjelp av konstruktøren som tar inn (int indeks).





