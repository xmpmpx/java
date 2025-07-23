package algorytmy;

public class BinarySearch {

    // Dziel i zwyciężaj
    // O(logN)

    public static void main(String[] args) {

        int[] numbers = {1, 3, 6, 8, 10, 13, 16, 18, 20, 25, 28, 30};
        String[] words = {"Ala", "Bartek", "Celina", "Dominika", "Halina", "Justyna", "Marcin", "Sara", "Wiki", "Zuzia"};

        binarySearch(words, 0, words.length - 1, "Marcin");
        System.out.println("----");
        binarySearch(words, "Marcin");

    }

    public static void binarySearch(String[] array, int left, int right, String value) {
        if (left > right) {
            System.out.println("Nie znaleziono");
            return;
        }
        int index = (left + right) / 2;
        System.out.println("Odwiedzam: " + array[index]);
        if (value.equals(array[index])) {
            System.out.println("Znaleziono: " + value + " - index: " + index);
        } else if (array[index].compareTo(value) > 0) {
            binarySearch(array, left, index - 1, value);
        } else if (array[index].compareTo(value) < 0) {
            binarySearch(array, index + 1, right, value);
        }
    }

    public static void binarySearch(String[] array, String value) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int index = (left + right) / 2;
            System.out.println("Odwiedzam: " + array[index]);
            if (value.equals(array[index])) {
                System.out.println("Znaleziono: " + value + " - index: " + index);
                return;
            } else if (array[index].compareTo(value) > 0) {
                right = index - 1;
            } else if (array[index].compareTo(value) < 0) {
                left = index + 1;
            }
        }
        System.out.println("Nie znaleziono");
    }
}
