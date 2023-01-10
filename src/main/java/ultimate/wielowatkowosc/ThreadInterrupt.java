package ultimate.wielowatkowosc;

public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new LoopInterface());
        thread.start();
        thread.setName("LoopInterface");
        Thread.sleep(3000);
        thread.interrupt();
    }
}


class LoopInterface implements Runnable {

    @Override
    public void run() {

        System.out.println("Start: " + Thread.currentThread().getName());
        int i = 0;
        while (true) {
            System.out.println(i++);
            if (Thread.currentThread().isInterrupted()) {
                return;
            }
        }
    }
}