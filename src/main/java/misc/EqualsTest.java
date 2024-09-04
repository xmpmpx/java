package misc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class EqualsTest {

    public static void main(String[] args) {
        Wrapper w1 = new Wrapper("text", new Inside("text", new Car("car")), List.of(new Inside("text2", new Car("car")), new Inside("text3", new Car("car2"))));
        w1.setId(1L);
        Wrapper w2 = new Wrapper("text", new Inside("text", new Car("car")), List.of(new Inside("text3", new Car("car2")), new Inside("text2", new Car("car"))));
        w2.setId(2L);

        System.out.println(w1.equals(w2));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Wrapper extends Super {

    private String text;
    private Inside inside;
    private List<Inside> insides;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wrapper)) return false;

        Wrapper wrapper = (Wrapper) o;

        return Objects.equals(text, wrapper.text)
                && Objects.equals(inside, wrapper.inside)
                && new HashSet<>(insides).equals(new HashSet<>(wrapper.insides));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inside, new HashSet<>(insides));
    }
}

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
class Super {

    Long id;
}

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
class Inside {

    private String text;
    private Car car;
}

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
class Car {

    private String text;
}
