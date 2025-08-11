package algorytmy;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {


        int[] tab = {7, 5, 6, 3, 2, 1, 4};
        System.out.println("Start:");
        System.out.println(Arrays.toString(tab));

        quickSort(tab, 0, tab.length);

        System.out.println("Posortowano:");
        System.out.println(Arrays.toString(tab));

    }

    private static void quickSort(int[] tab, int left, int right) {
        int pivot = right - 1;
        int blue = left - 1;
        int yellow = left;

        if (left >= right) {
            return;
        }
        while (yellow < right) {
            if (tab[yellow] <= tab[pivot]) {
                blue++;
                int temp = tab[yellow];
                tab[yellow] = tab[blue];
                tab[blue] = temp;
            }
            yellow++;

            System.out.println(Arrays.toString(tab));

        }
        quickSort(tab, left, blue);
        quickSort(tab, blue + 1, right);
    }
}
