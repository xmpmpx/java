package patterns.strategy;

public class StrategyMain {

    public static void main(String[] args) {
        PriceCalculator priceCalculator = new PriceCalculator();
        //Użytkownik nie jest zapisany do newslettera - normalna cena
        priceCalculator.setPricingStrategy(new RegularPrice());
        priceCalculator.calculate(145.99, false);
        //Użytkownik jest zapisany do newslettera - wybrana błędna strategia
        priceCalculator.setPricingStrategy(new RegularPrice());
        priceCalculator.calculate(145.99, true);
        //Użytkownik jest zapisany do newslettera - pól ceny
        priceCalculator.setPricingStrategy(new SalePrice());
        priceCalculator.calculate(145.99, true);
        //Użytkownik jest zapisany do newslettera - wybrana błędna strategia
        priceCalculator.setPricingStrategy(new SalePrice());
        priceCalculator.calculate(145.99, false);
    }
}
