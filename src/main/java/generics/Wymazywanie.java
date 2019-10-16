package generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

interface Inter<E> { // będzie interfejs goły
    public void method(E arg); // będzie Object

}

class Generics implements Inter<String> { // będzie bez parametru

    @Override
    public void method(String arg) {

    }

    /*public void method(Object arg) { // będzie metoda dodatkowa
        metoda((String) arg); // wywołanie naszej metody
    }*/
}


// public class Wymazywanie<E extends Number & Comparable > { // klas goła, ale wszystkie wystąpienia oaramentry jako skrajne
// lewe ograniczenie - w metodach i polach
public class Wymazywanie<E> { // zostanie usunięte <E> - będzie klasa goła

    private E pole;

    public E metoda(E argument) { // będzie typ Object lub skrajne lewe
        return argument;
    }

    public void metoda2() {
        ArrayList<String> lista = new ArrayList<>(); // podczas iteracji typ Object i rzutowanie
        List<?> lista2 = Collections.EMPTY_LIST;
        Comparable<String> comparable;
    }
}
