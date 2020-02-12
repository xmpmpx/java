package ultimate.funkcyjne;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamMethods {
    public static void main(String[] args) {

        Martini.getData().stream().filter(martini -> martini.name.equals("Marcin") && martini.age > 2).forEach(System.out::println);
        Martini.getData().stream().filter(martini -> martini.name.equals("Marcin")).filter(martini -> martini.age > 2).forEach(System.out::println);

        Martini.getData().stream().map(martini -> martini.name = "ON").forEach(System.out::println); //ON
        Martini.getData().stream().map(martini -> martini.name).forEach(System.out::println);

        Martini.getData().forEach(System.out::print);
        System.out.println();
        Martini.getData().stream().forEach(System.out::print);
        System.out.println();

        Martini.getData().stream().map(martini -> martini.name).findFirst().ifPresent(System.out::println); // 1-wszy el. ze strumienia
        boolean anyTomek = Martini.getData().stream().map(martini -> martini.name).anyMatch(s -> s.equals("Tomek")); // jakikolwiek
        boolean anyOver10 = Martini.getData().stream().map(martini -> martini.age).allMatch(s -> s > 10); // wszystkie
        boolean noneOver60 = Martini.getData().stream().map(martini -> martini.age).noneMatch(s -> s > 60); // żaden

        System.out.println();

        // Reduce - redukuje strumien do jednej wartości
        int silnia = IntStream.iterate(1, v -> v <= 5, v -> v + 1).reduce(1, (left, right) -> left * right);
        System.out.println("Suma: " + silnia);

        IntStream.iterate(1, v -> v <= 5, v -> v + 1).reduce((left, right) -> left * right).ifPresent(System.out::println);

        Martini.getData().stream().map(martini -> martini.age).reduce(BinaryOperator.minBy(Integer::compareTo)).ifPresent(System.out::println);
        Martini.getData().stream().map(martini -> martini.age).reduce(Integer::max).ifPresent(System.out::println);

        List<Integer> list = Martini.getData().stream().map(martini -> martini.age).collect(Collectors.toList());
        System.out.println("Ilość: " + Martini.getData().stream().map(martini -> martini.age).count());

        String lista = Martini.getData().stream().map(martini -> martini.age.toString()).collect(Collectors.joining(","));
        System.out.println(lista);

        LinkedList<Martini> collect = Martini.getData().stream().collect(Collectors.toCollection(LinkedList::new));

        Map<String, Integer> mapa = Martini.getData().stream().collect(Collectors.toMap(o -> o.name, o -> o.age));
        for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        Martini.getData().stream().collect(Collectors.groupingBy(o -> o.age))
                .forEach((integer, martinis) -> System.out.println(integer + " | " + martinis));

        Martini.getData().stream().limit(1).forEach(System.out::println); // ogranicza do pierwszego elementu ze streama
        Martini.getData().stream().skip(2).forEach(System.out::println); // pomija 2 pierwsze elementy
        Martini.getData().stream().mapToInt(martini -> martini.age).distinct().forEach(System.out::println); // bazuje na equals()
        System.out.println();
        Martini.getData().stream().map(martini -> martini.age).sorted(Comparator.reverseOrder()).forEach(System.out::println);
        Martini.getData().stream().sorted(Comparator.comparing(martini -> -martini.age)).forEach(System.out::println);

        //IntStream, LongStream itd.

        HashSet<Integer> lotto = new HashSet<>();
        IntStream.iterate(new Random().nextInt(49), v -> lotto.size() < 6, v -> new Random().nextInt(49)).forEach(lotto::add);
        System.out.println(lotto);
    }
}
