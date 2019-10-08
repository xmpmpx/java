package ultimate.wielowatkowosc;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InvokeAllAny {

    public static void main(String[] args) {

        Callable<Integer> callable1 = () -> {
            TimeUnit.SECONDS.sleep(1);
            return 1;
        };

        Callable<Integer> callable2 = () -> {
            TimeUnit.SECONDS.sleep(2);
            return 2;
        };

        Callable<Integer> callable3 = () -> {
            TimeUnit.SECONDS.sleep(3);
            return 3;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        try {
            Integer fastestResult = executorService.invokeAny(List.of(callable1, callable2, callable3));
            //zwróci pierwszy zakończony rezultat, reszte pomijając
            System.out.println(fastestResult);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            List<Future<Integer>> results = executorService.invokeAll(List.of(callable1, callable2, callable3));
            for (Future<Integer> result : results) {
                System.out.println(result.get()); //zwróci wszystkie rezultaty dopiero gdy wszystkie wątki zostaną zakończone
            }
        } catch (InterruptedException e) {
            e.getMessage();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}
