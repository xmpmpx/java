package patterns.strategy;

public class PriceCalculator {

    private PricingStrategy pricingStrategy;

    public void calculate(double price, boolean isSignedForNewsletter) {
        pricingStrategy.calculatePrice(price, isSignedForNewsletter);
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }
}
