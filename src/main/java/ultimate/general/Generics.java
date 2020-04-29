package ultimate.general;

import java.util.ArrayList;
import java.util.Arrays;

public class Generics {

    public static void main(String[] args) {

        ArrayList<A> Alista = new ArrayList<>(Arrays.asList(new A(), new A()));
        ArrayList<B> Blista = new ArrayList<>(Arrays.asList(new B(), new B()));
        ArrayList<C> Clista = new ArrayList<>(Arrays.asList(new C(), new C()));

        A element = getElement(Alista);
        B element1 = getElement(Blista);
        C element2 = getElement(Clista);

        Object element11 = getElement1(Alista);
        Object element12 = getElement1(Blista);
        Object element13 = getElement1(Clista);

        // getElementDol(Alista); - bląd
        B elementDol = getElementDol(Blista);
        C elementDol1 = getElementDol(Clista);
        // getElementDol1(Alista); - błąd
        B elementDol11 = getElementDol1(Blista);
        B elementDol12 = getElementDol1(Clista);

        Object elementGora1 = getElementGora1(Alista);
        Object elementGora11 = getElementGora1(Blista);
        // getElementGora1(Clista); - błąd
    }

    // bez zakresu
    public static <T> T getElement(ArrayList<T> lista) {
        T a = lista.get(0);
        return a;
    }

    public static Object getElement1(ArrayList<?> lista) {
        Object o = lista.get(0);
        return o;
    }

    // zakres w dół
    public static <T extends B> T getElementDol(ArrayList<T> lista) {
        T AlubB = lista.get(0);
        return AlubB;
    }

    public static B getElementDol1(ArrayList<? extends B> lista) {
        B b = lista.get(0); // tylko B lub instanceof i C
        return b;
    }

    //zakres w górę - nie można z typem generycznym
    /*public static <T super B> T getElementGora(ArrayList<T> lista){
        T a = lista.get(0);
        return a;
    }*/

    public static Object getElementGora1(ArrayList<? super B> lista) {
        Object o = lista.get(0);
        return o;
    }
}

class A {

}

class B extends A {

}

class C extends B {

}
