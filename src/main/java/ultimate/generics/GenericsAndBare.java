package ultimate.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericsAndBare {
    public static void main(String[] args) {

        List kolekcjaNiegeneryczna = new ArrayList();
        kolekcjaNiegeneryczna.add(4);
        kolekcjaNiegeneryczna.add("String");

        for (Object el : kolekcjaNiegeneryczna) { // tylko Object
            // niebezpieczne
        }

        List<String> kolekcja = new ArrayList<>();
        List<String> kolekcja2 = Collections.checkedList(new ArrayList<String>(), String.class); // poda miejsce gdzie
        // tak można szukać błędu, ale nie używać na co dzień

        niebezpieczna(kolekcja);

        for (String el : kolekcja) { // error w runtime bo rzutowanie na String, brak info gdzie

        }
    }

    public static void niebezpieczna(List list) {
        list.add(5); // źle, choć jeszcze nie error
    }
}

class Lis implements Comparable<Lis> {
    private Integer dlugoscOgona;

    public Lis(Integer dlugoscOgona) {
        this.dlugoscOgona = dlugoscOgona;
    }

    public static void main(String[] args) {
        Comparable lis1 = new Lis(1); // Bare Comparable
        Lis lis2 = new Lis(2);
        String lis3 = "Lis3";

        System.out.println(lis1);
        System.out.println(lis2);
        System.out.println(lis1.compareTo(lis2));
        System.out.println(lis1.compareTo(lis3)); // error w runtime
    }

    @Override
    public int compareTo(Lis o) {
        return dlugoscOgona.compareTo(o.dlugoscOgona);
    }

    @Override
    public String toString() {
        return "Lis{" +
                "dlugoscOgona=" + dlugoscOgona +
                '}';
    }
}