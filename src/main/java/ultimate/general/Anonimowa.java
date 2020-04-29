package ultimate.general;

import java.util.ArrayList;
import java.util.List;

public class Anonimowa {

    // pole klasy
    public Abstract abs = new Abstract() {
        @Override
        public void play() {

        }
    };

    //pole statyczne klasy
    public static List<Integer> lista = new ArrayList<>() {
        @Override
        public boolean add(Integer integer) {
            System.out.println("Dodano: " + integer);
            return super.add(integer);
        }
    };

    public static void main(String[] args) {
        Anonimowa anonimowa = new Anonimowa();
        anonimowa.abs.play();

        Anonimowa.lista.add(5);

        // zwykła zmienna
        Interfejs in = new Interfejs() {
            @Override
            public void graj() {

            }
        };
        in.graj();
    }
}

abstract class Abstract {
    public abstract void play();
}

interface Interfejs {
    void graj();
}