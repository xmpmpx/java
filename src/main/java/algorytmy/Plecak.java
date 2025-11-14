package algorytmy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Plecak {

    public static void main(String[] args) {

        List<Item> items = List.of(
                new Item("Moneta", 2, 3),
                new Item("Figurka", 3, 5),
                new Item("Zegar", 1, 2),
                new Item("Kula", 5, 4),
                new Item("Amulet", 3, 3));

        int maxWeight = 13;
        AtomicInteger currentWeight = new AtomicInteger();
        AtomicInteger currentValue = new AtomicInteger();
        ArrayList<Item> plecak = new ArrayList<>();
        items.stream().sorted(Comparator.comparing(Item::getRatio, Comparator.reverseOrder())).forEachOrdered(item -> {
            System.out.println(item);
            if (item.getWeight() + currentWeight.get() <= maxWeight) {
                plecak.add(item);
                currentWeight.addAndGet(item.getWeight());
                currentValue.addAndGet(item.getValue());
            }
        });

        System.out.println("Weight: " + currentWeight.get());
        System.out.println("Value: " + currentValue.get());
        System.out.println(plecak);
    }
}

class Item {

    private final String name;
    private final int value;
    private final int weight;

    public Item(String name, int value, int weight) {
        this.name = name;
        this.value = value;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public double getRatio() {
        return (double) value / weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", weight=" + weight +
                ", ratio=" + getRatio() +
                '}';
    }
}


