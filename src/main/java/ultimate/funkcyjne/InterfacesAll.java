package ultimate.funkcyjne;

import java.util.function.*;

public class InterfacesAll {

    public static void main(String[] args) {

        Predicate<Martin> predicate = martin -> martin.name.equals(martin.surname); // 1 arg
        predicate.test(new Martin()); // wykonuje test i zwraca boolean

        Consumer<Martin> consumer = martin -> System.out.println(martin.name); // 1 arg
        consumer.accept(new Martin()); // wykonuje dowolne operacje i nic nie zwraca

        Supplier<Martin> supplier = Martino::new; // brak argumentu
        supplier.get(); // zwraca obiekt typu generycznego lub jemu pochodnego

        Function<Martin, String> function = martin -> martin.name; // 1 arg
        function.apply(new Martin()); // pobiera 1 argument i zwraca wartość typu 2 argumentu

        UnaryOperator<Martin> unaryOperator = martin -> new Martin(); // 1 arg
        unaryOperator.apply(new Martin()); // pobiera 1 argument typu generycznego i zwraca obiekt też tego typu

        BiFunction<Martin, Martino, String> biFunction = (martin, martino) -> martin.name + martino.age;
        biFunction.apply(new Martin(), new Martino()); // pobiera 2 argumenty i zwraca wartość typu 3 argumentu

        BinaryOperator<Martin> binaryOperator = (martin, martin2) -> new Martin(); // 2 arg
        binaryOperator.apply(new Martin(), new Martin()); // pobiera 2 argumenty typu generycznego i zwraca obiekt też tego typu

        System.out.println(BinaryOperator.minBy(Integer::compareTo).apply(20, 1));

        IntBinaryOperator suma = (left, right) -> left + right;
        System.out.println(suma.applyAsInt(5, 10));

        ToIntFunction<Martino> toIntFunction = value -> value.age;
        System.out.println(toIntFunction.applyAsInt(new Martino()));
    }
}

class Martin {
    String name = "Martin";
    String surname = "Parat";
}

class Martino extends Martin {
    int age = 29;
}
