package mk.ukim.finki.a2;

class BruteForceExamples {
    private static final int INF = 1000000;

    public double min(double a, double b) {
        return (a < b) ? a : b;
    }

    public double distance(Point a, Point b) {
        return Math.sqrt((a.getX() - b.getX()) * (a.getX() - b.getX()) + (a.getY() - b.getY()) * (a.getY() - b.getY()));
    }

    public double minDistance(Point[] p) {
        double best = INF;
        for (int i = 0; i < p.length; ++i) {
            for (int j = i + 1; j < p.length - 1; ++j) {
                best = min(best, distance(p[i], p[j]));
            }
        }
        return best;
    }

    public boolean areAttacking(int i1, int i2, int j1, int j2) {  // queen1 = (i1, j1); queen2 = (i2, j2)
        if (i1 == i2)
            return true;
        else if (j1 == j2)
            return true;
        else if (Math.abs(i1 - i2) == Math.abs(j1 - j2))
            return true;
        return false;
    }

    public int differentWaysNumber() {
        int counter = 0;
        for (int i1 = 0; i1 < 8; ++i1)
            for (int i2 = 0; i2 < 8; ++i2)
                for (int j1 = 0; j1 < 8; ++j1)
                    for (int j2 = 0; j2 < 8; ++j2)
                        if (areAttacking(i1, i2, j1, j2))
                            ++counter;
        return counter;
    }

    public int minCoinsNumber(int total) {
        int min = INF;
        int numCoins;
        int tempTotal;
        for (int m50 = 0; m50 <= total / 50; ++m50) {
            for (int m10 = 0; m10 <= total / 10; ++m10) {
                for (int m5 = 0; m5 <= total / 5; ++m5) {
                    for (int m2 = 0; m2 <= total / 2; ++m2) {
                        for (int m1 = 0; m1 <= total; ++m1) {
                            tempTotal = (m50 * 50) + (m10 * 10) + (m5 * 5) + (m2 * 2) + m1;
                            if (tempTotal == total) {
                                numCoins = m50 + m10 + m5 + m2 + m1;
                                if (numCoins < min)
                                    min = numCoins;
                            }
                        }
                    }
                }
            }
        }
        return min;
    }
}
