package generics;

import java.util.ArrayList;
import java.util.List;

public class Methods {

    public static void main(String[] args) {
        Float liczba = 2.2F;
        Double liczba2 = 3.4;
        String text = "Text";

        Float aFloat = printAndReturn(liczba);
        Double aDouble = printAndReturn(liczba2);
        String aString = printAndReturn(text);

        List<String> lista = new ArrayList<>();
        lista.add("0");
        lista.add("1");
        lista.add("1");
        lista.add("2");

        modifyList(lista);
    }

    public static <T> T printAndReturn(T object) {
        System.out.println(object);
        return object;
    }

    public static <T> void modifyList(List<T> lista) {
        T element = lista.get(0); // można pobrać
        lista.set(1, element); // można ustawić
        lista.remove(lista.size() - 1); // można usunąć\
        lista.add(element); // można dodać
        System.out.println(lista);
    }
}
