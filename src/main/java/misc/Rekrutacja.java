package misc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class Rekrutacja {

    public static void main(String[] args) throws Exception {

        texts();
        numbers();
        testBigDecimal();
        testFrequency();
    }

    private static void texts() {
        System.out.println(100 + 100 + "Text");
        System.out.println("Text" + 100 + 100);

        String java = "java";
        String javass = new String("java").intern();
        System.out.println(java == javass);
    }

    private static void numbers() {
        Integer a = 1000, b = 1000;
        System.out.println(a == b);

        Integer c = 100, d = 100;
        System.out.println(c == d);
    }

    private static void testBigDecimal() {
        double v = new BigDecimal("187.3002003").doubleValue();
        System.out.println(new BigDecimal(1.0));
        System.out.println(BigDecimal.valueOf(1.0));

        System.out.println(new BigDecimal(0.1));
        System.out.println(BigDecimal.valueOf(0.1));

        int a = 2;
        System.out.println(++a + ++a * ++a);

        int x = 127;
        byte r = (byte) x;
        System.out.println(r);
    }

    private static void testFrequency() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());
        objects.add(new Object());

        System.out.println(objects);
        System.out.println(objects.size());
        int nullsFrequency = Collections.frequency(objects, null);
        System.out.println("Validation = " + (nullsFrequency != objects.size() && nullsFrequency != 0));
    }
}
