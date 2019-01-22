package generics;

import java.util.ArrayList;

public class Inheritence {

    public void metoda(){}

    public static void main(String[] args) {
        Rozszerzacz<String> roz = new Rozszerzacz<>();
        roz.metoda();

        Rozszerzacz2<String> roz2 = new Rozszerzacz2<>();
        roz2.size();
        roz2.rozszerzenie();

        Rozszerzacz3<Integer> roz3 = new Rozszerzacz3<>();
        String s = roz3.get(0); // i tak String
        // roz3.add(4); - nie można bo nie String

        Rozszerzacz4 roz4 = new Rozszerzacz4();
        String s1 = roz4.get(0); // i tak String
        // roz4.add(4); - nie można bo nie String
    }
}

// Nie można dziedziczyć po typie sparametryzowanym
// class Rozszerzacz<T> extends T

class Rozszerzacz<T> extends Inheritence {
    public void rozszerzenie() {
        System.out.println("Rozszerzenie klasy");
        metoda();
    }
}

// Nie można rozszerzyć klasy sparametryzowanej nieznanym typem klasą niesparametryzowaną
// class Rozszerzacz2 extends ArrayList<T> {

class Rozszerzacz2<T> extends ArrayList<T> {
    public void rozszerzenie() {
        System.out.println("Rozszerzenie klasy");
    }
}

// Można
class Rozszerzacz3<T> extends ArrayList<String> {

}

// Można, ale klasa nieparametryzowana
class Rozszerzacz4 extends ArrayList<String> {
    public void rozszerzenie() {
        System.out.println("Rozszerzenie klasy");
    }
}