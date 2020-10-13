package patterns.strategy;

public interface PricingStrategy {

    void calculatePrice(double price, boolean isSignedForNewsletter);
}
