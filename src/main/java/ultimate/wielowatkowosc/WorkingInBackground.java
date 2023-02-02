package ultimate.wielowatkowosc;

import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkingInBackground {

    public static void main(String[] args) {

        Timer licznik = new Timer("Licznik", true);
        licznik.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Work in progress!");
            }
        }, 1, 3000);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            BigInteger silnia = BigInteger.ONE;
            for (int i = 2; i <= 200000; i++) {
                silnia = silnia.multiply(new BigInteger(String.valueOf(i)));
                if (i % 10000 == 0) {
                    System.out.println(i);
                }
            }
            return silnia;
        }, executorService).thenAccept(result -> {
            System.err.println(result);
            licznik.cancel();
        });

        while (!voidCompletableFuture.isDone()) {
            try {
                System.out.println("Main");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        try {
            System.out.println(executorService.awaitTermination(30, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
