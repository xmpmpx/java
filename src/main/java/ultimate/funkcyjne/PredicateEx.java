package ultimate.funkcyjne;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class PredicateEx {

    public static void main(String[] args) {

        IntPredicate over3 = arg -> arg > 3;
        IntPredicate less10 = arg -> arg < 10;
        IntStream.rangeClosed(1, 10).forEach(a -> {
            if (over3.and(less10).test(a)) {
                System.out.println(a);
            }
        });

    }
}
