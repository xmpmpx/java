package algorytmy;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Fibonacci {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj liczbę: ");
        int number = scanner.nextInt();

       /* AtomicInteger operations = new AtomicInteger(0);
        System.out.println("Wynik: " + fibonacci(number, operations));
        System.out.println("Operations: " + operations);*/

   /*     AtomicInteger operations = new AtomicInteger(0);
        Map<Integer, Integer> mnemonic = new HashMap();
        System.out.println("Wynik: " + fibonacci_up_bottom(number, mnemonic, operations));
        System.out.println("Operations: " + operations);*/

        AtomicInteger operations = new AtomicInteger(0);
        System.out.println("Wynik: " + fibonacci_bottom_up(number, operations));
        System.out.println("Operations: " + operations);
    }

    private static int fibonacci_bottom_up(int number, AtomicInteger operations) {
        System.out.println("N: " + number);
        int[] arr = new int[number + 1];
        for (int i = 0; i <= number; i++) {
            operations.getAndIncrement();
            if (i == 0) {
                arr[i] = 0;
            } else if (i <= 2) {
                arr[i] = 1;
            } else {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
        }
        return arr[number];
    }

    private static int fibonacci_up_bottom(int number, Map<Integer, Integer> mnemonic, AtomicInteger operations) {
        System.out.println("N: " + number);
        operations.getAndIncrement();
        if (number == 0) {
            return 0;
        } else if (number <= 2) {
            return 1;
        } else if (mnemonic.containsKey(number)) {
            return mnemonic.get(number);
        }
        int result = fibonacci_up_bottom(number - 1, mnemonic, operations) +
                fibonacci_up_bottom(number - 2, mnemonic, operations);
        mnemonic.put(number, result);
        return result;
    }

    private static int fibonacci(int number, AtomicInteger operations) {
        System.out.println("N: " + number);
        operations.getAndIncrement();
        if (number == 0) {
            return 0;
        } else if (number <= 2) {
            return 1;
        } else {
            return fibonacci(number - 1, operations) + fibonacci(number - 2, operations);
        }
    }
}
