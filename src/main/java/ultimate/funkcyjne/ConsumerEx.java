package ultimate.funkcyjne;

import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class ConsumerEx {

    public static void main(String[] args) {

        IntPredicate mul2Over5 = a -> a > 5;

        IntConsumer print = integer -> System.out.print(integer + " ");

        IntConsumer multiply = integer -> {
            if (mul2Over5.test(integer)) {
                integer *= 2;
            }
            System.out.print(integer + " ");
        };

        IntStream.rangeClosed(1, 10).forEach(print);
        System.out.println();
        IntStream.rangeClosed(1, 10).forEach(multiply::accept);
        System.out.println();
        IntStream.rangeClosed(1, 10).forEach(print.andThen(multiply));

    }
}
