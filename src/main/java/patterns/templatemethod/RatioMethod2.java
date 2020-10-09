package patterns.templatemethod;

public class RatioMethod2 extends Equality {

    @Override
    protected double getRatio(double personSum) {
        return 1 / personSum;
    }
}
