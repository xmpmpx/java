package tasks.counter;

public class Endpoint {

    private final EndedCallService endedCallService;

    public Endpoint(EndedCallService endedCallService) {
        this.endedCallService = endedCallService;
    }

    public void addEndCall(String clientName) {
        endedCallService.addEndCall(clientName);
    }

    public int getEndCallCount(String clientName) {
        return endedCallService.getEndCallCount(clientName);
    }
}
