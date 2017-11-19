package mk.ukim.finki.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShakerSort {
    private static void shakerSort(int[] a, int n) {
        for (int i = 0; i < n - i; ++i) {
            boolean change = false;

            for (int j = n - i - 1; j > i; --j) {
                if (a[j] < a[j - 1]) {
                    int tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                    change = true;
                }
            }
            for (int j = 0; j < n; ++j)
                System.out.print(a[j] + " ");
            System.out.println();

            for (int j = i + 1; j < n - i - 1; ++j) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                    change = true;
                }
            }
            for (int j = 0; j < n; ++j)
                System.out.print(a[j] + " ");
            System.out.println();

            if (!change)
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
            String s = stdin.readLine();
            int n = Integer.parseInt(s);
            s = stdin.readLine();
            String[] pom = s.split("\\s+");
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = Integer.parseInt(pom[i]);
            shakerSort(a, n);
        }
    }
}
