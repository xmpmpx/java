package project;

import org.joda.time.Period;
import org.joda.time.format.ISOPeriodFormat;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        List<String> list = Collections.singletonList("String");
        Set<String> stringSet = new HashSet<>();
        stringSet.addAll(list);
        System.out.println(stringSet);

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
