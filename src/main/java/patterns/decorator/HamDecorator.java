package patterns.decorator;

import java.math.BigDecimal;

public class HamDecorator extends PizzaDecorator {

    public HamDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public BigDecimal getPrice() {
        return pizza.getPrice().add(BigDecimal.valueOf(3.95));
    }

    @Override
    public String getDesc() {
        return pizza.getDesc().concat(" with ham");
    }
}
