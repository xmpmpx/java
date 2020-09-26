package patterns.builder;

public class BuilderMain {

    public static void main(String[] args) {
        House house = new House.HouseBuilder(2).build();
    }
}
