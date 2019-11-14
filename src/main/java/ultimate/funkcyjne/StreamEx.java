package ultimate.funkcyjne;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamEx {

    public static void main(String[] args) {

        Stream.of("A", "B", "C", "D").forEach(System.out::println);

        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5).stream();

        Consumer<Number> print = a -> System.out.print(a + " ");

        Stream.generate(() -> new Random().nextInt(49) + 1).limit(6).forEach(print);
        System.out.println();

        Stream.iterate(0, i -> i + 2).limit(20).forEach(print);
        System.out.println();

        Stream.iterate(0, i -> i < 10, i -> i + 1).filter(integer -> integer % 2 == 0).forEach(print);
        System.out.println();

        IntStream.rangeClosed(5, 19).forEach(i -> System.out.print(i + " "));
        System.out.println();

        DoubleStream.generate(() -> new Random().nextDouble()).limit(5).forEach(i -> System.out.print(i + " "));
        System.out.println();

        //---------------------------------
        long s = System.currentTimeMillis();
        HashSet<Integer> nums = new HashSet<>();
        Stream.iterate(new Random().nextInt(49) + 1, i -> nums.size() < 6, i -> new Random().nextInt(49) + 1)
                .filter(nums::add)
                .forEach(integer -> System.out.print(integer + " "));
        System.err.println(System.currentTimeMillis() - s);

        System.out.println();

        s = System.currentTimeMillis();
        HashSet<Integer> numbers = new HashSet<>();
        do {
            int r = new Random().nextInt(49) + 1;
            if (numbers.add(r)) {
                System.out.print(r + " ");
            }
        } while (numbers.size() < 6);
        System.err.println(System.currentTimeMillis() - s);
    }
}
