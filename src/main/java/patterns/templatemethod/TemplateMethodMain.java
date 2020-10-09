package patterns.templatemethod;

public class TemplateMethodMain {

    public static void main(String[] args) {
        RatioMethod ratioMethod = new RatioMethod();
        RatioMethod2 ratioMethod2 = new RatioMethod2();

        ratioMethod.checkEquality();
        ratioMethod2.checkEquality();
    }
}
