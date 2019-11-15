package ultimate.funkcyjne;

import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        Random random = new Random();
        int v = (random.nextInt(2));
        return v == 0 ? new Martini() : null;
    }
}

class Martini {
    String name = "Martin";
    String surnmae = "Parat";
    Integer age = 29;

    public Martini() {
    }

    public Martini(String name, String surnmae, Integer age) {
        this.name = name;
        this.surnmae = surnmae;
        this.age = age;
    }

    public static List<Martini> getData() {
        return List.of(new Martini("Marcin", "Parat", 29),
                new Martini("Tomek", "Sliwa", 27),
                new Martini("Jarek", "Stawarz", 25),
                new Martini("Jakiś", "Kmiot", 25));
    }

    @Override
    public String toString() {
        return "Martini{" +
                "name='" + name + '\'' +
                ", surnmae='" + surnmae + '\'' +
                ", age=" + age +
                '}';
    }
}
