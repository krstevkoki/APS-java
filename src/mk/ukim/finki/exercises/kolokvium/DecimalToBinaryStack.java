package mk.ukim.finki.exercises.kolokvium;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class DecimalToBinaryStack {
    private static long convert(int n) {
        Stack<Integer> converted = new Stack<>();
        StringBuilder sb = new StringBuilder();
        sb.append("0");
        while (n != 0) {
            converted.push(n % 2);
            n = n / 2;
        }
        while (!(converted.isEmpty())) {
            sb.append(converted.pop().toString());
        }
        return Long.parseLong(sb.toString());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int n = sc.nextInt();
        System.out.println(convert(n));
    }
}
