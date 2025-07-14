package tasks.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final String CITY = "city";

    public static void main(String[] args) {

        String cities = "Brzostek,13 Gdynia,13, Jasło,13, Kraków,13, Łódź,13";

        Pattern pattern = Pattern.compile("(?<" + CITY + ">\\p{L}+),\\d+");
        Matcher matcher = pattern.matcher(cities);
        while (matcher.find()) {
            System.out.println(matcher.group(CITY));
        }

    }
}
