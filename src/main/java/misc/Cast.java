package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cast {
    public static void main(String[] args) {

        Object o1 = new ArrayList<>(Arrays.asList("1", "2"));
        Object o2 = new ArrayList<>(Arrays.asList("3", "4"));
        Object o3 = new ArrayList<>(Arrays.asList(new M(), new M()));

        System.out.println(o1 instanceof List);
        System.out.println(o2 instanceof List);


        ArrayList<List> lists = new ArrayList<>(Arrays.asList((List) o1, (List) o2, (List) o3));
        List<String> listOfType = getListOfType(String.class, lists);
        System.out.println(listOfType);
    }

    public static <T> List<T> getListOfType(Class<T> requestedClass, List<List> lists) {
        ArrayList<T> returnedList = new ArrayList<>();
        lists.stream().filter(list -> !list.isEmpty()).filter(list -> requestedClass.isInstance(list.get(0)))
                .forEach(list -> returnedList.addAll((List<T>) list));
        return returnedList;
    }
}

class M {

}
