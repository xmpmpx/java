package ultimate.wielowatkowosc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureBledy {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                throw new IndexOutOfBoundsException();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 32L;
        }, executorService).exceptionally(e -> {
            System.out.println("Error!");
            System.out.println(e.getLocalizedMessage());
            return 5L;
        }).thenApply(r -> r + 2).thenApply(r -> r + 5).thenAccept(System.out::println);

        executorService.shutdown();
    }
}
