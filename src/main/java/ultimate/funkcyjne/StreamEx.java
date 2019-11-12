package ultimate.funkcyjne;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamEx {

    public static void main(String[] args) {

        Stream.of("A", "B", "C", "D").forEach(System.out::println);

        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5).stream();

        Consumer<Integer> print = a -> System.out.print(a + " ");

        Stream.generate(() -> new Random().nextInt(49) + 1).limit(6).forEach(print);
        System.out.println();

        Stream.iterate(0, i -> i + 2).limit(20).forEach(print);
        System.out.println();

        Stream.iterate(0, i -> i < 10, i -> i + 1).filter(integer -> integer % 2 == 0).forEach(print);
        System.out.println();
    }
}
