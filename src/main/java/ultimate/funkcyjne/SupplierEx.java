package ultimate.funkcyjne;

import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class SupplierEx {

    public static void main(String[] args) {

        IntSupplier supplier = () -> 2 + 2 * 2;

        IntStream.rangeClosed(1, 10).forEach(i -> System.out.println(supplier.getAsInt() + i));
    }
}
