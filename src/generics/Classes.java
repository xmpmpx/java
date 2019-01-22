package generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Classes<T, K> {

    private T pole;

    // private static T pole2; - musi być instancja klasy by podać typ, więc static odpada

    void metoda(T arg) {
    }

    public static void main(String[] args) {
        LosowaLista<Integer> lista = new LosowaLista<>();
        lista.add(4);
        lista.add(44);
        lista.add(444);
        System.out.println(lista.get());


        LosowaLista<Pies> lista2 = new LosowaLista<>();
        LosowaLista<Kot> lista3 = new LosowaLista<>();
    }
}

class LosowaLista<T extends Comparable<? super T>> { // możliwość wielu extends za pomocą &

    private List<T> lista = new ArrayList<>();

    private Random random = new Random();

    public void add(T element) {
        lista.add(element);
    }

    public T get() {
        return lista.get(random.nextInt(lista.size()));
    }

    public void sort() {
        Collections.sort(lista);
    }
}

class Pies implements Comparable<Pies> {

    @Override
    public int compareTo(Pies o) {
        return 0;
    }
}

class Kot extends Pies {

}