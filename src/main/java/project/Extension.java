package project;

public class Extension {
    public static void main(String[] args) {
        Ksiadz ksiadz = new Ksiadz();
        Papiez papiez1 = new Papiez();
        papiez1.spowiadaj();
        System.out.println(Ksiadz.common);
        System.out.println(Papiez.common);
        Papiez papiez = new Papiez("ws", 2);
        System.out.println(papiez.imie);
        System.out.println(papiez.lataPoslugi);
    }
}

class Ksiadz {
    static int common = 100;
    final String imie;
    int lataPoslugi;

    public Ksiadz(String imie, int lataPoslugi) {
        this.imie = imie;
        this.lataPoslugi = lataPoslugi;
    }

    public Ksiadz(){
        this.imie = "S";
    }

    public void spowiadaj() {

    }
}

class Papiez extends Ksiadz {
    //int common = 12;
    public Papiez(String imie, int lataPoslugi) {
        super("Marcin", 22);
    }

    public Papiez(){
        //this.imie = "S";
        //common = 101;
    }

    @Override
    public void spowiadaj() {
        //super.spowiadaj();
        //common = 101;
    }

    public void pielgrzymuj() {

    }
}