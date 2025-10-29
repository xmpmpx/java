package algorytmy;

public class RozmianaMonet {

    public static void main(String[] args) {

        int[] coins = {2, 3, 4};

        int value = 9;

        int result = bottom_up(coins, value);
        if (result != Integer.MAX_VALUE) {
            System.out.println("Minimalna ilość monet do rozmiany " + value + " to " + result);
        } else {
            System.out.println("Nie można rozmienić!");
        }
    }

    private static int bottom_up(int[] coins, int value) {

        // init
        double[] array = new double[value + 1];
        array[0] = 0;
        for (int i = 1; i <= value; i++) {
            array[i] = Double.POSITIVE_INFINITY;
        }
        // implementacja
        for (int coin : coins) {
            for (int i = coin; i <= value; i++) {
                array[i] = Math.min(array[i], array[i - coin] + 1);
            }
        }
        return (int) array[value];
    }
}
