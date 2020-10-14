package patterns.state.states;

import patterns.state.Automat;

public class Przegrana implements State {
    @Override
    public void włóżMonetę(Automat automat) {
        System.out.println("Wrzucam monetę.");
        automat.state = new MonetaWłożona();
    }

    @Override
    public void pociągnijDźwignię(Automat automat) {
        System.out.println("Nie wygłupiaj się. Włóż monetę!");
    }

    @Override
    public void zabierzWygraną(Automat automat) {
        System.out.println("Nic nie wygrałeś!");
    }
}
