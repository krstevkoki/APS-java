package mk.ukim.finki.a2;

public class GreedyExamples {
    public void sortCoins(int coins[]) {
        for (int i = 0; i < coins.length - 1; ++i) {
            for (int j = 0; j < coins.length - i - 1; ++j) {
                if (coins[j] < coins[j + 1]) {
                    int temp = coins[j];
                    coins[j] = coins[j + 1];
                    coins[j + 1] = temp;
                }
            }
        }
    }

    public int greedyCoins(int coins[], int sum, int coinsNum[]) {
        sortCoins(coins);
        int minCoinsCouter = 0;
        for (int i = 0; sum > 0; ++i) {
            coinsNum[i] = sum / coins[i];
            sum -= coins[i] * coinsNum[i];
            minCoinsCouter += coinsNum[i];
        }
        return minCoinsCouter;
    }

    public void sortirajPoSoodnos(int p[], int t[]) {  // p[] = profit; t[] = tezini
        for (int i = 0; i < p.length - 1; ++i) {
            for (int j = 0; j < p.length - i - 1; ++j) {
                if ((p[j] / (float) t[j]) < (p[j + 1] / (float) t[j + 1])) {
                    int tempP = p[j];
                    int tempT = t[j];
                    p[j] = p[j + 1];
                    t[j] = t[j + 1];
                    p[j + 1] = tempP;
                    t[j + 1] = tempT;
                }
            }
        }
    }

    public double greedyFractionalKnapsack(int p[], int t[], double C, double x[]) {
        sortirajPoSoodnos(p, t);  // p[i] / t[i] >= p[i + 1] / t[i + 1]
        double profit = 0;
        for (int i = 0; i < t.length; ++i) {
            if (t[i] < C) {
                x[i] = 1;
                C -= t[i];
                profit += p[i];
            } else {
                x[i] = C / (double) t[i];
                profit += x[i] * (double) p[i];
                break;
            }
        }
        return profit;
    }
}
