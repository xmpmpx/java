package patterns.decorator;

import java.math.BigDecimal;

public class Margarita implements Pizza {

    private static final BigDecimal BASE_PRICE = new BigDecimal("12.95");

    public BigDecimal getPrice() {
        return BASE_PRICE;
    }

    @Override
    public String getDesc() {
        return "Margarita";
    }
}
