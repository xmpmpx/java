package patterns.state;

import patterns.state.states.BrakMonety;
import patterns.state.states.State;

public class Automat {

    public State state;

    public Automat() {
        state = new BrakMonety();
    }

    public void włóżMonetę(Automat automat) {
        state.włóżMonetę(this);
    }

    public void pociągnijDźwignię(Automat automat) {
        state.pociągnijDźwignię(this);
    }

    public void zabierzWygraną(Automat automat) {
        state.zabierzWygraną(this);
    }

    public void wypiszStan() {
        System.out.println(this.state.getClass().getSimpleName());
    }
}
