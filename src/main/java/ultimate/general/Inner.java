package ultimate.general;

public class Inner {
    public static void main(String[] args) {
        Komputer komputer = new Komputer(1);
        komputer.uruchom();

        //idiotyzm skrajny
        Komputer.Speakers speakers = new Komputer(1).new Speakers(); //z nawiasami
        Komputer.Mouse mouse = new Komputer.Mouse(); // bez nawiasów
    }
}

class Komputer {
    private int user;
    private static int age;

    public Komputer(int user) {
        this.user = user;
    }

    public void uruchom() {
        System.out.println("User: " + user);
        Karta karta = new Karta();
        karta.polacz();
    }

    //klasa tylko na użytek klasy Komputer
    private class Karta {
        public void polacz() {
            System.out.println("Connected: " + user); //nawet pola prywatne z klasy bazowej
        }
    }

    //klasa tylko na użytek klasy Komputer
    private static class Keyboard {
        public void polacz() {
            System.out.println("Connected: " + age); //nawet pola prywatne z klasy bazowej - tylko statyczne!
        }
    }

    //klasa wewnętrzna nie powinna być publiczna!
    public class Speakers {
        public void play() {
            System.out.println("Playing");
        }
    }

    //klasa wewnętrzna nie powinna być publiczna!
    public static class Mouse {
        public void click() {
            System.out.println("Clicking");
        }
    }
}
