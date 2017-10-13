package mk.ukim.finki.a2;

public class GreedyTester {
    public static void main(String[] args) {
        GreedyExamples g = new GreedyExamples();
        int coins[] = new int[5];
        int sum = 79;
        int coinsNum[] = new int[5];

        coins[0] = 1;
        coins[1] = 2;
        coins[2] = 5;
        coins[3] = 10;
        coins[4] = 50;
        System.out.println("Greedy coins: " + g.greedyCoins(coins, sum, coinsNum));

        /* =================== */
        double C = 20;
        int p[] = new int[3];
        int t[] = new int[3];
        double x[] = new double[3];

        p[0] = 25;
        p[1] = 24;
        p[2] = 15;

        t[0] = 18;
        t[1] = 15;
        t[2] = 10;
        System.out.println("Greedy fractional knapsack: " + String.format("%.2f", g.greedyFractionalKnapsack(p, t, C, x)));
        for (double aX : x) {
            if (aX != 0)
                System.out.print(String.format("%.2f ", aX));
        }
    }
}
