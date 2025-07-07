package tasks.counter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class EndedCallService {

    private final ConcurrentHashMap<String, AtomicInteger> counterMap = new ConcurrentHashMap<>();

    public void addEndCall(String clientName) {
        counterMap.computeIfAbsent(clientName, s -> new AtomicInteger(0)).getAndIncrement();
    }

    public int getEndCallCount(String clientName) {
        return counterMap.getOrDefault(clientName, new AtomicInteger(0)).get();
    }
}
