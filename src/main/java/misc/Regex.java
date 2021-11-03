package misc;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {


    public static final Pattern PATTERN = Pattern.compile("-(?<paxNr>\\d)=(?<lastname>[A-Z\\s]+)/(?<firstname>[A-Z\\s]+)");
    public static final String msg = "-1=LOKO/SPOKO$-2=DUPA/JAS$-3=KROWA/MARCIN";

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = PATTERN.matcher(msg);
        while (matcher.find()) {
            sb.append("-").append(matcher.group("paxNr")).append("=").append(anonymizeName(matcher.group("lastname"), matcher.group("firstname"))).append("$");
        }

        String input = StringUtils.chop(sb.toString());
        System.out.println(input);

        Matcher matcher2 = PATTERN.matcher(input);
        while (matcher2.find()) {
            System.out.println(matcher2.group());
            System.out.println(matcher2.group(1));
            System.out.println(matcher2.group(2));
            System.out.println(matcher2.group(3));
        }
    }

    private static String anonymizeName(String lastname, String firstname) {
        return RandomStringUtils.random(10, "ABCDEF") + "/" + StringUtils.repeat("X", firstname.length());
    }
}
