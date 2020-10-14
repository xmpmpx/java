package patterns.state.states;

import patterns.state.Automat;

public class Wygrana implements State {
    @Override
    public void włóżMonetę(Automat automat) {
        System.out.println("Odbierz wygraną!");
    }

    @Override
    public void pociągnijDźwignię(Automat automat) {
        System.out.println("Odbierz wygraną!");
    }

    @Override
    public void zabierzWygraną(Automat automat) {
        System.out.println("Zabieram $$$! Hurra!");
        automat.state = new BrakMonety();
    }
}
