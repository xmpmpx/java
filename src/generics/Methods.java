package generics;

public class Methods {

    public static void main(String[] args) {
        Float liczba = 2.2F;
        Double liczba2 = 3.4;
        String text = "Text";

        Float aFloat = printAndReturn(liczba);
        Double aDouble = printAndReturn(liczba2);
        String aString = printAndReturn(text);
    }

    public static <T> T printAndReturn(T object) {
        System.out.println(object);
        return object;
    }
}
