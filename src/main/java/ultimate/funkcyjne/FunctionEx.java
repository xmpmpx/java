package ultimate.funkcyjne;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class FunctionEx {

    public static void main(String[] args) {

        IntFunction<String> toString = a -> a + "i";
        IntStream.rangeClosed(1, 10).forEach(a -> System.out.println(toString.apply(a)));
    }
}
