package algorytmy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountingSort {

    public static void main(String[] args) {
        int[] tab = {7, 5, 6, 3, 2, 1, 4, 9, 2, 6, 3, 2, 1};
        System.out.println("Start:");
        System.out.println(Arrays.toString(tab));

        int max = tab[0];
        for (int i = 1; i < tab.length; i++) {
            if (tab[i] > max) {
                max = tab[i];
            }
        }
        System.out.println("Max: " + max);
        System.out.println("Max: " + Arrays.stream(tab).max().orElse(0));

        //------------------------
        Map<Integer, Long> map = Arrays.stream(tab)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(map);

        HashMap<Integer, Long> countingMap = new HashMap<>();
        for (int elem : tab) {
            countingMap.merge(elem, 1L, Long::sum);
        }
        System.out.println(countingMap);

        //------------------------[
        int pointer = 0;
        Set<Map.Entry<Integer, Long>> entries = map.entrySet();
        for (Map.Entry<Integer, Long> entry : entries) {
            Long value = entry.getValue();
            while (value-- > 0) {
                tab[pointer++] = entry.getKey();
            }
        }

        System.out.println("Posortowano:");
        System.out.println(Arrays.toString(tab));
    }
}
