package ultimate.wielowatkowosc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadExecutor {

    public static void main(String[] args) {
        System.out.println("Główny wątek: " + Thread.currentThread().getName());

        //ExecutorService - service pomagający nam zarządzenie dużą ilością wątków
        ExecutorService executorService = Executors.newSingleThreadExecutor(); //pojedynczy wątek

        Runnable runnable = () -> {
            try {
                System.out.println("Wątek: " + Thread.currentThread().getName());
                for (int i = 0; i <= 10; i++) {
                    System.out.println(i);
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                System.out.println("ShoudownNow EXCEPTION!");
                e.printStackTrace();
            }
        };

        executorService.submit(runnable);
        executorService.submit(runnable);

        executorService.shutdown(); //czeka na zakończenie
        //executorService.shutdownNow(); //force shutdown - rzuca InterruptedException
    }
}
