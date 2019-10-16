package generics;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

public class Restrictions {

    public static void main(String[] args) {

        ArrayList<Integer> listaInteger = new ArrayList<>();
        ArrayList<String> listaString = new ArrayList<>();

        //Ograniczenie górne
        System.out.println(rozmiarListy(listaInteger)); // można gdyż rozszerza Number
        // System.out.println(rozmiarListy(listaString)); // nie można gdyż NIE rozszerza Number


        ArrayList<Writer> listaWriter = new ArrayList<>();
        ArrayList<Float> listaFloat = new ArrayList<>();

        //Ograniczenie dolne
        System.out.println(rozmiarListy2(listaWriter)); // można gdyż typ nadrzędny PrintWriter
        // System.out.println(rozmiarListy2(listaFloat)); // nie można gdyż Float to nie typ nadrzędny typu PrintWriter
    }

    public static int rozmiarListy(ArrayList<? extends Number> lista) { // tylko typy podrzędne typu Number
        Number number = lista.get(0); // Nie musi być Object, może być już typ nadrzędny
        // lista.add(5); //Też nic nie można dodać oprócz Null
        return lista.size();
    }

    public static int rozmiarListy2(ArrayList<? super PrintWriter> lista) { // tylko typy nadrzędne typu PrintWriter
        Object object = lista.get(0); // Object bo nie wiemy jaki typ
        //lista.add(new PrintWriter(Writer.nullWriter())); // Można dodać wartość typu
        return lista.size();
    }
}
