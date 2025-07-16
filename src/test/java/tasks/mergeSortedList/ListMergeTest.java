package tasks.mergeSortedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ListMergeTest {

    public final ListMerge underTest = new ListMerge();

    @Test
    void testFirstListLonger() {

        List<Integer> integers1 = List.of(1, 1, 2, 3, 3, 4, 5, 6, 8, 10, 12, 13, 14);
        List<Integer> integers2 = List.of(2, 3, 4, 5, 5, 6, 7, 8, 10, 11);

        List<Integer> sortedLists = underTest.mergeSortedLists(integers1, integers2);

        Assertions.assertEquals(integers1.size() + integers2.size(), sortedLists.size());
    }

    @Test
    void testSecondListLonger() {

        List<Integer> integers1 = List.of(1, 1, 2, 3, 3, 4, 5, 6, 8);
        List<Integer> integers2 = List.of(2, 3, 4, 5, 5, 6, 7, 8, 10, 11, 12);

        List<Integer> sortedLists = underTest.mergeSortedLists(integers1, integers2);

        Assertions.assertEquals(integers1.size() + integers2.size(), sortedLists.size());
    }

    @Test
    void testListsSameSize() {

        List<Integer> integers1 = List.of(1, 1, 2, 3, 3, 4, 5, 6, 8, 10);
        List<Integer> integers2 = List.of(2, 3, 4, 5, 5, 6, 7, 8, 10, 11);

        List<Integer> sortedLists = underTest.mergeSortedLists(integers1, integers2);

        Assertions.assertEquals(integers1.size() + integers2.size(), sortedLists.size());
    }


}