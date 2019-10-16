package ultimate.wielowatkowosc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompletableFutureExample {
    public static void main(String[] args) {
        //Obie metody przyjmuja jako drugi parametr Executor.

        Runnable runnable = () -> System.out.println("Wątek: " + Thread.currentThread().getName());

        CompletableFuture.runAsync(runnable); //przyjmuje Runnable, nic nie zwraca

        Supplier<String> stringSupplier = () -> {
            System.out.println("Wątek: " + Thread.currentThread().getName());
            return "The END";
        };

        CompletableFuture<String> result = CompletableFuture.supplyAsync(stringSupplier);//przyjmuje Supplier, zwraca wartość określonego typu
        try {
            System.out.println(result.get()); //get też blokuje
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
