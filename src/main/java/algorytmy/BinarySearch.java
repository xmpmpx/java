package algorytmy;

public class BinarySearch {

    // Dziel i zwyciężaj
    // O(logN)

    public static void main(String[] args) {

        int[] numbers = {1, 3, 6, 8, 10, 13, 16, 18, 20, 25, 28, 30};
        String[] words = {"Ala", "Bartek", "Celina", "Dominika", "Halina", "Justyna", "Marcin", "Sara", "Wiki", "Zuzia"};

        binarySearch(words, 0, numbers.length - 1, "Marcin");

    }

    public static void binarySearch(String[] array, int left, int right, String value) {
        int index = (left + right) / 2;
        if (array[index].charAt(0) > value.charAt(0) && index != right) {
            binarySearch(array, left, index, value);
        } else if (array[index].charAt(0) < value.charAt(0) && index != left) {
            binarySearch(array, index, right, value);
        } else if (value.equals(array[index])) {
            System.out.println("Znaleziono: " + value + " - index: " + index);
        } else {
            System.out.println("Nie znaleziono");
        }
    }
}
