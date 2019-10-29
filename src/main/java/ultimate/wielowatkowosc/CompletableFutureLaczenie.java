package ultimate.wielowatkowosc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CompletableFutureLaczenie {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<Integer> firstFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Double res = Math.random() * 10 + 1;
            return res.intValue();
        }, executorService);

        firstFuture.thenCompose(userId -> CompletableFuture.supplyAsync(() -> getDiscount(userId)).thenAccept(System.out::println));

        executorService.shutdown();

    }

    public static Integer getDiscount(Integer userId) {
        int CONST = 100;
        switch (userId) {
            case 1:
                return userId + CONST;
            case 2:
                return userId + CONST;
            case 3:
                return userId + CONST;
            case 4:
                return userId + CONST;
            case 5:
                return userId + CONST;
            default:
                return userId;
        }
    }
}
