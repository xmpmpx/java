package patterns.templatemethod;

public class RatioMethod extends Equality {

    @Override
    protected double getRatio(double personSum) {
        return Math.sqrt(personSum);
    }
}
