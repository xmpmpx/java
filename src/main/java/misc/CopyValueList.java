package misc;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CopyValueList {

    public static void main(String[] args) {

        List<Val> list = new ArrayList<>(Arrays.asList(
                new Val(1, "Tomek"),
                new Val(3, "Marcin"),
                new Val(2, "Jarek")));

        System.out.println(list);

        list.stream().filter(val -> "Marcin".equals(val.getName())).findFirst().ifPresent(val -> {
            Val e = SerializationUtils.clone(val);
            e.setNr(10);
            e.setName("ADDED_VALUE");
            list.add(e);
        });

        System.out.println(list);
    }
}

class Val implements Serializable {

    int nr;
    String name;

    public Val(int nr, String name) {
        this.nr = nr;
        this.name = name;
    }

    public int getNr() {
        return nr;
    }

    public String getName() {
        return name;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Val{" +
                "nr=" + nr +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Val val = (Val) o;
        return nr == val.nr && name.equals(val.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nr, name);
    }
}
