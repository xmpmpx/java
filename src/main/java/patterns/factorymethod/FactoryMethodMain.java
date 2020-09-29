package patterns.factorymethod;

public class FactoryMethodMain {
    public static void main(String[] args) {
        Car audi = CarFactory.createCar(CarType.AUDI);
        Car bmw = CarFactory.createCar(CarType.BMW);

        System.out.println(audi);
        System.out.println(bmw);
    }
}
