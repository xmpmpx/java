package algorytmy;

import java.util.Arrays;

public class InsertionSort {

    public static void main(String[] args) {

        //7 | 5, 6, 3, 2, 1, 4
        //5 | 7, 6, 3, 2, 1, 4
        //5,7 | 6, 3, 2, 1, 4
        //5,7 | 7, 3, 2, 1, 4

        int[] tab = {7, 5, 6, 3, 2, 1, 4};
        System.out.println("Start:");
        System.out.println(Arrays.toString(tab));

        for (int i = 1; i < tab.length; i++) {
            int insert = tab[i];
            int right = i;
            int left = i - 1;

            while (left >= 0 && insert < tab[left]) {
                tab[right] = tab[left];
                left--;
                right--;
            }
            tab[right] = insert;
            System.out.println(Arrays.toString(tab) + " iteracja " + i);
        }

        System.out.println("Posortowano:");
        System.out.println(Arrays.toString(tab));
    }
}
