package patterns.abstractfactory;

import patterns.abstractfactory.car.Car;
import patterns.abstractfactory.car.CarType;
import patterns.abstractfactory.superbike.Superbike;
import patterns.abstractfactory.superbike.SuperbikeType;

public abstract class VehicleFactory {

    public abstract Car createCar(CarType carType);

    public abstract Superbike createSuperbike(SuperbikeType superbikeType);
}
