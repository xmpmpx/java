package project;

public class DoubleCompare {
    public static void main(String[] args) {
        Double niezero = 0.1;
        Double zero = 0.0;

        System.out.println(Double.compare(niezero, 0.0d));
        System.out.println(Double.compare(niezero, 0.1d));
        System.out.println(Double.compare(niezero, 0.11d));
        System.out.println();
        System.out.println(Double.compare(zero, 0.0d));
        System.out.println(Double.compare(zero, 0.01d));
        System.out.println(Double.compare(zero, 0.1d));
    }
}
