package patterns.state;

public class StateMain {

    public static void main(String[] args) {
        Automat automat = new Automat();

        automat.włóżMonetę(automat);
        automat.wypiszStan();
        automat.pociągnijDźwignię(automat);
        automat.wypiszStan();
        automat.zabierzWygraną(automat);

        System.out.println("-----------");
        automat.pociągnijDźwignię(automat);
        automat.zabierzWygraną(automat);

        System.out.println("-----------");
        automat.włóżMonetę(automat);
        automat.zabierzWygraną(automat);
    }
}
