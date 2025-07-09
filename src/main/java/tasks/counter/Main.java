package tasks.counter;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        IntStream.range(1, 1001).forEach(value -> executorService.submit(() -> {
            endpoint.addEndCall("Marcin");
            endpoint.addEndCall("Tomek");

        }));
        executorService.shutdown();
        executorService.awaitTermination(5L, TimeUnit.SECONDS);

        System.out.println(endpoint.getEndCallCount("Marcin"));
        System.out.println(endpoint.getEndCallCount("Tomek"));
    }
}
