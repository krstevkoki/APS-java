package mk.ukim.finki.lab5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OddEvenSort {
    private static void oddEvenSort(int[] a, int n) {
        // neparni - levo; parni - desno
        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < n - i - 1; ++j) {
                if (a[j] % 2 == 0) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < n - i - 1; ++j) {
                // ako se parni sortiraj vo opagjachki redosled
                if (a[j] % 2 == 0 && a[j + 1] % 2 == 0) {
                    if (a[j] < a[j + 1]) {
                        int temp = a[j];
                        a[j] = a[j + 1];
                        a[j + 1] = temp;
                    }
                }
                // ako se neparni sortiraj vo rastechki redosled
                if (a[j] % 2 != 0 && a[j + 1] % 2 != 0) {
                    if (a[j] > a[j + 1]) {
                        int temp = a[j];
                        a[j] = a[j + 1];
                        a[j + 1] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))) {
            int i;
            String s = stdin.readLine();
            int n = Integer.parseInt(s);
            s = stdin.readLine();
            String[] pom = s.split("\\s+");
            int[] a = new int[n];
            for (i = 0; i < n; i++)
                a[i] = Integer.parseInt(pom[i]);
            oddEvenSort(a, n);
            for (i = 0; i < n - 1; i++)
                System.out.print(a[i] + " ");
            System.out.print(a[i]);
        }
    }
}
