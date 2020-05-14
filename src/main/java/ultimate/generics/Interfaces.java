package ultimate.generics;

interface Interfejs<E> {

    void metoda(E argument);
}

// public class Interfaces implements Interfejs<String>, Interfejs<Number> - nie można bo wymazywanie typów

public class Interfaces implements Interfejs<String>, Comparable<Interfaces> {

    @Override
    public void metoda(String argument) {

    }

    @Override
    public int compareTo(Interfaces o) {
        return 0;
    }
}

// class Klasa extends Interfaces implements Comparable<Klasa> - nie można bo już zaimplementowany wyżej
class Klasa extends Interfaces {


    @Override
    public int compareTo(Interfaces o) { // tylko na podstawie klasy bazowej
        return super.compareTo(o);
    }
}

// -----------------------------------------------------------------------------------------------------------------

interface Interfejs1<E>{

    void metoda1(E arg); // po zmianie nazwy: metoda1
}


interface Interfejs2<E>{

    void metoda(E arg);
}

// class ImplementujOba implements Interfejs1<String>, Interfejs2<Number> - nie można gdyż typy zostaną wymazane i
// dalej będą metody o tym samej nazwie - należy zmienić nazwy metod
class ImplementujOba implements Interfejs1<String>, Interfejs2<Number> {

    @Override
    public void metoda1(String arg) {

    }

    @Override
    public void metoda(Number arg) {

    }
}

// Lub poprzez trik z ograniczeniem - po wymazaniu dwa różne typy
// -----------------------------------------------------------------------------------------------------------------

interface Interfejs3<E extends Number>{

    void metoda(E arg); // po zmianie nazwy: metoda1
}


interface Interfejs4<E>{

    void metoda(E arg);
}

class ImplementujObaInne implements Interfejs3<Number>, Interfejs4<String> {

    @Override
    public void metoda(Number arg) { // po wymazaniu Number

    }

    @Override
    public void metoda(String arg) { // po wymazaniu Object

    }
}


