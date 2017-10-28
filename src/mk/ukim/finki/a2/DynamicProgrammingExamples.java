package mk.ukim.finki.a2;

class DynamicProgrammingExamples {
    private final static int INFINITY = 1000000;

    public int binomialCoefficient(int n, int m) {
        int[][] bc = new int[n + 1][n + 1];
        for (int i = 0; i < bc.length; ++i) {
            bc[i][i] = 1;
            bc[i][0] = 1;
        }
        for (int i = 2; i < bc.length; ++i)
            for (int j = 1; j < i; ++j)
                bc[i][j] = bc[i - 1][j - 1] + bc[i - 1][j];
        return bc[n][m];
    }

    public int max(int a, int b) {
        return a > b ? a : b;
    }

    public int max(int a, int b, int c) {
        int max = a;
        if (b > max)
            max = b;
        if (c > max)
            max = c;
        return max;
    }

    public int robotMaxSum(int a[][]) {
        int[][] best = new int[a.length][a.length];
        best[0][0] = a[0][0];
        for (int i = 1; i < best.length; ++i) {
            best[0][i] = a[0][i] + best[0][i - 1];
            best[i][0] = a[i][0] + best[i - 1][0];
        }
        for (int i = 1; i < best.length; ++i) {
            for (int j = 1; j < best.length; ++j) {
                // best[i][j] = max(best[i][j - 1], best[i - 1][j], best[i - 1][j - 1]) + a[i][j];
                best[i][j] = max(best[i][j - 1], best[i - 1][j]) + a[i][j];
            }
        }
        for (int[] ints : best) {
            for (int anInt : ints) {
                System.out.print(String.format("%3s\t", Integer.toString(anInt)));
            }
            System.out.println();
        }
        return best[a.length - 1][a.length - 1];
    }

    public int min(int x, int y) {
        return x < y ? x : y;
    }

    public void minFlightPrice(int[] tax, int[][] cost, int[] best) {
        best[0] = tax[0];  // se plakja samo taksa za do prviot grad
        for (int i = 1; i < best.length; ++i) {
            best[i] = INFINITY;
            for (int j = 0; j < i; ++j)
                best[i] = min(best[i], best[j] + tax[i] + cost[i][j]);
        }
    }

    public int dynamicCoins(int[] coins, int sum) {
        int[] x = new int[sum + 1];
        for (int i = 0; i < coins.length; ++i)
            x[coins[i]] = 1;
        for (int i = 1; i < x.length; ++i) {
            for (int j = 0; j < coins.length; ++j) {
                if (coins[j] + i <= sum) {
                    if (x[coins[j] + i] == 0 || x[coins[j] + i] > x[i] + 1) {
                        x[i + coins[j]] = x[i] + 1;
                    }
                }
            }
        }
        return x[sum];
    }
}
