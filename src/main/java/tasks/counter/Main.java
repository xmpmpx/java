package tasks.counter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class Main {

    //    We are building simple spring boot based microservice.//
    //    We have an endpoint that is responsible for creating client's ended calls.////
    //    Create a service that is called from the rest controller,//
    //    that counts client’s ended calls.//
    //    Each call should result in updating the counter by one.//
    //    Values should be stored in-memory.//*/

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Endpoint endpoint = new Endpoint(new EndedCallService());

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<?> submit = executorService.submit(() -> {
            IntStream.range(1, 11).forEach(value -> endpoint.addEndCall("Marcin"));
            IntStream.range(1, 11).forEach(value -> endpoint.addEndCall("Tomek"));
            return endpoint.getEndCallCount("Marcin");
        });
        Future<?> submit1 = executorService.submit(() -> {
            IntStream.range(1, 11).forEach(value -> endpoint.addEndCall("Marcin"));
            IntStream.range(1, 11).forEach(value -> endpoint.addEndCall("Tomek"));
        });

        submit.get();
        submit1.get();
        executorService.shutdown();

        System.out.println(endpoint.getEndCallCount("Marcin"));
        System.out.println(endpoint.getEndCallCount("Tomek"));
    }
}
