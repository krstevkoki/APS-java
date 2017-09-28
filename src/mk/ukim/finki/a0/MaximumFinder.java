package mk.ukim.finki.a0;

import java.util.Scanner;

public class MaximumFinder {
    public static double maximum(double num1, double num2, double num3) {
        double maximumNumber = num1;
        if (num2 > maximumNumber)
            maximumNumber = num2;
        if (num3 > maximumNumber)
            maximumNumber = num3;
        return maximumNumber;
    }

    public static void main(String[] args) {
        Scanner numDouble = new Scanner(System.in);
        System.out.println("Vnesete 3 realni broja razdeleni so po edno prazno mesto:");
        double num1 = numDouble.nextDouble();
        double num2 = numDouble.nextDouble();
        double num3 = numDouble.nextDouble();
        double result = maximum(num1, num2, num3);
        System.out.println("Maksimumot e: " + result);
    }
}
