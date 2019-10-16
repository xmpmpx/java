package project;

public final class FinalClass {

}

// class Klasa extends FinalClass - nie można dziedziczyć

class PrywatnyKonstruktor {

    private String obj;

    public PrywatnyKonstruktor(String obj){
        this.obj = obj;
    }

    private PrywatnyKonstruktor() {
        System.out.println("Klasa");
    }

    public static class Klasa extends PrywatnyKonstruktor{
        Klasa() {
            super();
            System.out.println("PrywatnyKonstruktor i Klasa!");
        }

        public Klasa(String obj) {
            super(obj);
        }
    }

    public class Klasa2 extends PrywatnyKonstruktor{
        Klasa2() {
            super();
            System.out.println("PrywatnyKonstruktor i Klasa2!");
        }

        public Klasa2(String obj) {
            super(obj);
        }
    }
}

class Testowa{
    public static void main(String[] args){
        PrywatnyKonstruktor.Klasa klasa = new PrywatnyKonstruktor.Klasa();
        //PrywatnyKonstruktor prywatnyKonstruktor = new PrywatnyKonstruktor();
        //PrywatnyKonstruktor.Klasa2 klasa2 = prywatnyKonstruktor.new Klasa2();
        // tak by było wywołanie klasy wewnętrznej niestatycznej gdyby konstruktor był nie prywatny
    }
}

// class Klasa extends PrywatnyKonstruktor - nie można dziedziczyć