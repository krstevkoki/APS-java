package mk.ukim.finki.lab3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {
    private static int najdiNajdolgaCikCak(int a[]) {
        // Vasiot kod tuka
        int[] temp = new int[a.length];
        temp[0] = a[0];
        int maxLength = 1;
        int length = 1;
        for (int i = 1, j = 0; i < a.length; ++i) {
            if (temp[j] > 0) {
                if (a[i] < 0) {
                    j++;
                    temp[j] = a[i];
                    ++length;
                    if (length > maxLength)
                        maxLength = length;
                } else {
                    j = 0;
                    temp[j] = a[i];
                    length = 1;
                }
            } else if (temp[j] < 0) {
                if (a[i] > 0) {
                    j++;
                    temp[j] = a[i];
                    ++length;
                    if (length > maxLength)
                        maxLength = length;
                } else {
                    j = 0;
                    temp[j] = a[i];
                    length = 1;
                }
            } else { // if the char is 0
                j = 0;
                temp[j] = a[i];
                length = 1;
            }
        }
        return maxLength;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = Integer.parseInt(br.readLine());
        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);
        br.close();
    }
}
