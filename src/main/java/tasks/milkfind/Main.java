package tasks.milkfind;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Object[][] products = {
                {1, "Amul milk"},
                {2, "Nandini Chocolate Milk"},
                {3, "Milk Chocolate"},
                {4, "Chocolate Milk"},
                {5, "Milky Way Chocolate Bar"}
        };

        Map<Integer, String[]> data = Arrays.stream(products)
                .collect(Collectors.toMap(objects -> Integer.valueOf(String.valueOf(objects[0])),
                        objects -> String.valueOf(objects[1]).split(" ")));

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] inputWords = input.split(" ");

        data.entrySet()
                .stream()
                .map(entry -> contains(entry.getKey(), entry.getValue(), inputWords))
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Record::getScore, Comparator.reverseOrder())
                        .thenComparing(record -> record.getValues()[0]))
                .forEach(System.out::println);


    }

    private static Record contains(Integer key, String[] productWords, String[] inputWords) {
        int matches = 0;
        for (String inputWord : inputWords) {
            for (String productWord : productWords) {
                if (inputWord.equalsIgnoreCase(productWord)) {
                    matches++;
                }
            }
        }
        return (matches != inputWords.length) ? null : computeRecord(key, inputWords, productWords);
    }

    private static Record computeRecord(Integer key, String[] inputWords, String[] productWords) {
        double score = ((double) getLength(inputWords) / getLength(productWords) * 100);
        return new Record(key, productWords, (int) score);
    }

    private static int getLength(String[] inputWords) {
        return Arrays.stream(inputWords)
                .mapToInt(String::length)
                .reduce(0, Integer::sum);
    }
}


class Record {

    private int key;
    private String[] values;
    private int score;

    public Record(int key, String[] values, int score) {
        this.key = key;
        this.values = values;
        this.score = score;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Record{" +
                "key=" + key +
                ", values=" + Arrays.toString(values) +
                ", score=" + score +
                '}';
    }
}

