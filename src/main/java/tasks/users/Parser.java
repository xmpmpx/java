package tasks.users;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private final String REGEX = "(?<imie>\\p{L}+)\s+(?<nazwisko>\\p{L}+),(?<wiek>(\\d+)?),(?<miasto>\\p{L}+)";

    public List<User> parse(String dane) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(dane);

        ArrayList<User> users = new ArrayList<>();
        while (matcher.find()) {
            String imie = matcher.group("imie");
            String wiek = matcher.group("wiek");
            String miasto = matcher.group("miasto");
            users.add(new User(imie, !wiek.isBlank() ? Integer.parseInt(wiek) : null, miasto));
        }
        return users;
    }
}
