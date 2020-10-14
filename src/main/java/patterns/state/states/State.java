package patterns.state.states;

import patterns.state.Automat;

public interface State {

    void włóżMonetę(Automat automat);

    void pociągnijDźwignię(Automat automat);

    void zabierzWygraną(Automat automat);
}
