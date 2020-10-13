package patterns.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalePrice implements PricingStrategy {

    @Override
    public void calculatePrice(double price, boolean isSignedForNewsletter) {
        if (isSignedForNewsletter) {
            BigDecimal cena = BigDecimal.valueOf(price).divide(BigDecimal.valueOf(2), RoundingMode.DOWN);
            System.out.println("Przecena: " + cena);
        } else {
            System.out.println("Użytkownik NIE jest zapisany do newslettera, należy wybrać inną strategię cenową!");
        }
    }
}
