package ultimate.wielowatkowosc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExecutor {

    public static void main(String[] args) {
        System.out.println("Główny wątek: " + Thread.currentThread().getName());

        //ExecutorService - service pomagający nam zarządzenie dużą ilością wątków
        //automtycznie zarządza wątkami - trzyma runnable w puli i je dodaje do wcześniej utworzonych przez Executor wątków
        //kiedy wątek jest długo nieużywany - wyłącza go zarządzając tym samym pamięcia JVM.
        ExecutorService executorService = Executors.newFixedThreadPool(2); //określona z góry liczba wątków

        Runnable runnable1 = () -> {
            try {
                System.out.println("Wątek: " + Thread.currentThread().getName());
                System.out.println("Pracuję");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable runnable2 = () -> {
            try {
                System.out.println("Wątek: " + Thread.currentThread().getName());
                System.out.println("Gotuję");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable runnable3 = () -> {
            try {
                System.out.println("Wątek: " + Thread.currentThread().getName());
                System.out.println("Modlę się");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };


        executorService.submit(runnable1);
        executorService.submit(runnable2);
        executorService.submit(runnable3);

        executorService.shutdown(); //czeka na zakończenie
    }
}
