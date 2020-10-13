package patterns.strategy;

public class RegularPrice implements PricingStrategy {

    @Override
    public void calculatePrice(double price, boolean isSignedForNewsletter) {
        if (!isSignedForNewsletter) {
            System.out.println("Cena: " + price);
        } else {
            System.out.println("Użytkownik zapisany do newslettera, należy wybrać inną strategię cenową!");
        }
    }
}
