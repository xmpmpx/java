package ultimate.wielowatkowosc;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class CompletableFutureNieblokujacy {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //Obie metody przyjmuja jako drugi parametr Executor.

        Runnable runnable = () -> {
            System.out.println("Wątek: " + Thread.currentThread().getName());
            Scanner scanner = new Scanner(System.in);
            String key;
            do {
                key = scanner.nextLine();
                System.out.println(key);
            } while (!key.equals("end"));
            System.err.println("KONIEC");
        };

        CompletableFuture.runAsync(runnable, executor); //przyjmuje Runnable, nic nie zwraca

        //--------------------------------------------------------------------------
        Supplier<Integer> supplier = () -> {
            System.out.println("Wątek: " + Thread.currentThread().getName());
            return 10;
        };

        CompletableFuture.supplyAsync(supplier, executor)
                .thenApply(r -> {
                    try {
                        for (int i = 0; i < 10; i++) {
                            System.out.println(i);
                            TimeUnit.SECONDS.sleep(2);
                        }
                    } catch (InterruptedException e) {
                        e.getMessage();
                    }
                    System.out.println("Dodać 2: " + Thread.currentThread().getName());
                    return r + 2;
                }).thenApply(r -> r + 1)
                .thenAccept(System.out::println);

        System.out.println("NIE BLOKUJE!");

        executor.shutdown();
    }
}
