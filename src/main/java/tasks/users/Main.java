package tasks.users;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    // Create immutable User class that keeps name, age and city
    // parse input String in a separate thread and return collection of Users
    // return map of: city = count(user)

    private static final String testData = """ 
            Adam Nowak,18,Gdynia        Bartosz Kowalski,25,Kraków        Ewa Bartkowiak,30,Gdynia        Adam Nowak,99,Rzeszów        Krystian Ubrych,,Rzeszów        Lech Adamowski,61,Rzeszów        Alicja Misiak,,Kraków        Monika Derwa,55,Kraków        Jakub Anoniewicz,51,Sopot        
            """;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Parser parser = new Parser();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<List<User>> parseFuture = executorService.submit(() -> parser.parse(testData));
        List<User> users = parseFuture.get();
        executorService.shutdown();

        Map<String, Long> map = users.stream()
                .collect(Collectors.groupingBy(User::city, Collectors.counting()));

        map.forEach((k, l) -> System.out.println(k + " : " + l));

        String text = "Tomek,Maciek,Tomek, Marcin, Artur, Artur";

        System.out.println();
        Arrays.stream(text.split(","))
                .map(String::trim)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .forEach(System.out::println);

        System.out.println();

        Arrays.stream(text.split(","))
                .map(String::trim)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry<String, Long>::getValue).reversed().thenComparing(Map.Entry::getKey))
                .forEach(System.out::println);

    }
}
