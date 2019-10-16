package project;

import static project.Shape.PARAM;

public class Circle extends Shape implements Inter<Integer> {

    public Circle(String kolor, int wysokosc) {
        super(kolor, wysokosc);
    }

    @Override
    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public static void main(String[] args) {
        Circle circle = new Circle("red", 100);
        circle.setKolor("black");
    }

    @Override
    public Integer display(Integer obj) {
        return null;
    }

    @Override
    public void doOneMore() {
        Inter.doSth();
        this.display(5);
        Inter.super.display(5);
    }
}

class KlasaTestowa {
    public static void main(String[] args) {
        PARAM = "AB34R$";
        Inter.doSth();
        Inter.doSthType(5);
        Inter i = new Inter<String>() {
            @Override
            public void doOneMore() {
                Inter.doSth();
            }

            @Override
            public String display(String obj) {
                return null;
            }
        };
    }
}
