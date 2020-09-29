package patterns.factorymethod;

public class CarFactory {
    public static Car createCar(CarType carType) {
        switch (carType) {
            case AUDI:
                return new AUDI(20, "BENZYNA", 2007);
            case BMW:
                return new BMW(18, "ROPA", 2002);
            default:
                throw new IllegalArgumentException("WRONG PARAM!");
        }
    }
}
