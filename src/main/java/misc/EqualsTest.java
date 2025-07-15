package misc;

import de.danielbechler.diff.ObjectDifferBuilder;
import de.danielbechler.diff.node.DiffNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class EqualsTest {

    public static void main(String[] args) {
        Wrapper w1 = new Wrapper("text", new Inside("text", new Car("car")), List.of(new Inside("text2", new Car("car")), new Inside("text3", new Car("car2"))), Baby.BOY);
        w1.setId(1L);
        Wrapper w2 = new Wrapper("text", new Inside("text", new Car("car")), List.of(new Inside("text3", new Car("car2")), new Inside("text2", new Car("car"))), Baby.BOY);
        w2.setId(2L);

        System.out.println(w1.equals(w2));
        compareWithJavaObjectDiff(w1, w2);
    }

    public static void compareWithJavaObjectDiff(Object oldObject, Object newObject) {
        DiffNode diff = ObjectDifferBuilder.buildDefault().compare(newObject, oldObject);
        diff.visit((node, visit) -> {
            if (node.hasChanges()) {
                System.err.println("Changed property: " + node.getPath() + " " + node.getState().getReason());
            }
        });
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Wrapper extends Super {

    private String text;
    private Inside inside;
    private List<Inside> insides;
    private Baby baby;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wrapper)) return false;

        Wrapper wrapper = (Wrapper) o;

        return Objects.equals(text, wrapper.text)
                && Objects.equals(inside, wrapper.inside)
                && Objects.equals(baby, wrapper.baby)
                && new HashSet<>(insides != null ? insides : Collections.emptyList())
                .equals(new HashSet<>(wrapper.insides != null ? wrapper.insides : Collections.emptyList()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inside, new HashSet<>(insides));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Super {

    Long id;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Inside {

    private String text;
    private Car car;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Car {

    private String text;
}

enum Baby {
    BOY, GIRL
}
