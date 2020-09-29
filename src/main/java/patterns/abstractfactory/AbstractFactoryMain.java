package patterns.abstractfactory;

import patterns.abstractfactory.car.Car;
import patterns.abstractfactory.car.CarType;
import patterns.abstractfactory.superbike.Superbike;
import patterns.abstractfactory.superbike.SuperbikeType;

public class AbstractFactoryMain {
    public static void main(String[] args) {
        VehicleFactory factoryRight = Factory.getFactory(FactoryType.RIGHT);

        Car audi = factoryRight.createCar(CarType.AUDI);
        Car bmw = factoryRight.createCar(CarType.BMW);

        System.out.println(audi);
        System.out.println(bmw);

        Superbike suzuki = factoryRight.createSuperbike(SuperbikeType.SUZUKI);
        Superbike honda = factoryRight.createSuperbike(SuperbikeType.HONDA);

        System.out.println(suzuki);
        System.out.println(honda);

        System.out.println("------------");

        VehicleFactory factoryLeft = Factory.getFactory(FactoryType.LEFT);

        Car audi2 = factoryLeft.createCar(CarType.AUDI);
        Car bmw2 = factoryLeft.createCar(CarType.BMW);

        System.out.println(audi2);
        System.out.println(bmw2);

        Superbike suzuki2 = factoryLeft.createSuperbike(SuperbikeType.SUZUKI);
        Superbike honda2 = factoryLeft.createSuperbike(SuperbikeType.HONDA);

        System.out.println(suzuki2);
        System.out.println(honda2);
    }
}
