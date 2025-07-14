package tasks.duplikaty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        int[] numbers = {1, 2, 3, 4, 2, 3};

        findDiplicatesStream(numbers);
        findDiplicatesArray(numbers);
        findDiplicatesSet(numbers);
    }

    private static void findDiplicatesSet(int[] numbers) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (!set.add(number)) {
                System.out.println(number);
            }
        }
    }

    private static void findDiplicatesArray(int[] numbers) {
        Arrays.sort(numbers);
        for (int i = 1; i < numbers.length; i++) {
            int number = numbers[i];
            if (number == numbers[i - 1]) {
                System.out.println(number);
            }
        }
    }

    private static void findDiplicatesStream(int[] numbers) {

        Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .forEach(System.out::println);

    }
}
