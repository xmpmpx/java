package algorytmy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonDuplicate {

    public static final String NOT_FOUND = "Not found";

    public static void main(String[] args) {

        // Znajdz pierwszą literę z ciągu znaków, która się nie powtarza

        assert findFirstNonDuplicate("").equals(NOT_FOUND);
        assert findFirstNonDuplicate("abcdefacdef").equals("b");
        assert findFirstNonDuplicate("Aabcdefcdef").equals("A");
        assert findFirstNonDuplicate("abcdebcadef").equals("f");
        assert findFirstNonDuplicate("bbb").equals(NOT_FOUND);
    }

    protected static String findFirstNonDuplicate(String letters) {

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < letters.length(); i++) {
            char char1 = letters.charAt(i);
            map.put(char1, map.getOrDefault(char1, 0) + 1);
        }

        for (int i = 0; i < letters.length(); i++) {
            char char2 = letters.charAt(i);
            if (map.get(char2) == 1) {
                return String.valueOf(char2);
            }
        }
        return NOT_FOUND;
    }

    protected static String findFirstNonDuplicateStream(String letters) {
        Map<Character, Long> map = letters.chars()
                .mapToObj(value -> (char) value)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Optional<String> first = letters.chars()
                .mapToObj(value -> (char) value)
                .filter(e -> map.getOrDefault(e, 0L) == 1)
                .map(String::valueOf)
                .findFirst();

        return first.orElse(NOT_FOUND);
    }

    protected static String findFirstNonDuplicateOrder(String letters) {
        Map<Character, Long> map = letters.chars()
                .mapToObj(value -> (char) value)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        Optional<String> first = map.entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .map(String::valueOf)
                .findFirst();

        return first.orElse(NOT_FOUND);
    }
}
