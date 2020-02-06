package project;

public class BMI {

    public static void main(String[] args) {

        for (int i = 40; i < 65; i++) {
            for (int j = 150; j < 185; j++) {
                double BMI = i / (j * j / 10000.0);
                if (BMI > 15.00 && BMI < 16.50) {
                    System.out.println("Waga - " + i + " , wzrost - " + j);
                }
            }
        }
        System.err.println("EXTRA");
         for (int i = 40; i < 65; i++) {
            for (int j = 150; j < 185; j++) {
                double BMI = i / (j * j / 10000.0);
                if (BMI > 16.50 && BMI < 17.00) {
                    System.out.println("Waga - " + i + " , wzrost - " + j);
                }
            }
        }
        System.err.println("LIGA 2");
        for (int i = 40; i < 65; i++) {
            for (int j = 150; j < 185; j++) {
                double BMI = i / (j * j / 10000.0);
                if (BMI > 17.00 && BMI < 17.63) {
                    System.out.println("Waga - " + i + " , wzrost - " + j);
                }
            }
        }
        System.err.println("LIGA 3");
        for (int i = 40; i < 65; i++) {
            for (int j = 150; j < 185; j++) {
                double BMI = i / (j * j / 10000.0);
                if (BMI > 17.63 && BMI < 18.00) {
                    System.out.println("Waga - " + i + " , wzrost - " + j);
                }
            }
        }
        System.err.println("50");
        for (int i = 40; i < 65; i++) {
            for (int j = 150; j < 185; j++) {
                double BMI = i / (j * j / 10000.0);
                if (BMI > 18.00 && BMI < 19.00) {
                    System.out.println("Waga - " + i + " , wzrost - " + j);
                }
            }
        }
        System.err.println("52");
        for (int i = 40; i < 65; i++) {
            for (int j = 150; j < 185; j++) {
                double BMI = i / (j * j / 10000.0);
                if (BMI > 19.00 && BMI < 19.15) {
                    System.out.println("Waga - " + i + " , wzrost - " + j);
                }
            }
        }
    }
}
