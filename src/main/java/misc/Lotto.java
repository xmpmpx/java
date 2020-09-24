package misc;

import java.util.HashSet;
import java.util.Random;
import java.util.stream.IntStream;

public class Lotto {

    public static void main(String[] args) {
        HashSet<Integer> numbers = new HashSet<>();
        while (numbers.size() < 6) {
            numbers.add(new Random().nextInt(49) + 1);
        }
        System.out.println(numbers);

        numbers.clear();
        IntStream.generate(() -> new Random().nextInt(49) + 1).limit(100).distinct().limit(6).forEach(numbers::add);
        System.out.println(numbers);

        numbers.clear();
        IntStream.iterate(new Random().nextInt(49) + 1, value -> numbers.size() < 6,
                o -> {
                    numbers.add(o);
                    return new Random().nextInt(49) + 1;
                }).forEach(System.out::println);
    }
}
