package patterns.singleton;

import java.io.Serializable;

public class Singleton implements Serializable {

    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }

    protected Object readResolve() {
        return getInstance();
    }
}

class Gowno {

    public static void main(String[] args) {
        Singleton singleton = patterns.singleton.Singleton.getInstance();
        Singleton Singleton2 = patterns.singleton.Singleton.getInstance();

        System.out.println(singleton == Singleton2);
        System.out.println(singleton.equals(Singleton2));
    }
}
