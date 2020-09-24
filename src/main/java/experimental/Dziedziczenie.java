package experimental;

public class Dziedziczenie {

    public static void main(String[] args) {
        First second = new Second();
        second.wypisz();
        System.out.println(Second.PI);
        System.out.println(second.ABC);
        second.printuj();
    }
}

class First {
    public static double PI = 3.14;
    public double ABC = 3.14;

    public static void wypisz() {
        System.out.println(PI);
    }

    public void printuj() {
        System.out.println(ABC);
    }
}

class Second extends First {
    public static double PI = 777;
    public double ABC = 777;

    public void printuj() {
        System.out.println(ABC);
    }
}
