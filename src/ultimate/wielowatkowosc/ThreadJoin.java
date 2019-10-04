package ultimate.wielowatkowosc;

import java.util.concurrent.TimeUnit;

//Join czeka na zakończenie wątku na którym jest wywoływany.
//Bez parametrów czeka do jego końca. Z parametrem czasowym czeka tylko określony
//interwał czasu i wraca do wątku głównego.
public class ThreadJoin {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " | " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Thread1");
        thread1.start();

        try {
            thread1.join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName());
        new Thread(() -> System.out.println(Thread.currentThread().getName()), "Thread2").start();
    }
}
