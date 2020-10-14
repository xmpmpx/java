package patterns.state.states;

import patterns.state.Automat;

import java.util.Random;

public class MonetaWłożona implements State {
    @Override
    public void włóżMonetę(Automat automat) {
        System.out.println("Moneta jest już w środku.");
    }

    @Override
    public void pociągnijDźwignię(Automat automat) {
        System.out.println("Losuję!");
        Random random = new Random();
        int los = random.nextInt(10) + 1;
        System.out.println("Los: " + los);
        if (los <= 2) {
            System.out.println("Wygrałeś!");
            automat.state = new Wygrana();
        } else {
            System.out.println("No niestety! Nie tym razem.");
            automat.state = new Przegrana();
        }
    }

    @Override
    public void zabierzWygraną(Automat automat) {
        System.out.println("Nie wygłupiaj się. Pociągnij za dźwignię!");
    }
}
