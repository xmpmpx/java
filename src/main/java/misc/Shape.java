package misc;

import java.util.Objects;

abstract class Shape {

    private final String NAZWA;
    public static String PARAM;
    private int wysokosc = 10;
    String kolor;
    Shape(String kolor, int wysokosc) {
        this.kolor = kolor;
        this.wysokosc = wysokosc;
        this.NAZWA  = "KSZTAŁT";
    }

    public abstract void setKolor(String kolor);

    public void setWysokosc(int wysokosc) {
        this.wysokosc = wysokosc;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "kolor='" + kolor + '\'' +
                ", wysokosc=" + wysokosc +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape)) return false;
        Shape shape = (Shape) o;
        return wysokosc == shape.wysokosc &&
                Objects.equals(kolor, shape.kolor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kolor, wysokosc);
    }
}
