package mk.ukim.finki.lab2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Homework {
    public static int minBrojKazneni(int a[]) {
        // Vasiot kod tuka
        Arrays.sort(a);
        int min = 0;
        int midResult = 0;
        for (int i = 0; i < a.length; ++i) {
            midResult += a[i];
            min += midResult;
        }
        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = Integer.parseInt(br.readLine());
        int rez = minBrojKazneni(a);
        System.out.println(rez);
        br.close();
    }
}
