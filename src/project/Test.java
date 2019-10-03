package project;

import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class Test {
    public static void main(String[] args) {
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

    private static void periodToHHMM() {
        String stringPeriod = "P0DT7H35M";
        PeriodFormatter periodFormatter = ISOPeriodFormat.standard();
        Period period = periodFormatter.parsePeriod(stringPeriod);

        periodFormatter = new PeriodFormatterBuilder()
                .appendDays().appendSeparator("d")
                .appendHours().appendSeparator(":")
                .appendMinutes().toFormatter();

        String print = periodFormatter.print(period);
        System.out.println(print);
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
