package ultimate.funkcyjne;

public class StreamMethods {
    public static void main(String[] args) {

        Martini.getData().stream().filter(martini -> martini.name.equals("Marcin") && martini.age > 2).forEach(System.out::println);
        Martini.getData().stream().filter(martini -> martini.name.equals("Marcin")).filter(martini -> martini.age > 2).forEach(System.out::println);

        Martini.getData().stream().map(martini -> martini.name = "ON").forEach(System.out::println); //ON
        Martini.getData().stream().map(martini -> martini.name).forEach(System.out::println); //ON

        Martini.getData().forEach(System.out::print);
        System.out.println();
        Martini.getData().stream().forEach(System.out::print);


    }
}
