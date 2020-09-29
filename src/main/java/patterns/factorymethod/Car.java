package patterns.factorymethod;

public abstract class Car {
    int pojemnosc;
    String paliwo;
    int rokProdukcji;

    public Car(int pojemnosc, String paliwo, int rokProdukcji) {
        this.pojemnosc = pojemnosc;
        this.paliwo = paliwo;
        this.rokProdukcji = rokProdukcji;
    }

    @Override
    public String toString() {
        return "Car{" +
                "pojemnosc=" + pojemnosc +
                ", paliwo='" + paliwo + '\'' +
                ", rokProdukcji=" + rokProdukcji +
                '}';
    }
}
