package ultimate.wielowatkowosc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutor {

    public static void main(String[] args) {

        Runnable runnable1 = () -> {
            System.out.println("Wątek: " + Thread.currentThread().getName());
            try {
                for (int i = 0; i <= 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " | " + i);
                    TimeUnit.MILLISECONDS.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        //executor.schedule(runnable1, 5, TimeUnit.SECONDS);
        //executor.schedule(runnable1, 7, TimeUnit.SECONDS); - dobre, ale wykonanie z opóżnieniem tylko raz

        executor.scheduleAtFixedRate(runnable1, 1, 6, TimeUnit.SECONDS); //co jakiś interwał czasu, działą cały czas, nie może być shutdown
        executor.scheduleAtFixedRate(runnable1, 3, 6, TimeUnit.SECONDS); //co jakiś interwał czasu, działą cały czas, nie może być shutdown

        //executor.shutdown();
    }
}
