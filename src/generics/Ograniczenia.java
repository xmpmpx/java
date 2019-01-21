package generics;

import java.util.ArrayList;

public class Ograniczenia {

    public static void main(String[] args) {

        ArrayList<Integer> listaInteger = new ArrayList<>();
        ArrayList<String> listaString = new ArrayList<>();

        //Ograniczenie górne
        System.out.println(rozmiarListy(listaInteger)); // można gdyż rozszerza Number
        // System.out.println(rozmiarListy(listaString)); // nie można gdyż NIE rozszerza Number


        ArrayList<Number> listaNumber = new ArrayList<>();
        ArrayList<Float> listaFloat = new ArrayList<>();

        //Ograniczenie dolne
        System.out.println(rozmiarListy2(listaNumber)); // można gdyż typ nadrzędny Integer
        // System.out.println(rozmiarListy2(listaFloat)); // nie można gdyż Float to nie typ nadrzędny typu Integer
    }

    public static int rozmiarListy(ArrayList<? extends Number> lista) { // tylko typy podrzędne typu Number
        Number number = lista.get(0); // Nie musi być Object, może być już typ nadrzędny
        // lista.add(5); //Też nic nie można dodać oprócz Null
        return lista.size();
    }

    public static int rozmiarListy2(ArrayList<? super Integer> lista) { // tylko typy nadrzędne typu Integer
        Object object = lista.get(0); // Object bo nie wiemy jaki typ
        lista.add(5); // Można dodać wartość typu
        return lista.size();
    }
}
