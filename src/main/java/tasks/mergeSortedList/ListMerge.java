package tasks.mergeSortedList;

import java.util.ArrayList;
import java.util.List;

public class ListMerge {

    public List<Integer> mergeSortedLists(List<Integer> integers1, List<Integer> integers2) {
        if (integers1.isEmpty() && integers2.isEmpty()) {
            return new ArrayList<>();
        }
        if (integers1.isEmpty()) {
            return integers2;
        }
        if (integers2.isEmpty()) {
            return integers1;
        }

        List<Integer> result = new ArrayList<>();

        int pointer1 = 0;
        int pointer2 = 0;

        while (pointer1 < integers1.size() && pointer2 < integers2.size()) {
            Integer integer1 = integers1.get(pointer1);
            Integer integer2 = integers2.get(pointer2);

            if (integer1 < integer2) {
                result.add(integer1);
                pointer1++;
            } else {
                result.add(integer2);
                pointer2++;
            }
        }

        if (integers1.size() > integers2.size()) {
            result.addAll(integers1.subList(pointer1, integers1.size()));
        } else {
            result.addAll(integers2.subList(pointer2, integers2.size()));
        }

        if (pointer1 > pointer2) {
            result.addAll(integers1.subList(pointer1, integers1.size()));
        } else {
            result.addAll(integers2.subList(pointer2, integers2.size()));
        }

        return result;
    }
}
