package mk.ukim.finki.a2;

import java.io.InputStreamReader;
import java.util.Scanner;

public class DynamicProgrammingTester {
    public static void main(String[] args) {
        DynamicProgrammingExamples dp = new DynamicProgrammingExamples();
        System.out.println(dp.binomialCoefficient(5, 2));

        Scanner intScanner = new Scanner(new InputStreamReader(System.in));
        int[][] a = new int[4][4];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++)
                a[i][j] = Integer.parseInt(intScanner.next());
        }
        System.out.println(dp.robotMaxSum(a));

        System.out.println("Vnesi broj na gradovi");
        int n = intScanner.nextInt();
        int[] tax = new int[n];
        int[] best = new int[n];
        int[][] cost = new int[n][n];

        for (int i = 0; i < n; ++i) {
            System.out.print("Airport tax: ");
            tax[i] = intScanner.nextInt();
            System.out.println("Ticket prices:");
            for (int j = i + 1; j < n; ++j)
                cost[j][i] = intScanner.nextInt();
        }

        dp.minFlightPrice(tax, cost, best);
        System.out.println(String.format("Najeftini letovi od 1 do %d grad: ", n));
        for (int i : best) {
            System.out.print(i + " ");
        }

        System.out.println(dp.dynamicCoins(new int[]{1, 3, 5, 8, 10}, 23));

    }
}
