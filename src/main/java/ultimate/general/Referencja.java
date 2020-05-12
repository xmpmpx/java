package ultimate.general;

import java.util.ArrayList;

public class Referencja {
    public static void main(String[] args) {

        Inter in = Math::random;
        Inter2 in2 = Referencja::new;

        ArrayList<Integer> list = new ArrayList<>();
        Inter3 in3 = list::size;
    }
}

interface Inter {
    void metoda();
}

interface Inter2 {
    Referencja metoda();
}

interface Inter3 {
    int metoda();
}
