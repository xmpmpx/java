package tasks.onp;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        String onp = "2 3 5 - 3 * -";
        System.out.println(oblicz(onp));
    }

    private static int oblicz(String onp) {
        String[] array = onp.split(" ");

        Deque<Integer> stack = new LinkedList<>();
        Arrays.stream(array).forEachOrdered(getONPConsumer(stack));
        return stack.pop();
    }

    private static Consumer<String> getONPConsumer(Deque<Integer> stack) {
        return znak -> stack.push(isNumber(znak) ?
                Integer.valueOf(znak) :
                Integer.valueOf(expression(znak, stack.pop(), stack.pop())));
    }

    private static int expression(String znak, int second, int first) {
        return switch (znak) {
            case "+" -> first + second;
            case "-" -> first - second;
            case "*" -> first * second;
            case "/" -> first / second;
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }

    private static boolean isNumber(String znak) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(znak);
        return matcher.matches();
    }
}
