package ultimate.wielowatkowosc;

public class RunnableExample {
    public static void main(String[] args) {
        System.out.println("Glówny wątek: " + Thread.currentThread().getName());
        Thread myRunnable = new Thread(new MyRunnable(), "MyRunnable");
        myRunnable.start();

        MyThread watek_thread = new MyThread("Watek Thread");
        watek_thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Super Anonimowy Wątek");
            }
        }).start();

        Runnable super_anonimowy_wątek_lambda = () -> System.out.println("Super Anonimowy Wątek Lambda");
        new Thread(super_anonimowy_wątek_lambda).start();
    }
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Wątek: " + Thread.currentThread().getName());
    }
}

//Runnable lepsze, niż Thread
//Możliwość dziedziczenia
//Deklaracja w jednej linii w JAVA 8