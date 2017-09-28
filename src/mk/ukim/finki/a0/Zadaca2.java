package mk.ukim.finki.a0;

import java.util.Scanner;

public class Zadaca2 {
    public static void main(String[] args) {
        Scanner numInt = new Scanner(System.in);
        Scanner numShort = new Scanner(System.in);

        System.out.println("Vnesete opseg na broevi:");
        System.out.print("start = ");
        int start = numInt.nextInt();
        System.out.print("end = ");
        int end = numInt.nextInt();

        if (start > end) {
            System.out.println("Nevalidni vrednosti za opseg");
            return;
        }

        System.out.println("Izbor: 1-Parni    2-Neparni");
        short choice = numShort.nextShort();
        if (choice == 1) {
            for (int i = start; i <= end; ++i) {
                if (i % 2 == 0)
                    System.out.println(i + " ");
            }
        } else if (choice == 2) {
            for (int i = start; i <= end; ++i) {
                if (i % 2 != 0)
                    System.out.println(i + " ");
            }
        } else {
            System.out.println("Nevaliden izbor!");
        }

        // Reshenie so while
        /*if (choice == 1) {
            int i = start;
            while (i <= end) {
                if (i % 2 == 0)
                    System.out.println(i + " ");
                ++i;
            }
        } else if (choice == 2) {
           int i = start;
           while (i <= end) {
               if (i % 2 != 0)
                   System.out.println(i + " ");
               ++i;
           }
        } else {
            System.out.println("Nevaliden izbor!");
        }*/

        // Reshenie so do-while
        /*if (choice == 1) {
            int i = start;
            do {
                if (i % 2 == 0)
                    System.out.println(i + " ");
                ++i;
            } while (i <= end);
        } else if (choice == 2) {
            int i = start;
            do {
                System.out.println(i + " ");
                ++i;
            } while (i <= end);
        } else {
            System.out.println("Nevaliden izbor!");
        }*/
    }
}
