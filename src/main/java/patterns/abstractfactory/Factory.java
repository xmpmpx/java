package patterns.abstractfactory;

public class Factory {

    public static VehicleFactory getFactory(FactoryType factoryType) {

        switch (factoryType) {
            case RIGHT:
                return new RightVehicleFactory();
            case LEFT:
                return new LeftVehicleFactory();
            default:
                throw new IllegalArgumentException();
        }
    }
}
