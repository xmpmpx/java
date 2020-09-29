package patterns.abstractfactory;

import patterns.abstractfactory.car.AUDI;
import patterns.abstractfactory.car.BMW;
import patterns.abstractfactory.car.Car;
import patterns.abstractfactory.car.CarType;
import patterns.abstractfactory.superbike.HONDA;
import patterns.abstractfactory.superbike.SUZUKI;
import patterns.abstractfactory.superbike.Superbike;
import patterns.abstractfactory.superbike.SuperbikeType;

public class LeftVehicleFactory extends VehicleFactory {
    public Car createCar(CarType carType) {
        switch (carType) {
            case AUDI:
                return new AUDI(20, "BENZYNA", 2020);
            case BMW:
                return new BMW(18, "ROPA", 2020);
            default:
                throw new IllegalArgumentException("WRONG PARAM!");
        }
    }

    public Superbike createSuperbike(SuperbikeType superbikeType) {
        switch (superbikeType) {
            case SUZUKI:
                return new SUZUKI("BLACK", 200);
            case HONDA:
                return new HONDA("WHITE", 200);
            default:
                throw new IllegalArgumentException("WRONG PARAM!");
        }
    }
}
