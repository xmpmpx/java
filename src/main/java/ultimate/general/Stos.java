package ultimate.general;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stos<T> {

    private List<T> list;

    public Stos(int size) {
        list = new ArrayList<>(size);
    }

    public void add(T e) {
        if (list.size() == 3) {
            System.err.println("Stos pełny!");
        } else {
            list.add(e);
            System.out.println("Dodano: " + e);
            System.out.println(list);
        }
    }

    public void get() {
        if (list.size() == 0) {
            System.err.println("Stos pusty!");
        } else {
            T e = list.get(list.size() - 1);
            list.remove(e);
            System.out.println("Pobrano: " + e);
            System.out.println(list);
        }
    }
}

class Test {
    public static void main(String[] args) {
        Stos<Integer> stos = new Stos<>(3);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.startsWith("A")) {
                stos.add(Integer.parseInt(line.substring(2)));
            } else if (line.startsWith("G")) {
                stos.get();
            } else {
                System.err.println("Niepoprawna instrukcja!");
            }
        }
    }
}