package ultimate.wielowatkowosc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RaceCondition {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Counter counter = new Counter();

        for (int i = 0; i < 10000; i++) {
            executorService.submit(counter::increase);
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(counter.getCount());
    }
}

class Counter {
    private int count;

    public synchronized void increase() {
        System.out.println(1);
        count++;
        System.out.println(2);
    }

    public int getCount() {
        return count;
    }
}
