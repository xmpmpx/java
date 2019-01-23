package generics;

import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TypeArguments {
    public static void main(String[] args) {
        List<String> lista = new ArrayList<>(); // tylko typy referencyjne
        List<int> lista1 = new ArrayList<int>(); // trzeba podać dodatkowo typ podczas tworzenia obiektu
        List<int[]> lista2 = new ArrayList<>(); // tablica traktowana jako obiekt bo dziedziczy po Objects
        List<?> lista3 = new ArrayList<>();

        lista1.add(5);
    }

}

class TypeArgumenrsParametrized<T> {
    List<T> lista = new ArrayList<>();
    // List<T super String> lista = new ArrayList<>(); - nie można zmieniać typu
}

class PrzekazywanieTypu {
    public void main(String[] args) {
        List lista = new ArrayList(); // lista goła - dodać monża cokolwiek
        List<String> lista1 = new ArrayList<>(); // deklaracjia jawna

        wypisz(5);
        wypisz("5");
        // this.<String>wypisz(4); - można takie zabezpieczenie ale to kretynizm

        List<?> listaString = Collections.<String>emptyList();
        // listaString.add("ABC"); - i tak się nie da
    }

    public <T> void wypisz(T arg) {
        System.out.println(arg);
    }
}

class ArgumentyZagniezdzone {
    public static void main(String[] args) {
        List<String> lista = new ArrayList<>(); // lista konkretna
        // List<?> lista1 = new ArrayList<?>(); - jakiś idiotyzm
        List<?> lista1 = new ArrayList<>();

        List<List<?>> lista2 = new ArrayList<>();
        lista2.add(new ArrayList<String>()); // lista heterogeniczna
        lista2.add(new LinkedList<Integer>()); // może przechowywać elementy różnego typu

        List<? super List<?>> lista3 = new ArrayList<>();
        lista3.add(new ArrayList<>());
        lista3.add(new LinkedList<>());

        List<? super Number> lista4 = new ArrayList<>();
        lista4.add(4);
        lista4.add(4.0F);

        List<? super Exception> lista5 = new ArrayList<>();
        lista5.add(new ReadOnlyBufferException());
    }
}
