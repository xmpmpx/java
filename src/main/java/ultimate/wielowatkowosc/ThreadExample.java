package ultimate.wielowatkowosc;

import java.util.stream.IntStream;

public class ThreadExample {

    //Proces - to program działający w komputerze
    //Każdy proces ma przydzieloną swoją pamięć.
    //Każdy proces posiada co najmniej jeden wątek - wątek główny
    //Można dodawać kolejne wątki
    //Złota zasada - najpierw program sekwencyjny, następnie dzielenie na wątki i monitorowanie

    //2 sposoby na utworzenie wątku - rozszerzenie Thread

    public static void main(String[] args) {
        System.out.println("Główny wątek: " + Thread.currentThread().getName());
        MyThread myThread = new MyThread("My Thread nr.1");
        MyThread myThread2 = new MyThread("My Thread nr.2");
        myThread.start(); //start tworzy nowy wątek, nie wywyoływać metody run, gdyż będzie ten sam jeden wątek
        myThread2.start();
    }
}

class MyThread extends Thread {

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("Mój wątek: " + Thread.currentThread().getName());

        IntStream.rangeClosed(1, 10).forEach(i -> System.out.println(i + " | Wątek: " + Thread.currentThread().getName()));
    }
}
