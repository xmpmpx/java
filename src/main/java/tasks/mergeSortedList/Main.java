package tasks.mergeSortedList;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        ListMerge listMerge = new ListMerge();

        List<Integer> integers1 = List.of(1, 1, 2, 3, 3, 4, 5, 6, 8, 10, 12);
        List<Integer> integers2 = List.of(2, 3, 4, 5, 5, 6, 7, 8, 10, 11);

        List<Integer> sortedLists = listMerge.mergeSortedLists(integers1, integers2);
        System.out.println(sortedLists);
    }
}
