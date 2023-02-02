package ultimate.wielowatkowosc;

import java.util.Map;
import java.util.concurrent.*;

public class ConcurrentHashMapExmaple {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Map<String, Integer> map = new ConcurrentHashMap<>();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> add = executorService.submit(new AddMap(map));
        System.out.println(add.get());
        executorService.submit(new GetMap(map));
        executorService.shutdown();
        System.out.println(executorService.awaitTermination(10, TimeUnit.SECONDS));
    }
}

class AddMap implements Callable<String> {

    private final Map<String, Integer> map;

    public AddMap(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public String call() {
        try {
            Thread.sleep(3000);
            map.put("Marcin", 30000);
            map.put("Jarek", 3500);
            map.put("Tomek", 4000);
            map.put("Czesio", 2800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Completed";
    }
}

class GetMap implements Runnable {

    private final Map<String, Integer> map;

    public GetMap(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(map.get("Marcin"));
            System.out.println(map.get("Czesio"));
            Thread.sleep(4000);
            System.out.println(map.get("Jarek"));
            System.out.println(map.get("Tomek"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}