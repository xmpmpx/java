package generics;

import java.util.ArrayList;

public class Basics {

    public static void main(String[] args) {

        Number nr = 5.5; //dziedziczenie - przypisanie Double to Number
        // List<Number> lista = new ArrayList<Integer>(); //W listach generycznych dziedziczenie nie występuje

        //Użycie wildcard
        ArrayList<Float> lista = new ArrayList<>();
        System.out.println(rozmiarListy(lista));
    }

    public static int rozmiarListy(ArrayList<?> lista) {
        // Object o = lista.get(); - tylko Object przy pobraniu
        // lista.add(2); - nie można dodawać elementów
        lista.add(null); //tylko null można dodać
        return lista.size();
    }
}
