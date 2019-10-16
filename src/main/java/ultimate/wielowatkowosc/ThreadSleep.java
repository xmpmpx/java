package ultimate.wielowatkowosc;

import java.util.concurrent.TimeUnit;

public class ThreadSleep {

    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " | " + i);
                try {
                    Thread.sleep(1000);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ThreadNEW").start();

        Thread.currentThread().setName("MainThread");
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " | " + i);
        }
    }
}
