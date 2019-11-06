package ultimate.wielowatkowosc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CounterAtomic counter = new CounterAtomic();

        for (int i = 0; i < 10000; i++) {
            executorService.submit(counter::increase);
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(counter.getCount());
    }
}

class CounterAtomic {
    private AtomicInteger count = new AtomicInteger(0);

    public void increase() {
        count.getAndAdd(1);
    }

    public int getCount() {
        return count.get();
    }
}


