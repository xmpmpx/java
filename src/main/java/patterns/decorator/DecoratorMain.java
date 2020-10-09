package patterns.decorator;

public class DecoratorMain {

    public static void main(String[] args) {
        Pizza margarita = new Margarita();
        System.out.println(margarita.getPrice() + " - " + margarita.getDesc());

        Pizza mushroomDecorator = new MushroomDecorator(margarita);
        System.out.println(mushroomDecorator.getPrice() + " - " + mushroomDecorator.getDesc());

        Pizza hamDecorator = new HamDecorator(margarita);
        System.out.println(hamDecorator.getPrice() + " - " + hamDecorator.getDesc());

        Pizza hamAndMushroomDecorator = new MushroomDecorator(new HamDecorator(margarita));
        System.out.println(hamAndMushroomDecorator.getPrice() + " - " + hamAndMushroomDecorator.getDesc());
    }
}
