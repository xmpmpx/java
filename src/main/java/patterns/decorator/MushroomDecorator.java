package patterns.decorator;

import java.math.BigDecimal;

public class MushroomDecorator extends PizzaDecorator {

    public MushroomDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public BigDecimal getPrice() {
        return pizza.getPrice().add(BigDecimal.valueOf(1.95));
    }

    @Override
    public String getDesc() {
        return pizza.getDesc().concat(" with mushrooms");
    }
}
