package algorytmy;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        int[] tab = {7, 5, 6, 3, 2, 1, 4};
        System.out.println("Start:");
        System.out.println(Arrays.toString(tab));

        int[] result = mergeSort(tab);

        System.out.println("Posortowano:");
        System.out.println(Arrays.toString(result));
    }

    public static int[] mergeSort(int[] array) {
        if (array.length == 1) {
            return array;
        } else {
            int middle = array.length / 2;
            int[] left = mergeSort(Arrays.copyOfRange(array, 0, middle));
            int[] right = mergeSort(Arrays.copyOfRange(array, middle, array.length));
            return merge(left, right);
        }
    }

    private static int[] merge(int[] left, int[] right) {
        int leftIndex = 0;
        int rightIndex = 0;
        ArrayList<Integer> result = new ArrayList<>();

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                result.add(left[leftIndex]);
                leftIndex++;
            } else {
                result.add(right[rightIndex]);
                rightIndex++;
            }
        }

        while (leftIndex < left.length) {
            result.add(left[leftIndex]);
            leftIndex++;
        }
        while (rightIndex < right.length) {
            result.add(right[rightIndex]);
            rightIndex++;
        }

        int[] sorted = result.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("Merge: " + Arrays.toString(sorted));
        return sorted;
    }
}
