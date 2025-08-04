package algorytmy;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {

        int[] tab = {7, 5, 6, 3, 2, 1, 4};
        System.out.println("Start:");
        System.out.println(Arrays.toString(tab));

        for (int i = 0; i < tab.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < tab.length - 1 - i; j++) {
                System.out.println("Sprawdzam: " + tab[j] + " - " + tab[j + 1]);
                if (tab[j] > tab[j + 1]) {
                    int temp = tab[j + 1];
                    tab[j + 1] = tab[j];
                    tab[j] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            System.out.println(Arrays.toString(tab) + " iteracja " + (i + 1));
        }

        System.out.println("Posortowano:");
        System.out.println(Arrays.toString(tab));
    }
}
