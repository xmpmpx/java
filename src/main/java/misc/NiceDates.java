package misc;

import java.util.HashSet;
import java.util.List;

public class NiceDates {

    public static void main(String[] args) {

        HashSet<Object> digits = new HashSet<>();
        //01-01-2022
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= 1; k++) {
                    for (int l = 0; l <= 9; l++) {
                        for (int m = 1; m <= 2; m++) {
                            for (int n = 0; n <= 9; n++) {
                                for (int o = 0; o <= 9; o++) {
                                    for (int p = 0; p <= 9; p++) {
                                        digits.clear();
                                        digits.addAll(List.of(i, j, k, l, m, n, o, p));
                                        if (digits.size() == 2 && (i != 0 || j != 0) && (k != 0 || l != 0) && n == 0 && m == 2) {
                                            System.out.println(i + "" + j + "." + k + "" + l + "." + m + "" + n + "" + o + "" + p);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
