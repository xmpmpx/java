package ultimate.funkcyjne;

import ultimate.funkcyjne.interfaces.One;
import ultimate.funkcyjne.interfaces.Two;

import java.util.concurrent.Callable;

public class Lambda {

    public static void main(String[] args) {

        Callable<Integer> callExt = new Callable<>() {
            @Override
            public Integer call() throws Exception {
                return 0;
            }
        };

        Callable<Integer> call = () -> 0;

        One oneExt = new One() {
            @Override
            public int dodaj(int a) {
                return 5;
            }
        };

        One one = (int a) -> 5;
        One one1 = a -> 5;
        One one2 = a -> {
            System.out.println("Test");
            return 5;
        };

        Two twoExt = new Two() {
            @Override
            public int dodaj(int a, int b) {
                return 0;
            }
        };

        Two two = (int a, int b) -> 10;
        Two two1 = (a, b) -> 10;
        Two two2 = (a, b) -> {
            System.out.println(10);
            return 10;
        };

        testOne("Name", oneExt);
        testOne("Name", one);
        testOne("Name", a -> 5);
        testOne("Name", a -> {
            System.out.println("Test");
            return 0;
        });

        testTwo("Name", twoExt);
        testTwo("Name", two);
        testTwo("Name", (a, b) -> 0);
        testTwo("Name", (a, b) -> {
            System.out.println("Test");
            return a + b;
        });

    }

    public static void testOne(String name, One arg) {

    }

    public static void testTwo(String name, Two arg) {

    }
}
