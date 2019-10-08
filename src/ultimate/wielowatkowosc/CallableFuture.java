package ultimate.wielowatkowosc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CallableFuture {

    public static void main(String[] args) {
        //Runnable nie zwraca wyniku, Callable zwraca wynik o podanym typie
        Callable<Integer> callable = () -> {
            System.out.println("Name: " + Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
            return 10;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> result = executorService.submit(callable);
        try {
            System.out.println(result.get()); //blokuje główny wątek dopóki otrzyma odpowiedź
            //jest metoda future.isDone, którą można użyć w pęli while i robić w niej dodatkowe rzeczy
            //jest i future.get(delay, TimeUnit), który rzuca wyjątek jeśli nie ma rezultatu w określonym czasie
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("After the RS.");
        executorService.shutdown();
    }
}
