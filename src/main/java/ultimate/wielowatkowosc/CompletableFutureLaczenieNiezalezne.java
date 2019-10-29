package ultimate.wielowatkowosc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureLaczenieNiezalezne {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<Long> firstFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 32L;
        }, executorService);

        CompletableFuture<Long> secondFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 12L;
        }, executorService);

        //firstFuture.thenCompose(aLong -> CompletableFuture.supplyAsync(() -> aLong + 12L)).thenAccept(System.out::println);

        firstFuture.thenCombine(secondFuture, (aLong, aLong2) -> aLong + aLong2).thenAccept(System.out::println);

        executorService.shutdown();
    }

}
