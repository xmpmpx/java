package ultimate.funkcyjne;

public class StreamMethods {
    public static void main(String[] args) {

        Martini.getData().stream().filter(martini -> martini.name.equals("Marcin") && martini.age > 2).forEach(System.out::println);
        Martini.getData().stream().filter(martini -> martini.name.equals("Marcin")).filter(martini -> martini.age > 2).forEach(System.out::println);

        Martini.getData().stream().map(martini -> martini.name = "ON").forEach(System.out::println); //ON
        Martini.getData().stream().map(martini -> martini.name).forEach(System.out::println);

        Martini.getData().forEach(System.out::print);
        System.out.println();
        Martini.getData().stream().forEach(System.out::print);
        System.out.println();

        Martini.getData().stream().map(martini -> martini.name).findFirst().ifPresent(System.out::println); // 1-wszy el. ze strumienia
        boolean anyTomek = Martini.getData().stream().map(martini -> martini.name).anyMatch(s -> s.equals("Tomek")); // jakikolwiek
        boolean anyOver10 = Martini.getData().stream().map(martini -> martini.age).allMatch(s -> s > 10); // wszystkie
        boolean noneOver60 = Martini.getData().stream().map(martini -> martini.age).noneMatch(s -> s > 60); // żaden

    }
}
