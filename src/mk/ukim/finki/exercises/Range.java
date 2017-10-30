package mk.ukim.finki.exercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Range {
    private static long digitsSum(long num) {
        long sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    private static int proveri(long N, long A, long B) {
        if (A == B)
            return -1;
        if (A * A + digitsSum(A) + 200 * A == N)
            return (int) A;
        if (B * B + digitsSum(B) + 200 * B == N)
            return (int) B;
        long mid = (A + B) / 2;
        long temp = mid * mid + digitsSum(mid) + 200 * mid;
        if (temp > N)
            return proveri(N, A, mid);
        else if (temp < N)
            return proveri(N, mid, B);
        else return (int) mid;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            long N = Long.parseLong(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());
            int res = proveri(N, A, B);
            System.out.println(res);
        }
    }
}
