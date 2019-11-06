package project;

import java.util.Scanner;

public class Dziel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (!(input = scanner.nextLine()).equals("end")) {
            int val = Integer.parseInt(input);
            if (val % 2 == 0 || val % 3 == 0) {
                if (val % 2 == 0) {
                    System.out.print("FIZZ");
                }
                if (val % 3 == 0) {
                    System.out.print("BUZZ");
                }
            } else {
                System.out.print(val);
            }
            System.out.println();
        }
    }
}

