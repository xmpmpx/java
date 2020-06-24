package ultimate.general;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Compar {

    public static void main(String[] args) {

        List<String> imiona = Arrays.asList("Tomek", "Marcin", "Jarek", "Czesiek", "Tom", "Ann");
        List<Integer> liczby = Arrays.asList(5, 76, 22, 88, 12, 55, 77);

        liczby.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);
        System.out.println();
        imiona.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);
        System.out.println();
        imiona.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        System.out.println();
        imiona.stream().sorted(Comparator.comparing(String::length, Comparator.reverseOrder())).forEach(System.out::println);
        System.out.println();
        imiona.stream().sorted(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder())).forEach(System.out::println);
    }
}
