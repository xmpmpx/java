package ultimate.generics;

import java.util.ArrayList;

public class Refleksja {

    public static void main(String[] args) {

        ArrayList<String> lista = metodaTworzaca();
        ArrayList<Integer> listaLiczb = metodaTworzaca();

        lista.add("ss");
        listaLiczb.add(32);

        System.out.println(lista.get(0));
        System.out.println(listaLiczb.get(0));

        // ------------------------------------

        String string = metodaTworzaca2(String.class);
        String abc = sprawdzanieTypu("abc", String.class);

        System.out.println(abc);
    }

    private static <T> T sprawdzanieTypu(Object o, Class<T> c) {
        // if (o instanceof T) {} - nie można przez instanceof
        if (c.isInstance(o)) {
            return c.cast(o); // lub return (T) o;
        }
        return null;
    }

    private static <T> ArrayList<T> metodaTworzaca() {
        return new ArrayList<>();
    }

    // rozwiązanie niedobre
    private static <T> T metodaTworzaca2(Class<T> c) {
        try {
            return c.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
