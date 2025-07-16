package tasks.nna;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Double> numbers = List.of(-1.0, 2.0, 6.0, -6.0, 0.0, 4.1);
        System.out.println(computeNNA(numbers));
        System.out.println(computeNNABigDecimal(numbers));
    }

    private static double computeNNABigDecimal(List<Double> numbers) {
        BigDecimal sum = new BigDecimal(0);
        int count = 0;
        for (Double number : numbers) {
            if (number >= 0) {
                sum = sum.add(new BigDecimal(String.valueOf(number)));
                count++;
            }
        }
        return count == 0 ? 0 : sum.divide(new BigDecimal(count), MathContext.UNLIMITED).doubleValue();
    }

    private static double computeNNA(List<Double> numbers) {
        double sum = 0.0;
        int count = 0;
        for (Double number : numbers) {
            if (number >= 0) {
                sum += number;
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }
}
