package ultimate.funkcyjne;

import java.util.Optional;

public class OptionalEx {

    public static void main(String[] args) {

        Martini martini = getMartini();

        Optional<Martini> ofNullable = Optional.ofNullable(martini); //gdy nie wiadomo czy null

        if (ofNullable.isPresent()) {
            System.out.println(ofNullable.get().name);
        }

        ofNullable.ifPresent(mar -> System.out.println(mar.name));
        ofNullable.orElse(new Martini()); // zwróc wartość, a gdy jej nie ma to zwróc nową inną
        ofNullable.orElseGet(Martini::new); // zwróc wartość, a gdy jej nie ma to zwróc nową inną na podstawie suppliera
        ofNullable.filter(martini1 -> martini1.name.equals("Martin")).ifPresent(System.out::println);
        ofNullable.map(martini1 -> martini1.name);

        martini = new Martini();
        Optional<Martini> of = Optional.of(martini); //gdy wiadomo, że NIE null
        Optional<Martini> empty = Optional.empty(); //pusty Optional bez żadnej wartości
    }

    public static Martini getMartini() {
        int v = (int) (Math.random() * 10 + 1);
        return v > 5 ? new Martini() : null;
    }
}

class Martini {
    String name = "Martin";
}
